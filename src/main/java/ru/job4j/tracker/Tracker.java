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
        return Arrays.copyOf(this.items, this.position);
    }

    public Item[] findByName(String key) {
        Item[] rsl = new Item[this.position];
        int count = 0;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getName().equals(key)) {
                rsl[count] = this.items[index];
                count++;
            }
        }
        return Arrays.copyOf(rsl, count);
    }

    public Item findById(String id) {
        int index = this.indexOf(id);
        return index != -1 ? this.items[index] : null;
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < this.position; index++) {
            Item item = this.items[index];
            if (item.getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(String id, Item item) {
        int index = this.indexOf(id);
        boolean rsl = true;
        if (index != -1) {
            item.setId(this.items[index].getId());
            this.items[index] = item;
        } else {
            rsl = false;
        }
        return rsl;
    }

    public boolean delete(String id) {
        int index = this.indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            this.items[index] = null;
            System.arraycopy(this.items, index + 1, this.items, index, this.position - index);
            this.items[position--] = null;
        }
        return rsl;
    }
}