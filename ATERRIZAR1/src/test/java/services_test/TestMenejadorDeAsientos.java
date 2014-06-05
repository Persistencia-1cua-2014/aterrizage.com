package services_test;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.enterprise.asientos.Business;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.usuarios.CreateUsuario;
import org.junit.Before;
import org.junit.Test;
import support.EmptyTable;
import support.services.aerolinea.SaveAerolinea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestMenejadorDeAsientos {
	protected Usuario usuario;
	protected Asiento asiento;
	protected Tramo tramo;
	protected ManejadorDeAsientos manejadorDeAsientos;
	protected List<Asiento> asientos;

	@Before
	public void setUp() {
		SessionManager.runInSession(new EmptyTable("Asiento"));
		SessionManager.runInSession(new EmptyTable("Usuario"));
		SessionManager.runInSession(new EmptyTable("Tramo"));
		SessionManager.runInSession(new EmptyTable("Lugar"));
		SessionManager.runInSession(new EmptyTable("Vuelo"));
		SessionManager.runInSession(new EmptyTable("Aerolinea"));


		Business business = new Business();
		business.setFactorPrecio(10);
		this.asiento = new Asiento();
		asiento.setCategoria(business);

		Lugar origen = new Lugar("Argentina");
		Lugar destino = new Lugar("Brasil");
		Date salida = new Date();
		Date llegada = new Date();

		this.tramo = new Tramo(origen, destino, salida, llegada);
		List<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(tramo);


		Aerolinea aerolinea = new Aerolinea(new ArrayList<Vuelo>());
		Vuelo vuelo = new Vuelo(tramos);
		aerolinea.getVueloList().add(vuelo);

		this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12","");
		SessionManager.runInSession(new CreateUsuario(this.usuario));

		this.manejadorDeAsientos = new ManejadorDeAsientos();

		this.asientos = new ArrayList<Asiento>();
		for (int i = 0; i < 5; i++) {
			tramo.addAsiento(new Asiento(business));
		}
		tramo.addAsiento(asiento);

		SessionManager.runInSession(new SaveAerolinea(aerolinea));

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
