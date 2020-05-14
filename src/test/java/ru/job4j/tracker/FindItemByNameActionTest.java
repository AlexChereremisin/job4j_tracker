package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class FindItemByNameActionTest {
    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item item1 = new Item("fix bug1");
        Item item2 = new Item("fix bug2");
        Item item3 = new Item("fix bug1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        FindItemByNameAction act = new FindItemByNameAction();
        act.execute(new StubInput(new String[] {item1.getName()}), tracker);
        String expect = item1.toString() + item3.toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}