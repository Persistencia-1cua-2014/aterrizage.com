package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.exceptions.UsuarioNoExiste;
import ar.edu.unq.persistencia1.exceptions.UsuarioYaExisteException;
import ar.edu.unq.persistencia1.exceptions.ValidacionException;
import ar.edu.unq.persistencia1.services.Service;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.usuarios.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioDeUsuarios extends Service {

    static String tableName = "Usuario";

    public RepositorioDeUsuarios(String databaseName) {
        super(databaseName);
    }

    public void guardarUsuario(Usuario usuario) throws UsuarioYaExisteException {
        if (this.existeUsuario(usuario)) {
            throw new UsuarioYaExisteException();
        }
        this.forzarUsuario(usuario);
    }

    public void forzarUsuario(Usuario usuario) {
        SessionManager.runInSession(new CreateUsuario(usuario));
    }

    public boolean existeUsuario(Usuario usuario) {
        return SessionManager.runInSession(new ExisteUsuario(usuario));
    }

    public boolean existeCodigo(String codigo, String nombreDeUsuario) {
        return SessionManager.runInSession(new ExisteCodigo(codigo,nombreDeUsuario));
    }


    public void guardarCodigo(Usuario usuario, String codigo) throws Exception {
        SessionManager.runInSession(new GuardarCodigo(usuario, codigo));

    }

    public Usuario getUsuario(String nombreDeUsuario, String password) throws UsuarioNoExiste {
        Usuario u = SessionManager.runInSession(new GetUsuario(nombreDeUsuario, password));
		if(u == null)
			throw new UsuarioNoExiste();
		return u;
    }

    private Usuario buildUser(ResultSet queryResult) throws SQLException {
        return new Usuario(queryResult.getString("nombre"), queryResult.getString("apellido"), queryResult.getString("nombreDeUsuario"), queryResult.getString("email"), queryResult.getDate("birthday"), "", "");
    }

    public void validarCuenta(String codigo, String nombreDeUsuario) throws ValidacionException {

        if (this.existeCodigo(codigo, nombreDeUsuario)) {
            this.forzarValidacion(codigo, nombreDeUsuario);
        } else {
            throw new ValidacionException();
        }
    }

    public void forzarValidacion(String codigo, String nombreDeUsuario) {
		SessionManager.runInSession(new ValidarCodigo(codigo, nombreDeUsuario));

    }

    public boolean chequearValidacion(String codigo, String nombreDeUsuario) throws Exception {

        return SessionManager.runInSession(new CheckearValidacion(codigo, nombreDeUsuario));

    }


    public void cambiarPassword(String userName, String nuevaPass) {
        SessionManager.runInSession(new ChangePassword(userName, nuevaPass));

    }

    public boolean existeUsuarioConPass(String userName, String pass) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean existePassword(String pass, Usuario usuario) throws Exception {
        Connection connection = this.getConnection();
        String userName = usuario.getNombreDeUsuario();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE password = ? and nombreDeUsuario = ? ");
        ps.setString(1, pass);
        ps.setString(2, userName);
        ResultSet queryResult = ps.executeQuery();
        boolean result = queryResult.next();
        ps.close();
        connection.close();
        return result;
    }


}
