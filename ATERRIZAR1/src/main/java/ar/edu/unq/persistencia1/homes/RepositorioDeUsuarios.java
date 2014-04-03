package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.exceptions.UsuarioNoExiste;
import ar.edu.unq.persistencia1.exceptions.UsuarioYaExisteException;
import ar.edu.unq.persistencia1.exceptions.ValidacionException;
import ar.edu.unq.persistencia1.services.Service;

import java.sql.*;

public class RepositorioDeUsuarios extends Service {

    static String tableName = "Usuario";

    public RepositorioDeUsuarios(String databaseName) {
        super(databaseName);
    }

    public void guardarUsuario(Usuario usuario) throws UsuarioYaExisteException {
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

    public boolean existeUsuario(Usuario usuario) {
        Connection connection = this.getConnection();
        String userName = usuario.getNombreDeUsuario();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE nombreDeUsuario = ?");
            ps.setString(1, userName);
            ResultSet queryResult = ps.executeQuery();
            boolean result = queryResult.next();
            ps.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existeCodigo(String codigo) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE codigoDeValidacion = ?");
            ps.setString(1, codigo);
            ResultSet queryResult = ps.executeQuery();
            boolean result = queryResult.next();
            ps.close();
            connection.close();
            return result;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public void guardarCodigo(Usuario usuario, String codigo) throws Exception {
        Connection connection = this.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE Usuario SET codigoDeValidacion = " + codigo + " WHERE nombreDeUsuario = '" + usuario.getNombreDeUsuario() + "'");

        ps.execute();
        ps.close();
        connection.close();

    }

    public Usuario getUsuario(String nombreDeUsuario, String password) throws UsuarioNoExiste {
        Connection connection = this.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE nombreDeUsuario = '" + nombreDeUsuario + "' and password = '" + password + "'");
            ResultSet queryResult = ps.executeQuery();
            boolean result = queryResult.next();
            if (!result) {
                throw new UsuarioNoExiste();
            }
            Usuario user = this.buildUser(queryResult);
            ps.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Usuario buildUser(ResultSet queryResult) throws SQLException {
        return new Usuario(queryResult.getString("nombre"), queryResult.getString("apellido"), queryResult.getString("nombreDeUsuario"), queryResult.getString("email"), queryResult.getDate("birthday"), "");
    }

    public void validarCuenta(String codigo) throws ValidacionException {

        if (this.existeCodigo(codigo)) {
            this.forzarValidacion(codigo);

        } else {
            try {
            } catch (Exception e) {
                throw new ValidacionException();
            }
        }

    }

    public void forzarValidacion(String codigo) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Usuario SET verificado = 1 WHERE codigoDeValidacion = '" + codigo + "'");

            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public int chequearValidacion(String codigo) throws Exception {

        Connection connection = this.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE codigoDeValidacion = ?");
        ps.setString(1, codigo);
        ResultSet queryResult = ps.executeQuery();
        boolean result = queryResult.next(); // avanzar a la primer columna
        int resultado = queryResult.getInt("verificado");
        ps.close();
        connection.close();
        return resultado;

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
