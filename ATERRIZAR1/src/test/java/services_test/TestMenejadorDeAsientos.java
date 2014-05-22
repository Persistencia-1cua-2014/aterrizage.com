package services_test;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.enterprise.asientos.Turista;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;

import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.lugares.GuardarLugar;
import ar.edu.unq.persistencia1.services.tramos.GuardarTramo;
import org.junit.Before;
import org.junit.Test;
import support.EmptyTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestMenejadorDeAsientos {
	private Usuario usuario;
	private Asiento asiento;
	private Tramo tramo;
	private ManejadorDeAsientos manejadorDeAsientos;
	private List<Asiento> asientos;

	@Before
	public void setUp() {
		SessionManager.runInSession(new EmptyTable("Tramo"));
		SessionManager.runInSession(new EmptyTable("Lugar"));
		Lugar destino = new Lugar("argentina");
		Lugar origen = new Lugar("china");

		SessionManager.runInSession(new GuardarLugar(origen));
		SessionManager.runInSession(new GuardarLugar(destino));


		this.tramo = new Tramo(origen, destino, new Date(), new Date());
		SessionManager.runInSession(new GuardarTramo(this.tramo));
		this.usuario = new Usuario();
		this.manejadorDeAsientos = new ManejadorDeAsientos();
		this.asientos = new ArrayList<Asiento>();
		for (int i = 0; i < 5; i++) {
			tramo.addAsiento(new Asiento(new Turista()));
		}
		this.asiento = tramo.getAsientos().get(0);
	}

	@Test
	public void testReservarUnAsiento() throws AsientoYaReservado {
		manejadorDeAsientos.reservarAsiento(usuario, asiento, tramo);
		assertTrue(asiento.estaReservado());
	}

	@Test
	public void testReservarUnAsientoTieneUsuario() throws AsientoYaReservado {
		manejadorDeAsientos.reservarAsiento(usuario, asiento, tramo);
		assertNotNull(asiento.getUsuario());
	}


	@Test(expected = AsientoYaReservado.class)
	public void noSePuedeReservarUnAsientoyaReservado() throws AsientoYaReservado {
		List<Asiento> aReservar = new ArrayList<Asiento>();
		aReservar.add(asiento);
		aReservar.add(tramo.getAsientos().get(0));
		aReservar.add(tramo.getAsientos().get(1));
		aReservar.add(tramo.getAsientos().get(2));
		manejadorDeAsientos.reservarAsiento(usuario, asiento, tramo);
		manejadorDeAsientos.reservarAsientos(usuario, aReservar, tramo);
	}

	@Test
	public void testReservarUnAsientosTieneUsuario() throws AsientoYaReservado {
		List<Asiento> aReservar = new ArrayList<Asiento>();
		aReservar.add(tramo.getAsientos().get(0));
		aReservar.add(tramo.getAsientos().get(1));
		aReservar.add(tramo.getAsientos().get(2));
		manejadorDeAsientos.reservarAsientos(usuario, aReservar, tramo);
		for (Asiento reservado : aReservar)
			assertTrue(reservado.estaReservado());
	}

	@Test
	public void testAsientosDisponiblesNoContieneElReservado() {
		tramo.reservar(asiento, usuario);
		assertFalse(manejadorDeAsientos.asientosDisponibles(tramo).contains(asiento));
	}

	@Test
	public void testAsientosDisponiblesContieneLosDiponibles() {
		List<Asiento> noReservados = new ArrayList<Asiento>(tramo.getAsientos());
		tramo.reservar(asiento, usuario);
		noReservados.remove(asiento);
		assertEquals(noReservados, tramo.getAsientosDisponibles());
	}
}
