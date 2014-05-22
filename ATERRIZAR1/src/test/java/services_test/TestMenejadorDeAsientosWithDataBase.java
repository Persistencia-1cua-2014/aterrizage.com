package services_test;

import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.junit.Test;
import support.services.asientos.EstaReservado;
import support.services.asientos.ReservarAsiento;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestMenejadorDeAsientosWithDataBase extends TestMenejadorDeAsientos {

	@Test
	public void testReservarUnAsiento() throws AsientoYaReservado {
		SessionManager.runInSession(new ReservarAsiento(asiento, tramo, manejadorDeAsientos));

		assertTrue(SessionManager.runInSession(new EstaReservado(asiento)));
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
