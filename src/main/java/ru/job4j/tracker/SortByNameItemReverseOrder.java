package ru.job4j.tracker;

import java.util.Comparator;

/**
 * SortByNameItemReverseOrder класс реализует Comparator<Item>.
 * Предназначен для сортировки заявок по убыванию имен.
 */
public final class SortByNameItemReverseOrder implements Comparator<Item> {
    /**
     * Метод сравнения двух заявок.
     * @param first первая заявка.
     * @param second вторая заявка.
     * @return 0 если имена заявок равны,
     * < 0 если первая больше второй, иначе > 0.
     */
    @Override
    public int compare(final Item first, final Item second) {
        return second.getName().compareToIgnoreCase(first.getName());
    }
}
