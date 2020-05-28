package ru.job4j.tracker;

/**
 * ExitProgramAction класс реализации UserAction.
 * Для подачи флага завершения программы.
 */
public final class ExitProgramAction implements UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "=== Exit Program ===";
    }

    /**
     * Метод подачи флага завершения программы.
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return false, т.к. завершаем работу программы.
     */
    @Override
    public boolean execute(final Input input, final Tracker tracker) {
        return false;
    }
}
