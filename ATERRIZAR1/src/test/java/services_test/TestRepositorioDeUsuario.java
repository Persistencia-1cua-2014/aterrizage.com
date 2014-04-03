package services_test;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.exceptions.UsuarioYaExisteException;
import ar.edu.unq.persistencia1.homes.RepositorioDeUsuarios;
import junit.framework.Assert;
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
        this.service = new RepositorioDeUsuarios("aterrizage_test");
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
        this.ps = this.connection.prepareStatement("INSERT INTO Usuario (nombreDeUsuario, email) VALUES (?,?)");
        this.ps.setString(1, nombreDeUsuario);
        this.ps.setString(2, email);
        this.ps.execute();
        this.ps.close();
        this.connection.close();
        Usuario usuario = new Usuario("Lalocura", "DeLalo", nombreDeUsuario, email, new Date(),"12");
        boolean result = this.service.existeUsuario(usuario);
        Assert.assertTrue(result);

    }

    @Test
    public void testTestExistUserReturnsFalse() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12");
        boolean result = this.service.existeUsuario(usuario);
        Assert.assertFalse(result);

    }

    @Test
    public void testGuardarUsuario() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12");
        this.service.guardarUsuario(usuario);
        Assert.assertTrue(this.service.existeUsuario(usuario));

    }

    @Test(expected = UsuarioYaExisteException.class)
    public void testLanzaExcepcionConUsuarioExistente() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12");
        this.service.guardarUsuario(usuario);
        this.service.guardarUsuario(usuario);


    }

    @Test
    public void testSetearCodigoUsuario() throws Exception {
    	Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12");
        RepositorioDeUsuarios repo = new RepositorioDeUsuarios("aterrizage_test");
        this.service.guardarUsuario(usuario);
        repo.guardarCodigo(usuario, "123");
        Assert.assertTrue(repo.existeCodigo("123"));


    }
    
    
    @Test
    public void testCambiarPassword() throws Exception { 
       Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12");
       RepositorioDeUsuarios repo = new RepositorioDeUsuarios("aterrizage_test");
       this.service.guardarUsuario(usuario);
       repo.cambiarPassword("123");
       Assert.assertTrue(repo.existePassword("123",usuario));
       
       
    }


}
