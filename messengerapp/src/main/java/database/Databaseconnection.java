package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Databaseconnection {
    public static Connection con;

    public Connection getConnection() {
        try {

            // con = DriverManager.getConnection("jdbc:mysql://localhost/messengerapp",
            // "root", "asdfghjkl");
            con = DriverManager.getConnection("jdbc:mysql://43.204.215.33:3306/messengerapp",
                    "root", "1234");
            // con = DriverManager.getConnection(
            // "jdbc:mysql://messenger.cqocdes0lc0n.ap-south-1.rds.amazonaws.com:3306/messengerapp",
            // "admin",
            // "12345678");

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;

    }
}
