package ru.job4j.tracker;

public class FindItemByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by Id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.print(item);
        } else {
            System.out.println("No item with this id exists!");
        }
        return true;
    }
}
