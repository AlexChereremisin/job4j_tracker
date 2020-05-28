package ru.job4j.tracker;

import java.util.Comparator;

/**
 * SortByNameItem класс реализует Comparator<Item>.
 * Предназначен для сортировки заявок по возрастанию имен.
 */
public final class SortByNameItem implements Comparator<Item> {
    /**
     * Метод сравнения двух заявок.
     * @param first первая заявка.
     * @param second вторая заявка.
     * @return 0 если имена заявок равны,
     * < 0 если первая меньше второй, иначе > 0.
     */
    @Override
    public int compare(final Item first, final Item second) {
        return first.getName().compareToIgnoreCase(second.getName());
    }
}
