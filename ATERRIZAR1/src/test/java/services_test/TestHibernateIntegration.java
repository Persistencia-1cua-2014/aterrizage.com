package services_test;


import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.enterprise.asientos.Business;
import ar.edu.unq.persistencia1.services.SessionManager;
import junit.framework.TestCase;
import support.EmptyTable;
import support.services.aerolinea.SaveAerolinea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestHibernateIntegration extends TestCase {

	public void setUp(){
		SessionManager.runInSession(new EmptyTable("Asiento"));


		SessionManager.runInSession(new EmptyTable("Tramo"));
		SessionManager.runInSession(new EmptyTable("Lugar"));

		SessionManager.runInSession(new EmptyTable("Vuelo"));
		SessionManager.runInSession(new EmptyTable("Aerolinea"));
	}


	public void testSaveAerolinea(){
		Aerolinea aerolinea = new Aerolinea(new ArrayList<Vuelo>());
		SessionManager.runInSession(new SaveAerolinea(aerolinea));
		assertNotNull(aerolinea.getIdAerolinea());
	}

	public void testSaveVuelo(){
		Aerolinea aerolinea = new Aerolinea(new ArrayList<Vuelo>());
		Vuelo vuelo = new Vuelo(new ArrayList<Tramo>());
		aerolinea.getVueloList().add(vuelo);
		SessionManager.runInSession(new SaveAerolinea(aerolinea));
		assertNotNull(vuelo.getId());
	}

	public void testSaveTramo(){
		Lugar origen = new Lugar("Argentina");
		Lugar destino = new Lugar("Brasil");
		Date salida = new Date();
		Date llegada = new Date();

		Tramo t1 = new Tramo(origen, destino, salida, llegada);
		t1.setPrecioBase(2000);
		List<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);

		Aerolinea aerolinea = new Aerolinea(new ArrayList<Vuelo>());
		Vuelo vuelo = new Vuelo(tramos);
		aerolinea.getVueloList().add(vuelo);
		SessionManager.runInSession(new SaveAerolinea(aerolinea));
		assertNotNull(t1.getId());
	}

	public void testSaveAsiento(){

		Business business = new Business();
		business.setFactorPrecio(10);
		Asiento asiento = new Asiento();
		asiento.setCategoria(business);

		Lugar origen = new Lugar("Argentina");
		Lugar destino = new Lugar("Brasil");
		Date salida = new Date();
		Date llegada = new Date();

		Tramo t1 = new Tramo(origen, destino, salida, llegada);
		t1.setPrecioBase(2000);
		t1.addAsiento(asiento);
		List<Tramo> tramos = new ArrayList<Tramo>();
		tramos.add(t1);


		Aerolinea aerolinea = new Aerolinea(new ArrayList<Vuelo>());
		Vuelo vuelo = new Vuelo(tramos);
		aerolinea.getVueloList().add(vuelo);
		SessionManager.runInSession(new SaveAerolinea(aerolinea));
		assertNotNull(asiento.getId());
	}

}
