package orm.core;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface DbContext<E> {

    boolean persist(E entity) throws IllegalAccessException, SQLException;

    List<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    E findById(Class<E> table, int id) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    boolean delete(Class<E> table, int id) throws SQLException;
}
