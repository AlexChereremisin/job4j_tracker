package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortByNameItemTest {

    @Test
    public void compare() {
        Item itema = new Item("a");
        Item itemb = new Item("b");
        Item itemc = new Item("c");
        Item itemd1 = new Item("d");
        Item itemd2 = new Item("d");
        Item[] result = {itemc, itemd1, itema, itemd2, itemb};
        Arrays.sort(result, new SortByNameItem());
        assertThat(result, is(new Item[]{itema, itemb, itemc, itemd1, itemd2}));
    }
}