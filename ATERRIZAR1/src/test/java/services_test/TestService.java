package services_test;


import ar.edu.unq.persistencia1.services.Service;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestService {

    Service service;

    @Before
    public void setUp() throws Exception {
        this.service = new Service("aterrizage_test");
        this.service.emptyTable("Usuario");
    }

    @After
    public void tearDown() throws Exception {
        this.service.emptyTable("Usuario");
    }

    @Test
    public void testInsert() throws Exception  {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = this.service.getConnection();
            ps = conn.prepareStatement("INSERT INTO Usuario (nombreDeUSuario, email) VALUES (?,?)");
            ps.setString(1, "unNombreDeUsuario");
            ps.setString(2, "unEmail");
            ps.execute();

            Assert.assertEquals("Se espera que haya podido insertar 1 registro", 1, ps.getUpdateCount());
            ps.close();
        }finally{
            if(ps != null)
                ps.close();
            if(conn != null)
                conn.close();
        }
    }



}
