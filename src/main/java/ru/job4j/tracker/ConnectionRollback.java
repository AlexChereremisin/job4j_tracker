package ru.job4j.tracker;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Фабричный метод соединения с
 * откатыванием изменений.
 * Нужен для интеграционных тестов.
 */
public final class ConnectionRollback {
    /**
     * Конструктор-заглушка.
     */
    private ConnectionRollback() {
        //Заглушка.
    }
    /**
     * Метод создающий соединение с БД с параметром autocommit=false.
     * @param connection соединение с БД.
     * @return соединение как тип, либо null.
     */
    public static Connection create(final Connection connection) {
        try  {
            connection.setAutoCommit(false);
            return (Connection) Proxy.newProxyInstance(
                    ConnectionRollback.class.getClassLoader(),
                    new Class[] {Connection.class},
                    (proxy, method, args) -> {
                        Object rsl = null;
                        if ("close".equals(method.getName())) {
                            connection.rollback();
                            connection.close();
                        } else {
                            rsl = method.invoke(connection, args);
                        }
                        return rsl;
                    }
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
