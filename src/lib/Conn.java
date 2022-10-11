package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost/ovchip?user=postgres&password=postgres");
        } catch (SQLException ex) {
            throw new RuntimeException("Error: RuntimeException",ex);
        }
    }

    public static void closeConnection(Connection con){
        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("error"+ex.getMessage());
        }
    }

}
