package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.exceptions.UsuarioYaExisteException;
import ar.edu.unq.persistencia1.services.Service;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositorioDeUsuarios extends Service {

    static String tableName = "Usuario";

    public RepositorioDeUsuarios(String databaseName) {
        super(databaseName);
    }

    public void guardarUsuario(Usuario usuario) throws Exception {
        if (this.existeUsuario(usuario)) {
            throw new UsuarioYaExisteException();

        } else {
            try {
                this.forzarUsuario(usuario);
            } catch (Exception e) {
                throw new UsuarioYaExisteException();
            }
        }

    }

    public void forzarUsuario(Usuario usuario) throws Exception {

        Connection connection = this.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Usuario (nombre, apellido, nombreDeUsuario, email, birthday) VALUES (?,?,?,?,?)");
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getNombreDeUsuario());
        ps.setString(4, usuario.getEmail());

        Date sqlBirthday = new Date(usuario.getBirthday().getTime());
        ps.setDate(5, sqlBirthday);
        ps.execute();
        ps.close();
        connection.close();
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

    public boolean existeCodigo(String codigo) throws Exception {
        Connection connection = this.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE codigoDeValidacion = ?");
        ps.setString(1, codigo);
        ResultSet queryResult = ps.executeQuery();
        boolean result = queryResult.next();
        ps.close();
        connection.close();
        return result;
    }


    public void guardarCodigo(Usuario usuario, String codigo) throws Exception {
        Connection connection = this.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE Usuario SET codigoDeValidacion = " + codigo + " WHERE nombreDeUsuario = '" + usuario.getNombreDeUsuario() + "'");

        ps.execute();
        ps.close();
        connection.close();

    }

	public void cambiarPassword(String nuevaPass) throws Exception {
		Connection connection = this.getConnection();
		PreparedStatement ps = connection.prepareStatement("UPDATE Usuario SET password = " + nuevaPass + "");
		ps.execute();
        ps.close();
        connection.close();
		
	}

	public boolean existeUsuarioConPass(String userName, String pass) throws Exception {
		Connection connection = this.getConnection();
        //String userName = usuario.getNombreDeUsuario();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE nombreDeUsuario = ? and password= ? ");
        ps.setString(1, userName);
        ps.setString(2, pass);
        ResultSet queryResult = ps.executeQuery();
        boolean result = queryResult.next();
        ps.close();
        connection.close();
        return result;
		
	}
	
	
	
	public boolean existePassword(String pass) throws Exception {
	        Connection connection = this.getConnection();
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE password = ?");
	        ps.setString(1, pass);
	        ResultSet queryResult = ps.executeQuery();
	        boolean result = queryResult.next();
	        ps.close();
	        connection.close();
	        return result;
	    }
    
    

}
