package services_test;

import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.junit.Test;
import support.services.asientos.*;

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
		SessionManager.runInSession(new ReservarAsiento(asiento, tramo, manejadorDeAsientos));
		int asientoID = asiento.getId();
		assertNotNull(SessionManager.runInSession(new GetUsuario(asientoID)));
	}


	@Test
	public void noSePuedeReservarUnAsientoyaReservado() throws AsientoYaReservado {
		List<Asiento> aReservar = new ArrayList<Asiento>();
		aReservar.add(asiento);
		aReservar.add(tramo.getAsientos().get(0));
		aReservar.add(tramo.getAsientos().get(1));
		aReservar.add(tramo.getAsientos().get(2));

		SessionManager.runInSession(new ReservarAsientoToUser(usuario, asiento, tramo, manejadorDeAsientos));
		try {
			SessionManager.runInSession(new ReservarAsientos(usuario, aReservar, tramo, manejadorDeAsientos));
			fail();
		} catch (RuntimeException e) {
			assertEquals(e.getCause().getClass(), AsientoYaReservado.class);
		}

	}

	@Test
	public void testReservarUnAsientosTieneUsuario() throws AsientoYaReservado {
		List<Asiento> aReservar = new ArrayList<Asiento>();
		aReservar.add(tramo.getAsientos().get(0));
		aReservar.add(tramo.getAsientos().get(1));
		aReservar.add(tramo.getAsientos().get(2));
		SessionManager.runInSession(new ReservarAsientos(usuario, aReservar, tramo, manejadorDeAsientos));
		SessionManager.runInSession(new ChequearAsientosReservados(aReservar));

	}

	@Test
	public void testAsientosDisponiblesNoContieneElReservado() {
		SessionManager.runInSession(new ReservarAsientoToUser(usuario, asiento, tramo, manejadorDeAsientos));
		assertFalse(SessionManager.runInSession(new NoEstaDisponible(tramo, asiento, manejadorDeAsientos)));
	}

	@Test
	public void testAsientosDisponiblesContieneLosDiponibles() {
		List<Asiento> noReservados = new ArrayList<Asiento>(tramo.getAsientos());
		tramo.reservar(asiento, usuario);
		noReservados.remove(asiento);
		assertEquals(noReservados, tramo.getAsientosDisponibles());
	}
}
