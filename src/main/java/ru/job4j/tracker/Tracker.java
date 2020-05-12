package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;

    public Item add(Item item) {
        item.setId(generateId());
        this.items[this.position++] = item;
        return item;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public Item[] findAll() {
        Item[] rsl = new Item[this.position];
        int count = 0;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null) {
                rsl[count] = this.items[index];
                count++;
            }
        }
        return Arrays.copyOf(rsl, count);
    }

    public Item[] findByName(String key) {
        Item[] rsl = new Item[this.position];
        int count = 0;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null && this.items[index].getName().equals(key)) {
                rsl[count] = this.items[index];
                count++;
            }
        }
        return Arrays.copyOf(rsl, count);
    }

    public Item findById(String id) {
        Item rsl = null;
        for (int index = 0; index < this.position; index++) {
            Item item = this.items[index];
            if (item.getId().equals(id)) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }
}