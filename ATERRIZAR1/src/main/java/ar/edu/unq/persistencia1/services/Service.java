package ar.edu.unq.persistencia1.services;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Service {

    String database;

    public Service(String database) {
        this.database = database;
    }


    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/" + this.database + "?user=root&password=root");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void emptyTable(String table_name) throws Exception{
        Connection connection = this.getConnection();
        connection.prepareStatement("DELETE FROM " + this.database + "." + table_name).execute();
    }
}
