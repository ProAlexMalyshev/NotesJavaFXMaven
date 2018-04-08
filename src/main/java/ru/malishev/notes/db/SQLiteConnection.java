package ru.malishev.notes.db;

import java.sql.*;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */
public class SQLiteConnection {

    private static Connection connection = null;
    public static Connection getConnection (){
        try {
            Class.forName("org.sqlite.JDBC");
            if(connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite::resource:dbnote/note.db");
            }
            System.out.println("Connect");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
