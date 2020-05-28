package ru.job4j.tracker;

/**
 * Input интерфейс работы с данными от пользователя.
 */
public interface Input {
    /**
     * Метод получения строки по запросу String question.
     * @param question строка выводимая пользователю.
     * @return строка полученная от пользователя.
     */
    String askStr(String question);

    /**
     * Метод получения числа по запросу String question.
     * @param question строка выводимая пользователю.
     * @return число типа int полученное от пользователя.
     */
    int askInt(String question);

    /**
     * Метод получения числа по запросу String question.
     * Число вводимое пользователем ожидается в диапазоне [0, max].
     * @param question строка выводимая пользователю.
     * @param max максимальное число,
     *            до которого ожидаем получить ввод.
     * @return число типа int полученное от пользователя.
     */
    int askInt(String question, int max);
}
