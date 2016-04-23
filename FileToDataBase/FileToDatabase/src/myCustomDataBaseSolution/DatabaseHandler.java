/**
 * 
 */
package myCustomDataBaseSolution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Cody Soultz
 *
 */
public class DatabaseHandler {
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String dbAddress = "jdbc:mysql://localhost:3306/";
    private String userPass = "?user=root&password=";
    private String dbName = "TIGER";
    private String userName = "root";
    private String password = "";

    private PreparedStatement statement;
    private ResultSet result;
    private Connection con;

    public DatabaseHandler() {

        try {
        	createDatabase();
        	Class.forName(jdbcDriver);
            con = DriverManager.getConnection(dbAddress + dbName, userName, password);
        } 

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (SQLException e) {
            createDatabase();
        }
    }

    private void createDatabase() {
        try {
        Class.forName(jdbcDriver);
        con = DriverManager.getConnection(dbAddress + userPass);
        Statement s = con.createStatement();
        //int myResult = s.executeUpdate("CREATE DATABASE " + dbName);
        int myResult = statement.executeUpdate("CREATE DATABASE IF NOT EXISTS TIGER;");
        } 
        catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }