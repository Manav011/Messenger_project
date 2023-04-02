import java.sql.Connection;
import java.sql.DriverManager;

public class Databaseconnection {
    public Connection databaselink;

    public Connection getConnection() {
        String databaseName = "java project";
        String databaseUser = "root";
        String databasePassword = "asdfghjkl";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaselink;
    }
}
