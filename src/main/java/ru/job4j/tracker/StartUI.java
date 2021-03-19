package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * StartUI класс пользовательского консольного интерфейса.
 */
public final class StartUI {
    /**
     * Метод запуска меню с опросом пользователя.
     * @param input  ссылка на реализацию входных данных.
     * @param tracker ссылка на контейнер заявок.
     * @param userActions ссылка на список
     *                    доступных пользователю действий.
     */
    public void init(
            final Input input,
            final Store tracker,
            final List<UserAction> userActions
    ) {
        boolean run = true;
        while (run) {
            tracker.init();
            this.showMenu(userActions);
            int select = input.askInt("Select: ", userActions.size());
            System.out.println(userActions.get(select).name());
            run = userActions.get(select).execute(input, tracker);
        }
    }

    /**
     * Метод вывода меню на консоль.
     * @param userActions ссылка на список
     *                    доступных пользователю действий.
     */
    private void showMenu(final List<UserAction> userActions) {
        System.out.println("Menu.");
        for (UserAction action : userActions) {
            System.out.println(
                    userActions.indexOf(action)
                    + " . "
                    + action.name()
            );
        }
    }

    /**
     * Точка входа в программу.
     * @param args args.
     */
    public static void main(final String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Store tracker = new SqlTracker();
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
