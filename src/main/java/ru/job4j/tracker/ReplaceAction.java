package ru.job4j.tracker;

/**
 * ReplaceAction класс реализации UserAction.
 * Для команды Tracker.replace(String id, Item item).
 */
public final class ReplaceAction implements UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "=== Edit Item ===";
    }

    /**
     * Метод выполнения команды Tracker.replace(String id, Item item).
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return true, т.к. выход из программы не нужен.
     */
    @Override
    public boolean execute(final Input input, final Store tracker) {
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Enter new name: ");
        Item newItem = new Item(name);
        if (tracker.replace(id, newItem)) {
            System.out.println("Editing successfully!");
        } else {
            System.out.println("Editing failed!");
        }
        return true;
    }
}
