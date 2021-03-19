package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tracker класс контейнер заявок типа Item.
 */
public final class MemTracker implements Store {
    /**
     * Список заявок типа Item.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод добавления в список новой заявки.
     * @param item новая заявка.
     */
    public Item add(final Item item) {
        item.setId(generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерации ID.
     * @return сгенерированный ID.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Метод получения всего списка заявок.
     * @return весь список заявок.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод поиска заявки по ее имени.
     * @param key ключ для поиска по имени.
     * @return список заявок с таким же именем.
     */
    public List<Item> findByName(final String key) {
        List<Item> rsl = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                rsl.add(item);
            }
        }
        return rsl;
    }

    /**
     * Метод нахождения индекса по ID заявки.
     * @param id ID заявки.
     * @return индекс заявки, иначе -1.
     */
    private int indexOf(final String id) {
        int rsl = -1;
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод поиска заявки по ID.
     * @param id ID аявки.
     * @return найденная заявка, иначе null.
     */
    public Item findById(final String id) {
        Item rsl = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод замены заявки по ID.
     * @param id ID заменяемой.
     * @param item новая заявка для этого ID.
     * @return true если замена совершена, иначе false.
     */
    public boolean replace(final String id, final Item item) {
        int index = this.indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            this.items.set(index, item);
        }
        return rsl;
    }

    /**
     * Метод удаления заявки по ID.
     * @param id ID удаляемой заявки.
     * @return true если удаление совершено, иначе false.
     */
    public boolean delete(final String id) {
        int index = this.indexOf(id);
        return index != -1 && this.items.remove(index) != null;
    }

    /**
     * Метод заглушка.
     */
    @Override
    public void init() {

    }

    /**
     * Метод заглушка.
     * @throws Exception исключение.
     */
    @Override
    public void close() throws Exception {

    }
}
