package ru.job4j.tracker;

import java.util.List;

public class FindItemByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find items by name ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.print(item);
            }
        } else {
            System.out.println("No item with this name exists!");
        }
        return true;
    }
}
