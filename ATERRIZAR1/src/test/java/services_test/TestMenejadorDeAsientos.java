package services_test;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.enterprise.asientos.Turista;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;
import ar.edu.unq.persistencia1.exceptions.UsuarioNoExiste;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TestMenejadorDeAsientos {
    private Usuario usuario;
    private Asiento asiento;
    private Tramo tramo;
    private ManejadorDeAsientos manejadorDeAsientos;

    @Before
    public void setUp(){
        Lugar destino = new Lugar("argentina");
        Lugar origen = new Lugar("china");
        this.tramo = new Tramo(origen, destino, new Date(), new Date());
        this.asiento = new Asiento(new Turista());
        this.usuario = new Usuario();
        this.manejadorDeAsientos = new ManejadorDeAsientos();
    }

    @Test(expected = AsientoYaReservado.class)
    public void noSePuedeReservarUnAsientoyaReservado() throws AsientoYaReservado {
        manejadorDeAsientos.reservarAsiento(usuario, asiento, tramo);
        manejadorDeAsientos.reservarAsiento(usuario, asiento, tramo);
    }
}
