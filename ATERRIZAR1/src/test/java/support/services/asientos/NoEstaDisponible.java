package support.services.asientos;


import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

public class NoEstaDisponible implements Operation<Boolean>{

	private ManejadorDeAsientos manejadorDeAsientos;
	private Asiento asiento;
	private Tramo tramo;

	public NoEstaDisponible(Tramo tramo, Asiento asiento, ManejadorDeAsientos manejadorDeAsientos){
		this.tramo = tramo;
		this.asiento = asiento;
		this.manejadorDeAsientos = manejadorDeAsientos;
	}


	@Override
	public Boolean execute() {

		Tramo tramoDB = (Tramo) SessionManager.getSession().get(Tramo.class, tramo.getId());
		Asiento asientoDb = (Asiento) SessionManager.getSession().get(Asiento.class, asiento.getId());
		return manejadorDeAsientos.asientosDisponibles(tramoDB).contains(asientoDb);
	}
}
