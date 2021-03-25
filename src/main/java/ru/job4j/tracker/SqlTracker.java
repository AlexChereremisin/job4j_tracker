package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс реализации хранилища заявок на основе базы данных.
 */
public class SqlTracker implements Store {
    /**
     * Соединение с БД.
     */
    private Connection connection;

    /**
     * Метод инициазации соединения с БД.
     */
    @Override
    public final void init() {
        try (
                InputStream input = SqlTracker.class.
                        getClassLoader().
                        getResourceAsStream("app.properties")
        ) {
            Properties configuration = new Properties();
            configuration.load(input);
            Class.forName(configuration.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    configuration.getProperty("url"),
                    configuration.getProperty("username"),
                    configuration.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Метод закрытия соединения с БД.
     * @throws Exception исключение, если
     * закрытие не произошло.
     */
    @Override
    public final void close() throws Exception {
        this.connection.close();
    }

    /**
     * Метод добавления новой заявки в БД.
     * @param item новая заявка.
     * @return заявка с присвоеным ID.
     */
    @Override
    public final Item add(final Item item) {
        try (
                PreparedStatement statement =
                        this.connection.prepareStatement(
                                "INSERT INTO items (name) VALUES (?)",
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {
            statement.setString(1, item.getName());
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException(
                        "Creating item failed, no rows affected."
                );
            }
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getString(1));
                } else {
                    throw new SQLException(
                            "Creating item failed, no ID obtained."
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод редактирования имени заявки в БД.
     * @param id номер заявки для редактирования.
     * @param item новая заявка.
     * @return true если редактирование прошло успешно,
     * false если неуспешно.
     */
    @Override
    public final boolean replace(final String id, final Item item) {
        int count = 0;
        try (
                PreparedStatement statement =
                        this.connection.prepareStatement(
                                "UPDATE items SET name = ? WHERE id = ?"
                        )
        ) {
            statement.setString(1, item.getName());
            statement.setInt(2, Integer.parseInt(id));
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    /**
     * Метод удаления заявки по номеру из БД.
     * @param id номер заявки для удаления.
     * @return true если удаление прошло успешно,
     * false если неуспешно.
     */
    @Override
    public final boolean delete(final String id) {
        int count = 0;
        try (
                PreparedStatement statement =
                        this.connection.prepareStatement(
                                "DELETE FROM items WHERE id = ?"
                        )
        ) {
            statement.setInt(1, Integer.parseInt(id));
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    /**
     * Метод получения всех заявок из БД.
     * @return список всех заявок,
     * null если заявок нет.
     */
    @Override
    public final List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (
                PreparedStatement statement =
                        this.connection.prepareStatement(
                                "SELECT * FROM items"
                        )
        ) {
            ResultSet all = statement.executeQuery();
            Item item = null;
            while (all.next()) {
                item = new Item(all.getString("name"));
                item.setId(all.getString("id"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод получения заявок из БД по имени.
     * @param key имя заявки.
     * @return список всех заявок с именем key
     * или null если совпадений нет.
     */
    @Override
    public final List<Item> findByName(final String key) {
        List<Item> result = new ArrayList<>();
        try (
                PreparedStatement statement =
                        this.connection.prepareStatement(
                                "SELECT * FROM items WHERE items.name = ?"
                        )
        ) {
            statement.setString(1, key);
            ResultSet set = statement.executeQuery();
            Item item = null;
            while (set.next()) {
                item = new Item(set.getString("name"));
                item.setId(set.getString("id"));
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод получения заявки по номеру из БД.
     * @param id номер заявки.
     * @return заявка с номером id или null,
     * если такого номера нет.
     */
    @Override
    public final Item findById(final String id) {
        Item result = null;
        try (
                PreparedStatement statement =
                        this.connection.prepareStatement(
                                "SELECT * FROM items WHERE items.id = ?"
                        )
        ) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = new Item(set.getString("name"));
                result.setId(set.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
