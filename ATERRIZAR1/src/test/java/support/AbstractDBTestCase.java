package support;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.enterprise.asientos.Business;
import ar.edu.unq.persistencia1.enterprise.asientos.Turista;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.usuarios.CreateUsuario;
import junit.framework.TestCase;
import support.services.aerolinea.SaveAerolinea;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AbstractDBTestCase extends TestCase{
    protected Aerolinea aerolinea;
	protected Vuelo vuelo;
	protected Vuelo vuelo2;
	protected Usuario usuario;
	protected Asiento asiento;
	protected Tramo tramo;
	protected ManejadorDeAsientos manejadorDeAsientos;
	protected List<Asiento> asientos;
	private Tramo tramo2;
    private Vuelo vuelo3;
    protected  Turista turista;


    public void cleanDB(){
		SessionManager.runInSession(new EmptyTable("Asiento"));
		SessionManager.runInSession(new EmptyTable("Usuario"));
		SessionManager.runInSession(new EmptyTable("Tramo"));
		SessionManager.runInSession(new EmptyTable("Lugar"));
		SessionManager.runInSession(new EmptyTable("Vuelo"));
		SessionManager.runInSession(new EmptyTable("Aerolinea"));
	}

	public void initModels(){
        this.turista = new Turista();
        turista.setFactorPrecio(1);
		Business business = new Business();
		business.setFactorPrecio(10);
		this.asiento = new Asiento();
		asiento.setCategoria(business);

        Date today = new Date();
		Lugar origen = new Lugar("Argentina");
		Lugar destino = new Lugar("Brasil");
        Lugar origen2 = destino;
        Lugar destino2 = new Lugar("Portugal");


		Date salida = today;
		Date llegada = plusDays(today, 1);

        Date salida2 = llegada;
        Date llegada2 = plusDays(llegada, 1);

		this.aerolinea = new Aerolinea(new ArrayList<Vuelo>());
        Aerolinea aerolinea2 = new Aerolinea(new ArrayList<Vuelo>());
		this.vuelo = new Vuelo();
		this.tramo = createTramo(vuelo, origen, destino, salida, llegada, 2000);
		this.tramo = createTramo(vuelo, destino, destino2, llegada, llegada2, 3000);

		this.vuelo2 = new Vuelo();
		this.tramo2 = createTramo(vuelo2, origen, destino, salida, llegada, 1000);

        this.vuelo3 = new Vuelo();
        createTramo(vuelo3, origen, destino, salida, llegada, 1000);
        aerolinea2.getVueloList().add(vuelo3);

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
			tramo2.addAsiento(new Asiento(turista));
		}
		tramo2.addAsiento(asiento);

		SessionManager.runInSession(new SaveAerolinea(aerolinea));
	}

	public Tramo createTramo(Vuelo vuelo, Lugar origen, Lugar destino, Date salida, Date llegada, int precio){
		Tramo tramo = new Tramo(origen, destino, salida, llegada);
		List<Tramo> tramos = new ArrayList<Tramo>();
		tramo.setPrecioBase(precio);
        for(Tramo t: tramos)
            vuelo.addTramo(t);
		vuelo.addTramo(tramo);
		return tramo;
	}

    public Date plusDays(Date d, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
