package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Store memTracker = new MemTracker();
        Item item = new Item("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenAddNewItemsThenTrackerCanFindAll() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        List<Item> result = memTracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenTrackerHasNoItemWhenNoFind() {
        Store memTracker = new MemTracker();
        List<Item> expected = new ArrayList<>();
        List<Item> result = memTracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddNewItemsThenTrackerCanFindByName() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        List<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item3);
        List<Item> result = memTracker.findByName("test2");
        assertThat(result, is(expected));
    }

    public void whenItemNameNoExistingThenTrackerCanNotFindByName() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        List<Item> expected = new ArrayList<>();
        List<Item> result = memTracker.findByName("test4");
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddNewItemsButFindNotExistingIdWhenNull() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        Item result = memTracker.findById("12345");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenReplace() {
        Store memTracker = new MemTracker();
        Item bug = new Item("Bug");
        memTracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        memTracker.replace(id, bugWithDesc);
        assertThat(memTracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Store memTracker = new MemTracker();
        Item bug = new Item("Bug");
        memTracker.add(bug);
        String id = bug.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(id), is(nullValue()));
    }
}