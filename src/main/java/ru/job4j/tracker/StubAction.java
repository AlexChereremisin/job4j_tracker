package ru.job4j.tracker;

/**
 * StubAction класс реализации UserAction.
 * Предназначен для тестирования.
 */
public final class StubAction implements UserAction {
    /**
     * Флаг выполнения команды.
     */
    private boolean call = false;

    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    @Override
    public String name() {
        return "Stub action";
    }

    /**
     * Метод эмуляции выполнения команды.
     * Внутри поднимаем флаг выполнения команды.
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return false, т.к. завершаем работу программы.
     */
    @Override
    public boolean execute(final Input input, final Tracker tracker) {
        call = true;
        return false;
    }

    /**
     * Геттер поля boolean call.
     * @return состояние флага boolean call.
     */
    public boolean isCall() {
        return call;
    }
}
