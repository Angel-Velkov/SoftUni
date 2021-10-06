package orm_framework.core;

import orm_framework.annotation.Column;
import orm_framework.annotation.Entity;
import orm_framework.annotation.Id;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityManagerFactory {

    public static EntityManager create(
            String dbType,
            String host,
            int port,
            String user,
            String pass,
            String dbName,
            Class<?> mainClass
    ) throws SQLException, URISyntaxException, ClassNotFoundException {

        Connection connection = createConnection(dbType, host, port, user, pass, dbName);

        List<Class<?>> classes = getEntities(mainClass);

        createTables(connection, classes);

        return new EntityManagerImpl(connection);
    }

    private static Connection createConnection(String dbType, String host, int port, String user, String pass, String dbName) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:" + dbType + "://" + host + ":" + port + "/" + dbName,
                user,
                pass
        );
    }

    private static void createTables(Connection connection, List<Class<?>> classes) throws SQLException {
        for (Class<?> classInfo : classes) {
            Entity entityInfo = classInfo.getAnnotation(Entity.class);
            StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");

            String tableName = entityInfo.tableName();

            sql.append(tableName).append(" (")
                    .append(System.lineSeparator());

            String primaryKeyDef = "";

            for (Field field : classInfo.getDeclaredFields()) {

                if (field.isAnnotationPresent(Id.class)) {
                    sql.append("  ").append(field.getName()).append(" INT AUTO_INCREMENT,")
                            .append(System.lineSeparator());

                    primaryKeyDef = "CONSTRAINT " + tableName + "_pk PRIMARY KEY (" + field.getName() + ")";
                } else if (field.isAnnotationPresent(Column.class)) {
                    Column columnInfo = field.getAnnotation(Column.class);
                    sql.append("  ").append(columnInfo.name()).append(" ").append(columnInfo.columnDefinition()).append(",")
                            .append(System.lineSeparator());
                }
            }

            sql.append("  ").append(primaryKeyDef)
                    .append(System.lineSeparator()).append(");");

            System.out.println(sql);

            connection.createStatement().execute(sql.toString());
        }
    }

    private static List<Class<?>> getEntities(Class<?> mainClass) throws URISyntaxException, ClassNotFoundException {
        String path = mainClass.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
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

    private static void scanEntities(File dir, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        for (File file : Objects.requireNonNull(dir.listFiles())) {

            if (file.isDirectory()) {
                scanEntities(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                Class<?> classInfo = Class.forName(packageName + "." + file.getName().replace(".class", ""));

                if (classInfo.isAnnotationPresent(Entity.class)) {
                    classes.add(classInfo);
                }
            }
        }
    }
}