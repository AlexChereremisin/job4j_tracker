package ru.job4j.tracker;

public class StartUI {
    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showAllItems(Tracker tracker) {
        System.out.println("=== All Items ===");
        for (Item item : tracker.findAll()) {
            System.out.printf("Name : %s ; id : %s%s", item.getName(), item.getId(), System.lineSeparator());
        }
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Edit Item ===");
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Enter new name: ");
        Item newItem = new Item(name);
        if (tracker.replace(id, newItem)) {
            System.out.println("Editing successfully!");
        } else {
            System.out.println("Editing failed!");
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ===");
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Deleting successfully!");
        } else {
            System.out.println("Deleting failed!");
        }
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Search Item by id ===");
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.printf("Name : %s ; id : %s%s", item.getName(), item.getId(), System.lineSeparator());
        } else {
            System.out.println("No item with this id exists!");
        }
    }

    public static void findItemsByName(Input input, Tracker tracker) {
        System.out.println("=== Search Item by name ===");
        String name = input.askStr("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.printf("Name : %s ; id : %s%s", item.getName(), item.getId(), System.lineSeparator());
            }
        } else {
            System.out.println("No item with this name exists!");
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                StartUI.createItem(input, tracker);
            } else if (select == 1) {
                StartUI.showAllItems(tracker);
            } else if (select == 2) {
                StartUI.replaceItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.findItemById(input, tracker);
            } else if (select == 5) {
                StartUI.findItemsByName(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
