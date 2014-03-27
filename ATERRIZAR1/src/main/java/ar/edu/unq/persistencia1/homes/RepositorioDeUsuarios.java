package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositorioDeUsuarios extends Service{

    static String databaseName = "Usuario";

    public RepositorioDeUsuarios() {
        super(databaseName);
    }

    public void guardarUsuario(Usuario usuario){

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
