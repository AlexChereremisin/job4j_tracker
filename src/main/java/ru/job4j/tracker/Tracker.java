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
        Item tmp = this.findById(id);
        boolean rsl = tmp != null;
        if (rsl) {
            item.setId(tmp.getId());
            this.items.set(this.items.indexOf(tmp), item);
        }
        return rsl;
    }

    public boolean delete(String id) {
        Item tmp = this.findById(id);
        return tmp != null && this.items.remove(tmp);
    }
}