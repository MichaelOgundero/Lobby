package util;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    public static Connection connect()  //for local
    {
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user="rovshan";
            String password="rovshan";
            String url="jdbc:mysql://localhost/projectLab";

            connection=(Connection) DriverManager.getConnection(url,user,password);
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Connection failed (ClassNotFoundException)");
        }
        catch (SQLException ex) {
            System.out.println("Connection failed (SQLException)");
        }

        System.out.println(connection);

        return connection;
    }

    

    public static Connection conn() throws ServletException
    {
        try {

            Class.forName("com.mysql.jdbc.GoogleDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error loading Google JDBC Driver", e);
        }
        try {

            String DBUrl = String.format("jdbc:google:mysql://%s/%s",
                    "training-project-lab:europe-west3:instance757", "lobby");
            Connection  DbConn = DriverManager.getConnection(DBUrl,"rovshan","rovshan");
            return DbConn;
        } catch(SQLException  ex) {
            throw new ServletException("SQL Error: " + ex.getMessage(), ex);
        }

    }
}