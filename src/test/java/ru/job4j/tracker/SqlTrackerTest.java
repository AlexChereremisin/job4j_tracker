package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("name").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void replaceItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("Problem")).getId();
            boolean result = tracker.replace(id, new Item("Problem with description"));
            assertThat(result, is(true));
            assertThat(tracker.findById(id).getName(), is("Problem with description"));
            assertThat(tracker.findByName("Problem with description").size(), is(1));
            assertThat(tracker.findAll().size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Test1"));
            String id = tracker.add(new Item("#####")).getId();
            tracker.add(new Item("Test3"));
            boolean result = tracker.delete(id);
            assertThat(result, is(true));
            assertThat(tracker.findByName("#####").size(), is(0));
            assertNull(tracker.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Test1"));
            tracker.add(new Item("Test2"));
            tracker.add(new Item("Test3"));
            assertThat(tracker.findAll().size(), is(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByItemName() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Test1"));
            tracker.add(new Item("Test2"));
            tracker.add(new Item("Test1"));
            assertThat(tracker.findByName("Test2").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByItemId() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("Test1"));
            String id = tracker.add(new Item("Test2")).getId();
            tracker.add(new Item("Test3"));
            assertThat(tracker.findById(id).getName(), is("Test2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
