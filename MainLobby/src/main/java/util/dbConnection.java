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

    public static Connection getconn() throws ServletException {
        String url = "jdbc:mysql://google/lobby?cloudSqlInstance=training-project-lab:europe-west3:instance757&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=rovshan&password=rovshan&useSSL=false" ;
        System.out.println(url);
        try {

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error loading Google JDBC Driver", e);
        }
        try {
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException e) {
            throw new ServletException("SQL Error: " + e.getMessage(), e);
        }
    }

    public static Connection conn() throws ServletException
    {
        try {

        	 Class.forName("com.mysql.jdbc.Driver");
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