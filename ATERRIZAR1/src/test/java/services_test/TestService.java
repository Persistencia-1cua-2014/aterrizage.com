package services_test;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Service;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class TestService {

    Service service;
    Connection connection;
    PreparedStatement ps;

    @Before
    public void setUp() throws Exception {
        this.service = new Service("aterrizage_test");
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
        this.ps = this.connection.prepareStatement("INSERT INTO Usuario (nombreDeUSuario, email) VALUES (?,?)");
        this.ps.setString(1, "unNombreDeUsuario");
        this.ps.setString(2, "unEmail");
        this.ps.execute();
        this.ps.close();
        this.connection.close();
        Usuario user = new Usuario("nombre", "apellido", "unNombreDeUsuario", "email", new Date());
        boolean result = this.service.existeUsuario(user);
        Assert.assertTrue(result);

    }

    @Test
    public void testTestExistUserReturnsFalse() throws Exception {
        Usuario user = new Usuario("nombre", "apellido", "unNombreDeUsuario", "email", new Date());
        boolean result = this.service.existeUsuario(user);
        Assert.assertFalse(result);

    }

}
