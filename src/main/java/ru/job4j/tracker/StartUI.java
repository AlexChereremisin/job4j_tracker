package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    public void init(Input input, Tracker tracker, List<UserAction> userActions) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            int select = input.askInt("Select: ", userActions.size());
            System.out.println(userActions.get(select).name());
            run = userActions.get(select).execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> userActions) {
        System.out.println("Menu.");
        for (UserAction action : userActions) {
            System.out.println(userActions.indexOf(action) + " . " + action.name());
        }
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Tracker tracker = new Tracker();
        List<UserAction> userActions = new ArrayList<>();
        userActions.add(new CreateAction());
        userActions.add(new ShowAllItemsAction());
        userActions.add(new ReplaceAction());
        userActions.add(new DeleteAction());
        userActions.add(new FindItemByIdAction());
        userActions.add(new FindItemByNameAction());
        userActions.add(new ExitProgramAction());
        new StartUI().init(validate, tracker, userActions);
    }
}
