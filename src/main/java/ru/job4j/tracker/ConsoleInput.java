package ru.job4j.tracker;

import java.util.Scanner;

/**
 * ConsoleInput класс реализующий Input.
 * Для получения данных от пользователя через консоль.
 */
public final class ConsoleInput implements Input {
    /**
     * Сканер входного потока из консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод получения строки по запросу String question.
     * @param question строка выводимая пользователю.
     * @return строка полученная от пользователя.
     */
    @Override
    public String askStr(final String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Метод получения числа по запросу String question.
     * @param question строка выводимая пользователю.
     * @return число типа int полученное от пользователя.
     */
    @Override
    public int askInt(final String question) {
        return Integer.valueOf(askStr(question));
    }

    /**
     * Метод получения числа по запросу String question.
     * Число вводимое пользователем ожидается в диапазоне [0, max].
     * @param question строка выводимая пользователю.
     * @param max максимальное число,
     *            до которого ожидаем получить ввод.
     * @return число типа int полученное от пользователя.
     */
    @Override
    public int askInt(final String question, final int max) {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            String mg = String.format("Out of about %s > [0, %s]", select, max);
            throw new IllegalStateException(mg);
        }
        return select;
    }
}
