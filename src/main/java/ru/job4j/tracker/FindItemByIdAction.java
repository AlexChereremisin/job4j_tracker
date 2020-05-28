package ru.job4j.tracker;

/**
 * FindItemByIdAction класс реализации UserAction.
 * Для команды Tracker.findById(String id).
 */
public final class FindItemByIdAction implements UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "=== Find item by Id ===";
    }

    /**
     * Метод выполнения команды Tracker.findById(String id).
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return true, т.к. выход из программы не нужен.
     */
    @Override
    public boolean execute(final Input input, final Tracker tracker) {
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.print(item);
        } else {
            System.out.println("No item with this id exists!");
        }
        return true;
    }
}
