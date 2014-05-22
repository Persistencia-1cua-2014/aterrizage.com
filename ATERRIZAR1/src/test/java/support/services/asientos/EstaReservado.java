package support.services.asientos;


import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

public class EstaReservado implements Operation<Boolean> {

	private Asiento asiento;

	public EstaReservado(Asiento asiento) {
		this.asiento = asiento;
	}


	@Override
	public Boolean execute() {
		Asiento a = (Asiento) SessionManager.getSession().get(Asiento.class, asiento.getId());
		return a.estaReservado();
	}

}
