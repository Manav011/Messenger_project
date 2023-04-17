package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Databaseconnection {
    public static Connection con;

    public Connection getConnection() {

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/messengerapp","root","1234");

//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("select * from accountsdata");
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//            con.close();
        }catch(Exception e){ System.out.println(e);}


        return con;
    }
}

