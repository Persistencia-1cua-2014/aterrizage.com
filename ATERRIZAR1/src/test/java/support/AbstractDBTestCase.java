package support;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.enterprise.asientos.Business;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.usuarios.CreateUsuario;
import junit.framework.TestCase;
import support.services.aerolinea.SaveAerolinea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbstractDBTestCase extends TestCase{
	protected Vuelo vuelo;
	protected Vuelo vuelo2;
	protected Usuario usuario;
	protected Asiento asiento;
	protected Tramo tramo;
	protected ManejadorDeAsientos manejadorDeAsientos;
	protected List<Asiento> asientos;
	private Tramo tramo2;


	public void cleanDB(){
		SessionManager.runInSession(new EmptyTable("Asiento"));
		SessionManager.runInSession(new EmptyTable("Usuario"));
		SessionManager.runInSession(new EmptyTable("Tramo"));
		SessionManager.runInSession(new EmptyTable("Lugar"));
		SessionManager.runInSession(new EmptyTable("Vuelo"));
		SessionManager.runInSession(new EmptyTable("Aerolinea"));
	}

	public void initModels(){
		Business business = new Business();
		business.setFactorPrecio(10);
		this.asiento = new Asiento();
		asiento.setCategoria(business);

		Lugar origen = new Lugar("Argentina");
		Lugar destino = new Lugar("Brasil");
		Date salida = new Date();
		Date llegada = new Date();

		Aerolinea aerolinea = new Aerolinea(new ArrayList<Vuelo>());
		this.vuelo = new Vuelo();
		this.tramo = createTramo(vuelo, origen, destino, salida, llegada, 2000);
		this.vuelo2 = new Vuelo();
		this.tramo2 = createTramo(vuelo2, origen, destino, salida, llegada, 1000);

		aerolinea.getVueloList().add(vuelo);
		aerolinea.getVueloList().add(vuelo2);

		this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(),"12","");
		SessionManager.runInSession(new CreateUsuario(this.usuario));

		this.manejadorDeAsientos = new ManejadorDeAsientos();

		this.asientos = new ArrayList<Asiento>();
		for (int i = 0; i < 5; i++) {
			tramo.addAsiento(new Asiento(business));
		}
		tramo2.addAsiento(asiento);
		for (int i = 0; i < 5; i++) {
			tramo2.addAsiento(new Asiento(business));
		}
		tramo2.addAsiento(asiento);

		SessionManager.runInSession(new SaveAerolinea(aerolinea));
	}

	public Tramo createTramo(Vuelo vuelo, Lugar origen, Lugar destino, Date salida, Date llegada, int precio){
		Tramo tramo = new Tramo(origen, destino, salida, llegada);
		List<Tramo> tramos = new ArrayList<Tramo>();
		tramo.setPrecioBase(precio);
		vuelo.setTramos(tramos);
		vuelo.addTramo(tramo);
		return tramo;
	}
}
