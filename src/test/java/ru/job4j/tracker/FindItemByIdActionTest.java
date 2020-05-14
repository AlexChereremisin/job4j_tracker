package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class FindItemByIdActionTest {
    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        FindItemByIdAction act = new FindItemByIdAction();
        act.execute(new StubInput(new String[] {item.getId()}), tracker);
        String expect = item.toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}