package ru.job4j.tracker;

import java.util.List;

/**
 * Интерфейс контейнера заявок.
 */
public interface Store extends AutoCloseable {
    /**
     * Метод инициализации контейнера.
     */
    void init();

    /**
     * Метод добавления новой заявки.
     * @param item новая заявка.
     * @return заявка с присвоеным ID.
     */
    Item add(Item item);

    /**
     * Метод редактирования имени заявки в контейнере.
     * @param id номер заявки для редактирования.
     * @param item новая заявка.
     * @return true если редактирование прошло успешно,
     * false если неуспешно.
     */
    boolean replace(String id, Item item);

    /**
     * Метод удаления заявки из контейнера.
     * @param id номер заявки для удаления.
     * @return true если удаление прошло успешно,
     * false если неуспешно.
     */
    boolean delete(String id);

    /**
     * Метод получения всех заявок из контейнера.
     * @return список всех заявок,
     * null если заявок нет.
     */
    List<Item> findAll();

    /**
     * Метод получения заявки по имени.
     * @param key имя заявки.
     * @return список всех заявок с именем key
     * или null если совпадений нет.
     */
    List<Item> findByName(String key);

    /**
     * Метод получения заявки по номеру.
     * @param id номер заявки.
     * @return заявка с номером id или null,
     * если такого номера нет.
     */
    Item findById(String id);
}
