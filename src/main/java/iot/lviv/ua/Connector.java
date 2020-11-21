package iot.lviv.ua;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/leszczynski";
    private static final String USERNAME = "maxroot";
    private static final String PASSWORD = "Democracy2020***DDD";

    private static Connection DATABASE_CONNECTION;

    public Connector() {
    }

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            if (DATABASE_CONNECTION == null || DATABASE_CONNECTION.isClosed()) {
                try {
                    DATABASE_CONNECTION = DriverManager.getConnection(
                            URL,
                            USERNAME,
                            PASSWORD
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DATABASE_CONNECTION;
    }
}
