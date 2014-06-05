package services_test;

import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.exceptions.AsientoYaReservado;
import org.junit.Before;
import org.junit.Test;
import support.AbstractDBTestCase;
import java.util.ArrayList;
import java.util.List;

public class TestMenejadorDeAsientos extends AbstractDBTestCase {


	@Before
	public void setUp() {
		cleanDB();
		initModels();
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
