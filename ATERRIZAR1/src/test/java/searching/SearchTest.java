package searching;


import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.search.Searcher;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.vuelos.AllVuelos;
import org.junit.Before;
import org.junit.Test;
import support.AbstractDBTestCase;

import java.util.List;

public class SearchTest extends AbstractDBTestCase{

	@Before
	public void setUp(){
		cleanDB();
		initModels();
	}

	@Test
	public void testGetVuelosDisponibles(){
		Searcher searcher = new Searcher();
		List<Vuelo> vuelos = searcher.getVuelosDisponibles();
		List<Vuelo> result = SessionManager.runInSession(new AllVuelos());

		assertEquals(result, vuelos);
	}
}
