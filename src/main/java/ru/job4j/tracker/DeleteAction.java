package ru.job4j.tracker;

/**
 * DeleteAction класс реализации UserAction.
 * Для команды Tracker.delete(String id).
 */
public final class DeleteAction implements UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "=== Delete Item ===";
    }

    /**
     * Метод выполнения команды Tracker.delete(String id).
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return true, т.к. выход из программы не нужен.
     */
    @Override
    public boolean execute(final Input input, final Store tracker) {
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Deleting successfully!");
        } else {
            System.out.println("Deleting failed!");
        }
        return true;
    }
}
