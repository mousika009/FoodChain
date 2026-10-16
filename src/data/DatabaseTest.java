package data;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.getConnection()) {

            System.out.println("=================================");
            System.out.println("MYSQL CONNECTION SUCCESSFUL!");
            System.out.println("Connected to: foodchain_db");
            System.out.println("=================================");

        } catch (SQLException e) {

            System.out.println("MYSQL CONNECTION FAILED!");
            e.printStackTrace();

        }
    }
}