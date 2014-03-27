package ar.edu.unq.persistencia1.services;


import ar.edu.unq.persistencia1.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public boolean existeUsuario(Usuario usuario) throws Exception {
        Connection connection = this.getConnection();
        String userName = usuario.getNombreDeUsuario();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE nombreDeUsuario = ?");
        ps.setString(1, userName);
        ResultSet queryResult = ps.executeQuery();
        boolean result = queryResult.next();
        ps.close();
        connection.close();
        return result;
    }
}
