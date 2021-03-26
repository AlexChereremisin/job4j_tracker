package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
     * Инициализация соединения с БД.
     * @return соединение с БД.
     */
    public static Connection initConnect() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")
        ) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Точка входа в программу.
     * @param args args.
     */
    public static void main(final String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Store tracker = new SqlTracker(
                ConnectionRollback.create(initConnect())
        );
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
