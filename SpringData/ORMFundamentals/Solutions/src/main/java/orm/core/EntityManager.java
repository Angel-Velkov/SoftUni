package orm.core;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {
    private static final String SELECT_STAR_FROM = "SELECT * FROM ";
    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUE (%s);";
    private static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s;";
    private static final String DELETE_QUERY = "DELETE FROM %s WHERE %s;";

    private final Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException, ParseException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        if (value == null || (int) value <= 0) {
            return this.doInsert(entity);
        }
        return this.doUpdate(entity, primary);
    }

    @Override
    public List<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        Statement statement = connection.createStatement();
        String query = SELECT_STAR_FROM + getTableName(table) +
                (where.equals("")
                        ? ""
                        : where);
        ResultSet resultSet = statement.executeQuery(query);
        List<E> entities = new ArrayList<>();
        while (resultSet.next()) {
            E entity = table.getConstructor().newInstance();
            this.fillEntity(table, resultSet, entity);
        }
        return entities;
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        Statement statement = connection.createStatement();
        String query = SELECT_STAR_FROM + getTableName(table) + " WHERE " +
                (where.equals("")
                        ? ""
                        : where + " LIMIT 1;");

        ResultSet rs = statement.executeQuery(query);
        if (!rs.next()) {
            return null;
        }

        E entity = table.getConstructor().newInstance();
        this.fillEntity(table, rs, entity);

        return entity;
    }

    @Override
    public E findById(Class<E> table, int id) throws SQLException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        String tableName = table.getAnnotation(Entity.class).name();
        String idColumnName = Arrays.stream(table.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow()
                .getName();

        PreparedStatement ps = this.connection.prepareStatement(
                SELECT_STAR_FROM + tableName + " WHERE " + idColumnName + " = ?"
        );

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return null;
        }

        E entity = table.getDeclaredConstructor().newInstance();
        this.fillEntity(table, rs, entity);

        return entity;
    }

    @Override
    public boolean delete(Class<E> table, int id) throws SQLException {
        String query = String.format(DELETE_QUERY, getTableName(table), "id = " + id);

        return executeQuery(query);
    }

    public void createTables(Class<?> mainClass) throws SQLException, URISyntaxException, ClassNotFoundException {
        List<Class<?>> classes = getEntities(mainClass);

        for (Class<?> classInfo : classes) {
            Entity entityInfo = classInfo.getAnnotation(Entity.class);
            StringBuilder createStatement = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
            String tableName = entityInfo.name();
            createStatement.append(tableName).append("(")
                    .append(System.lineSeparator());

            String primaryKeyDef = "";

            for (Field field : classInfo.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    createStatement.append("    ").append(field.getName())
                            .append(" INT").append(" AUTO_INCREMENT").append(",")
                            .append(System.lineSeparator());
                    primaryKeyDef = "CONSTRAINT " + tableName + "_pk PRIMARY KEY(" + field.getName() + ")";
                } else if (field.isAnnotationPresent(Column.class)) {
                    Column columnInfo = field.getAnnotation(Column.class);
                    createStatement.append("    ").append(columnInfo.name())
                            .append(" ").append(columnInfo.columnDefinition()).append(",")
                            .append(System.lineSeparator());
                }
            }

            createStatement.append(primaryKeyDef)
                    .append(System.lineSeparator()).append(");");
            System.out.println(createStatement);

            connection.createStatement().execute(createStatement.toString());
        }
    }

    private static void scanEntities(File dir, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        // Protects from a pont at the begging of the class when we creating it.
        // I think it is because the 'Main' class is not in a package and as we pass it, the 'packageName' becomes empty (with a dot in front of it)
        packageName = packageName.equals("")
                ? packageName
                : packageName + ".";
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                scanEntities(file, packageName + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                Class<?> classInfo = Class.forName(packageName + file.getName().replace(".class", ""));
                if (classInfo.isAnnotationPresent(Entity.class)) {
                    classes.add(classInfo);
                }
            }
        }
    }

    private void fillEntity(Class<?> table, ResultSet resultSet, E entity) throws SQLException, IllegalAccessException {
        Field[] declaredFields = table.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            this.fillField(field, entity, resultSet,
                    field.isAnnotationPresent(Id.class)
                            ? "id"
                            : field.getAnnotation(Column.class).name()
            );
        }
    }

    // TODO: Make it generic
    private void fillField(Field field, E entity, ResultSet resultSet, String name) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        switch (name) {
            case "id" -> field.set(entity, resultSet.getInt("id"));
            case "username" -> field.set(entity, resultSet.getString("username"));
            case "password" -> field.set(entity, resultSet.getString("password"));
            case "age" -> field.set(entity, resultSet.getInt("age"));
            case "registration_date" -> {
                try {
                    field.set(entity, new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("registration_date")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean doInsert(E entity) throws SQLException, ParseException {
        String tableName = this.getTableName(entity.getClass());
        List<String> fieldNames = this.getFieldNames(entity);
        List<String> fieldValues = this.getFieldValues(entity);

        // I know it is not very right but I just wanted it to work.
        // Especially this with the hardcore class 'User'.
        // There must be other ways besides the triple parse of the Date.
        for (String date : getColumnsWithType(entity.getClass(), "DATE")) {
            int i = fieldNames.indexOf(date);
            fieldValues.set(i, "'" + new java.sql.Date(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(fieldValues.get(i)).getTime()) + "'");
        }

        String insertQuery = String.format(INSERT_QUERY,
                tableName,
                String.join(", ", fieldNames),
                String.join(", ", fieldValues));
        return executeQuery(insertQuery);
    }

    private boolean doUpdate(E entity, Field primaryKey) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());

        List<String> setFieldNameAndValue = Arrays.stream(entity.getClass().getDeclaredFields())
                .map(getFieldNameAndValue(entity))
                .collect(Collectors.toList());

        String insertQuery = String.format(UPDATE_QUERY,
                tableName,
                String.join(", ", setFieldNameAndValue),
                "id = " + primaryKey.get(entity));

        return executeQuery(insertQuery);
    }

    private boolean executeQuery(String statement) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(statement);
        return ps.execute();
    }

    private List<String> getColumnsWithType(Class<?> clazz, String columnDefinition) {
        ArrayList<String> columnNames = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column.columnDefinition().equals(columnDefinition)) {
                    columnNames.add(column.name());
                }
            }
        }

        return columnNames;
    }

    private static List<Class<?>> getEntities(Class<?> mainClass) throws URISyntaxException, ClassNotFoundException {
        String path = mainClass
                .getProtectionDomain()
                .getCodeSource()
                .getLocation().toURI()
                .getPath();
        String packageName = mainClass.getPackageName();

        File rootDir = new File(path + packageName.replace(".", "/"));
        List<Class<?>> classes = new ArrayList<>();

        scanEntities(
                rootDir,
                packageName,
                classes
        );

        return classes;
    }

    private Field getId(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException("Entity does not have primary key."));
    }

    private String getTableName(Class<?> entity) {
        Entity entityAnnotation = entity.getAnnotation(Entity.class);
        if (entityAnnotation != null && entityAnnotation.name().length() > 0) {
            return entityAnnotation.name();
        } else {
            return entity.getSimpleName();
        }
    }

    private List<String> getFieldValues(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(getFieldValue(entity))
                .collect(Collectors.toList());
    }

    private Function<Field, String> getFieldNameAndValue(E entity) {
        return f -> {
            f.setAccessible(true);
            try {
                Object value = f.get(entity);
                Class<?> type = f.getType();

                return String.format(" %s = %s",
                        f.isAnnotationPresent(Id.class)
                                ? "id"
                                : f.getAnnotation(Column.class).name(),
                        type == String.class || type == LocalDate.class
                                ? String.format("'%s'", value.toString())
                                : value.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return "";
        };
    }

    private Function<Field, String> getFieldValue(E entity) {
        return field -> {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                Class<?> type = field.getType();

                return type == String.class || type == LocalDate.class
                        ? String.format("'%s'", value.toString())
                        : value.toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return "";
        };
    }

    private List<String> getFieldNames(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    f.setAccessible(true);
                    return f.getAnnotation(Column.class).name();
                })
                .collect(Collectors.toList());
    }
}