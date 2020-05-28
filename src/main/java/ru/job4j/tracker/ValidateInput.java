package ru.job4j.tracker;

/**
 * ValidateInput класс реализующий Input.
 * Для получения проверяемых данных от пользователя через консоль.
 */
public final class ValidateInput implements Input {
    /**
     * Ссылка на данные от пользователя.
     */
    private final Input input;

    /**
     * Конструктор принимающий в качестве параметра
     * ссылку на данные от пользователя.
     * @param inpt ссылка на данные от пользователя
     */
    public ValidateInput(final Input inpt) {
        this.input = inpt;
    }

    /**
     * Метод получения строки по запросу String question.
     * @param question строка выводимая пользователю.
     * @return строка полученная от пользователя.
     */
    @Override
    public String askStr(final String question) {
        return input.askStr(question);
    }

    /**
     * Метод получения числа по запросу String question.
     * Проверяет, что это целое число.
     * Если не число, то просим повторить ввод числа.
     * Выполняется до получения целого числа.
     * @param question строка выводимая пользователю.
     * @return число типа int полученное от пользователя.
     */
    @Override
    public int askInt(final String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }

    /**
     * Метод получения числа по запросу String question.
     * Проверяет, что это целое число в диапазоне [0, max].
     * Если не число и/или не в диапазоне [0, max],
     * то просим повторить ввод числа.
     * Выполняется до получения целого числа в диапазоне [0, max].
     * @param question строка выводимая пользователю.
     * @param max максимальное число,
     *            до которого ожидаем получить ввод.
     * @return число типа int полученное от пользователя.
     */
    @Override
    public int askInt(final String question, final int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
