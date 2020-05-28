package ru.job4j.tracker;

/**
 * StubInput класс реализующий Input.
 * Для тестирования.
 */
public final class StubInput implements Input {
    /**
     * Массив с "ответами от пользователя".
     */
    private String[] answers;
    /**
     * Указатель на позицию в массиве answers.
     */
    private int position = 0;

    /**
     * Конструктор принимающий в качестве параметра
     * массив "ответов пользователя".
     * @param answrs "ответы пользователя"
     */
    public StubInput(final String[] answrs) {
        this.answers = answrs;
    }

    /**
     * Метод получения строки по запросу String question.
     * @param question строка выводимая пользователю.
     * @return строка полученная по указателю position.
     * После указатель position увеличивается на 1.
     */
    @Override
    public String askStr(final String question) {
        return answers[position++];
    }

    /**
     * Метод получения числа по запросу String question.
     * @param question строка выводимая пользователю.
     * @return число типа int полученное
     * из массива answers по position, через вызов askStr.
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
     * @return число типа int полученное
     * из массива answers по position, через вызов askStr.
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
