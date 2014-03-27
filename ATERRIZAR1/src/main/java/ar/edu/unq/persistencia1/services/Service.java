package ar.edu.unq.persistencia1.services;


import java.sql.Connection;
import java.sql.DriverManager;

public class Service {

    String database;

    public Service(String database) {
        this.database = database;
    }


    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/" + this.database + "?user=root&password=root");
    }

    public void emptyTable(String table_name) throws Exception{
        Connection connection = this.getConnection();
        connection.prepareStatement("DELETE FROM " + this.database + "." + table_name).execute();
    }
}
