package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker {
    private final List<Item> items = new ArrayList<>();

    public void add(Item item) {
        item.setId(generateId());
        this.items.add(item);
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public List<Item> findAll() {
        return this.items;
    }

    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                rsl.add(item);
            }
        }
        return rsl;
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < this.items.size(); index++) {
            if (this.items.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public Item findById(String id) {
        Item rsl = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(String id, Item item) {
        int index = this.indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            this.items.set(index, item);
        }
        return rsl;
    }

    public boolean delete(String id) {
        int index = this.indexOf(id);
        return index != -1 && this.items.remove(index) != null;
    }
}