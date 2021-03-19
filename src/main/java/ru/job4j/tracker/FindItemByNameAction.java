package ru.job4j.tracker;

import java.util.List;

/**
 * FindItemByNameAction класс реализации UserAction.
 * Для команды Tracker.findByName(String name).
 */
public final class FindItemByNameAction implements UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "=== Find items by name ===";
    }

    /**
     * Метод выполнения команды Tracker.findByName(String name).
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return true, т.к. выход из программы не нужен.
     */
    @Override
    public boolean execute(final Input input, final Store tracker) {
        String name = input.askStr("Enter name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.print(item);
            }
        } else {
            System.out.println("No item with this name exists!");
        }
        return true;
    }
}
