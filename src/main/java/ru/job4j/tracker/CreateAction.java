package ru.job4j.tracker;

/**
 * CreateAction класс реализации UserAction.
 * Для команды Tracker.add(Item).
 */
public final class CreateAction implements UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "=== Create a new Item ====";
    }

    /**
     * Метод выполнения команды Store.add(Item).
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return true, т.к. выход из программы не нужен.
     */
    @Override
    public boolean execute(final Input input, final Store tracker) {
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        return tracker.add(item).getId() != null;
    }
}
