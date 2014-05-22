package support.services.asientos;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.Date;

public class ReservarAsiento implements Operation<Asiento> {
	private ManejadorDeAsientos manejador;
	private Asiento asiento;
	private Tramo tramo;

	public ReservarAsiento(Asiento a, Tramo t, ManejadorDeAsientos manejador){
		this.asiento = a;
		this.tramo = t;
		this.manejador = manejador;
	}


	@Override
	public Asiento execute() {
		Asiento a = (Asiento) SessionManager.getSession().get(Asiento.class, asiento.getId());
		Usuario u = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
		Tramo t = (Tramo) SessionManager.getSession().get(Tramo.class, tramo.getId());

		manejador.reservarAsiento(u, a, t);
		return a;
	}
}
