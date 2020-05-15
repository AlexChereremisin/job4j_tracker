package ru.job4j.tracker;

public class StartUI {
    public void init(Input input, Tracker tracker, UserAction[] userActions) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            int select = input.askInt("Select: ", userActions.length);
            System.out.println(userActions[select].name());
            run = userActions[select].execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] userActions) {
        System.out.println("Menu.");
        for (int index = 0; index < userActions.length; index++) {
            System.out.println(index + " . " + userActions[index].name());
        }
    }


    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        UserAction[] userActions = {
                new CreateAction(),
                new ShowAllItemsAction(),
                new ReplaceAction(),
                new DeleteAction(),
                new FindItemByIdAction(),
                new FindItemByNameAction(),
                new ExitProgramAction()
        };
        new StartUI().init(input, tracker, userActions);
    }
}
