package orm_framework.core;

import orm_framework.annotation.Column;
import orm_framework.annotation.Entity;
import orm_framework.annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManagerImpl implements EntityManager {

    private final Connection connection;

    public EntityManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> T findById(int id, Class<T> type) throws SQLException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {

        String tableName = type.getAnnotation(Entity.class).tableName();
        String idColumnName = Arrays.stream(type.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow()
                .getName();

        String selectQuery = String.format("SELECT * FROM `%s` WHERE `%s` = ?",
                tableName,
                idColumnName);

        PreparedStatement preparedStatement = this.connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);

        T entity = type.getConstructor().newInstance();

        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }

        for (Field field : type.getDeclaredFields()) {

            if (field.isAnnotationPresent(Column.class)) {
                Column columnInfo = field.getAnnotation(Column.class);
                String setterName = "set" + (String.valueOf(field.getName().charAt(0)).toUpperCase()) + field.getName().substring(1);

                if (field.getType().equals(String.class)) {
                    String s = resultSet.getString(columnInfo.name());
                    type.getMethod(setterName, String.class).invoke(entity, s);
                } else if (field.getType().equals(LocalDate.class)) {
                    LocalDate s = LocalDate.parse(resultSet.getString(columnInfo.name()));
                    type.getMethod(setterName, LocalDate.class).invoke(entity, s);
                } else {
                    int s = resultSet.getInt(columnInfo.name());
                    type.getMethod(setterName, field.getType()).invoke(entity, s);
                }

            } else if (field.isAnnotationPresent(Id.class)) {
                String setterName = "set" + ((field.getName().charAt(0) + "").toUpperCase()) + field.getName().substring(1);
                type.getMethod(setterName, int.class).invoke(entity, id);
            }
        }

        return entity;
    }

    @Override
    public <T> boolean persist(T entity) throws IllegalAccessException, SQLException {
        Field idField = this.getIdFieldFromEntity(entity);
        idField.setAccessible(true);
        int id = (int) idField.get(entity);

        if (id == 0) {
            return this.doInsert(entity);
        }

        return this.doUpdate(id, entity);
    }

    private <T> boolean doInsert(T entity) throws SQLException {
        String tableName = this.getTableName(entity);

        String fieldNames = this.getFieldNamesBy(entity.getClass());

        String fieldValues = this.getFieldsValuesAsStr(entity);

        String insertQuery = String.format("INSERT INTO `%s` (%s) VALUES (%s) ",
                tableName,
                fieldNames,
                fieldValues);

        PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery);

        return preparedStatement.execute();
    }

    private String getFieldNamesBy(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getAnnotation(Column.class).name())
                .collect(Collectors.joining(" ,"));
    }

    private <T> String getFieldsValuesAsStr(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    try {
                        return this.getValueToString(field, entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .collect(Collectors.joining(", "));
    }

    private <T> boolean doUpdate(int id, T entity) throws SQLException {
        String tableName = this.getTableName(entity);

        String fieldsNamesAndValues = this.getFieldNamesAndValuesAsMap(entity)
                .entrySet()
                .stream()
                .map(kvp -> String.format(" %s = %s ", kvp.getKey(), kvp.getValue()))
                .collect(Collectors.joining(", "));

        String updateQuery = String.format("UPDATE `%s` SET %s WHERE id = ?;",
                tableName,
                fieldsNamesAndValues);

        PreparedStatement preparedStatement = this.connection.prepareStatement(updateQuery);
        preparedStatement.setInt(1, id);

        return preparedStatement.execute();
    }

    private <T> Map<String, String> getFieldNamesAndValuesAsMap(T entity) {
        Map<String, String> resultMap = new LinkedHashMap<>();

        Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    String fieldName = field.getAnnotation(Column.class).name();
                    String fieldValue = null;

                    try {
                        fieldValue = this.getValueToString(field, entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    resultMap.put(fieldName, fieldValue);
                });

        return resultMap;
    }

    private <T> String getValueToString(Field field, T entity) throws IllegalAccessException {
        field.setAccessible(true);
        String type = field.getAnnotation(Column.class).columnDefinition();

        if (type.equals("DATE") || type.startsWith("VARCHAR")) {

            try {
                return String.format(" '%s' ", field.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return String.format(" %s ", field.get(entity));
    }

    @Override
    public <T> boolean delete(T entity) throws IllegalAccessException, SQLException {
        Field fieldId = this.getIdFieldFromEntity(entity);
        fieldId.setAccessible(true);
        int id = (int) fieldId.get(entity);

        String tableName = this.getTableName(entity);

        String deleteQuery = String.format("DELETE FROM `%s` WHERE id = ?", tableName);

        PreparedStatement preparedStatement = this.connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, id);

        return preparedStatement.execute();
    }

    private <T> Field getIdFieldFromEntity(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity doesn't have id"));
    }

    @Override
    public <T> boolean alterTable(T entity) throws SQLException {
        Map<String, String> newColumns = this.getAllNewColumnsFrom(entity);

        if (newColumns.isEmpty()) {
            return false;
        }

        String tableName = this.getTableName(entity);

        StringBuilder columnsInfo = new StringBuilder();

        Iterator<Map.Entry<String, String>> iterator = newColumns.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> nameAndDefinition = iterator.next();

            columnsInfo.append(nameAndDefinition.getKey()).append(" ").append(nameAndDefinition.getValue());

            if (iterator.hasNext()) {
                columnsInfo.append(", ");
            }
        }

        String alterQuery = String.format("ALTER TABLE `%s` ADD COLUMN %s",
                tableName,
                columnsInfo);

        return this.connection.prepareStatement(alterQuery).execute();
    }

    private <E> Map<String, String> getAllNewColumnsFrom(E entity) throws SQLException {
        Map<String, String> newColumns = new HashMap<>();
        Set<String> allColumnsInTheTable = this.getAllColumnsInTableBy(entity);

        Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    String columnsName = field.getAnnotation(Column.class).name();
                    if (!allColumnsInTheTable.contains(columnsName)) {
                        newColumns.put(columnsName, field.getAnnotation(Column.class).columnDefinition());
                    }
                });

        return newColumns;
    }

    private <T> Set<String> getAllColumnsInTableBy(T entity) throws SQLException {
        String tableName = this.getTableName(entity);
        Set<String> allColumns = new HashSet<>();

        String selectQuery = String.format("SELECT `COLUMN_NAME` FROM `information_schema`.`COLUMNS` " +
                "WHERE `TABLE_SCHEMA` = 'test_orm' AND `TABLE_NAME` = '%s'", tableName);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            allColumns.add(resultSet.getString(1));
        }

        return allColumns;
    }

    private <T> String getTableName(T entity) {
        return entity
                .getClass()
                .getAnnotation(Entity.class)
                .tableName();
    }
}