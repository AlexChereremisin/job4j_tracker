package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс для загрузки данных из файла в базу.
 */
public class ImportDB {
    /**
     * Конфигурация подключения к базе.
     */
    private Properties cfg;
    /**
     * Путь до файла с данными для загрузки в базу.
     */
    private String dump;

    /**
     * Конструктор с двумя параметрами для инициализации.
     * @param cfg конфигурация подключения.
     * @param dump путь ло файла с данными.
     */
    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод считывания строк из файла с
     * вычленением имени и емейла.
     * @return список типа User.
     * @throws IOException исключение ввода-вывода.
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(this.dump))) {
            rd.lines().forEach(s -> {
                String[] param = s.trim().split(";", 3);
                users.add(new User(param[0], param[1]));
            });
            return users;
        }
    }

    /**
     * Метод сохранения списка в базу.
     * @param users список типа User.
     * @throws ClassNotFoundException исключение загрузки класса.
     * @throws SQLException исключение базы данных.
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            String sql = "INSERT INTO users (user_name, email) VALUES (?, ?)";
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(sql)) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    /**
     * Внутренний класс описывающий тип User.
     */
    private static class User {
        /**
         * Имя пользователя.
         */
        String name;
        /**
         * Емейл пользователя.
         */
        String email;

        /**
         * Конструктор с двумя параметрами, для инициализации.
         * @param name имя пользователя.
         * @param email емейл пользователя.
         */
        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    /**
     * Точка входа в программу.
     * @param args параметры командной строки.
     * @throws Exception исключения.
     */
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./src/main/resources/spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db =new ImportDB(cfg, "./src/main/resources/dump.txt");
        db.save(db.load());
    }
}
