package ru.job4j.tracker;

/**
 * ShowAllItemsAction класс реализации UserAction.
 * Для команды Tracker.findAll().
 */
public final class ShowAllItemsAction implements UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    /**
     * Метод выполнения команды Tracker.findAll().
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return true, т.к. выход из программы не нужен.
     */
    @Override
    public boolean execute(final Input input, final Store tracker) {
        for (Item item : tracker.findAll()) {
            System.out.print(item);
        }
        return true;
    }
}
