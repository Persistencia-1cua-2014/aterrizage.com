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

	@Test
	public void testVueloByPrecio(){
		Searcher searcher = new Searcher();
		List<Vuelo> vuelos = searcher.getVuelosByPrecioASC();
		assertEquals(vuelo2.getId(), vuelos.get(0).getId());
	}

    @Test
    public void testVueloByEscala(){
        Searcher searcher = new Searcher();
        List<Vuelo> vuelos = searcher.getVuelosByEscalaASC();
        assertEquals(vuelo2.getId(), vuelos.get(0).getId());
    }

    @Test
    public void testVueloByDuracion(){
        Searcher searcher = new Searcher();
        List<Vuelo> vuelos = searcher.getVuelosByDuracionASC();
        assertEquals(vuelo2.getId(), vuelos.get(0).getId());
    }


}
