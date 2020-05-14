package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit Item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Enter new name: ");
        Item newItem = new Item(name);
        if (tracker.replace(id, newItem)) {
            System.out.println("Editing successfully!");
        } else {
            System.out.println("Editing failed!");
        }
        return true;
    }
}
