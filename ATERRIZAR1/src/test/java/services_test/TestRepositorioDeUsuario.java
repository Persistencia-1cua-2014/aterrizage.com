package services_test;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.exceptions.UsuarioNoExiste;
import ar.edu.unq.persistencia1.exceptions.UsuarioYaExisteException;
import ar.edu.unq.persistencia1.homes.RepositorioDeUsuarios;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class TestRepositorioDeUsuario {

    RepositorioDeUsuarios service;
    Connection connection;
    PreparedStatement ps;

    @Before
    public void setUp() throws Exception {
        //Avoid log4j warning
        org.apache.log4j.BasicConfigurator.configure();

        this.service = new RepositorioDeUsuarios("aterrizage_test");
		this.service.emptyTable("Asiento");
        this.service.emptyTable("Usuario");
        this.connection = this.service.getConnection();

    }

    @After
    public void tearDown() throws Exception {
        this.service.emptyTable("Usuario");
        if (this.ps != null)
            this.ps.close();
        if (this.connection != null)
            this.connection.close();
    }

    @Test
    public void testTestExistUserReturnsTrue() throws Exception {
        this.connection = this.service.getConnection();
        String nombreDeUsuario = "unNombreDeUsuario";
        String email ="Laloooo";
        this.ps = this.connection.prepareStatement("INSERT INTO Usuario (nombreDeUsuario, email, verificado) VALUES (?,?, ?)");
        this.ps.setString(1, nombreDeUsuario);
        this.ps.setString(2, email);
		this.ps.setBoolean(3, false);
        this.ps.execute();
        this.ps.close();
        this.connection.close();
        Usuario usuario = new Usuario("Lalocura", "DeLalo", nombreDeUsuario, email, new Date(),"12","");
        boolean result = this.service.existeUsuario(usuario);
        Assert.assertTrue(result);

    }

    @Test
    public void testTestExistUserReturnsFalse() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12","");
        boolean result = this.service.existeUsuario(usuario);
        Assert.assertFalse(result);

    }

    @Test
    public void testGuardarUsuario() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12","");
        this.service.guardarUsuario(usuario);
        Assert.assertTrue(this.service.existeUsuario(usuario));

    }

    @Test(expected = UsuarioYaExisteException.class)
    public void testLanzaExcepcionConUsuarioExistente() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12","");
        this.service.guardarUsuario(usuario);
        this.service.guardarUsuario(usuario);


    }

    @Test
    public void testSetearCodigoUsuario() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12","");
        RepositorioDeUsuarios repo = new RepositorioDeUsuarios("aterrizage_test");
        this.service.guardarUsuario(usuario);
        repo.guardarCodigo(usuario, "123");
        Assert.assertTrue(repo.existeCodigo("123", usuario.getNombreDeUsuario()));
    }
    
    @Test
    public void testValidarCuentaInexistente() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "","");
    	RepositorioDeUsuarios repo = new RepositorioDeUsuarios("aterrizage_test");
    	this.service.guardarUsuario(usuario);
    	repo.guardarCodigo(usuario, "123");
    	Assert.assertFalse(repo.chequearValidacion("123", usuario.getNombreDeUsuario()));

    }
    @Test
    public void testValidarCuentaExistente() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "","");
    	RepositorioDeUsuarios repo = new RepositorioDeUsuarios("aterrizage_test");
    	this.service.guardarUsuario(usuario);
    	repo.guardarCodigo(usuario, "123");
    	this.service.validarCuenta("123", usuario.getNombreDeUsuario());
		Assert.assertTrue(repo.chequearValidacion("123", usuario.getNombreDeUsuario()));
    }

    @Test
    public void testGetUsuario() throws Exception {
        Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "","");
        this.service.guardarUsuario(usuario);
        this.setPassword(usuario, "123456");
        String password = "123456";
        Usuario user = this.service.getUsuario(usuario.getNombreDeUsuario(), password);
        boolean sameUser = user.getNombreDeUsuario().equals(usuario.getNombreDeUsuario());
        Assert.assertTrue(sameUser);
    }

    @Test(expected = UsuarioNoExiste.class)
    public void testGetUsuarioRaiseUsuarioNoExiteException() throws Exception{
        Usuario user = this.service.getUsuario("aUserName", "aPassword");
    }

    /**
     * Remove this method after implement cambiarPassword in Sistema class.
     * @param usuario
     */
    public void setPassword(Usuario usuario, String password) throws Exception {
        Connection connection = this.service.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE Usuario SET password = " + password + " WHERE nombreDeUsuario = '" + usuario.getNombreDeUsuario() + "'");
        ps.execute();
        ps.close();
        connection.close();
    }
    
    
    @Test
    public void testCambiarPassword() throws Exception { 
       Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12","");
       RepositorioDeUsuarios repo = new RepositorioDeUsuarios("aterrizage_test");
       this.service.guardarUsuario(usuario);
       repo.cambiarPassword(usuario.getNombreDeUsuario(), "123");
       Assert.assertTrue(repo.existePassword("123",usuario));
       
       
    }


}
