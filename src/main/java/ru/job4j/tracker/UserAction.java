package ru.job4j.tracker;

/**
 * UserAction интерфейс описывающий действия пользователя.
 */
public interface UserAction {
    /**
     * Метод получения имени команды.
     * @return имя команды.
     */
    String name();

    /**
     * Метод выполнения команды.
     * @param input ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @return false, если нужен выход из программы,
     * иначе true.
     */
    boolean execute(Input input, Tracker tracker);
}
