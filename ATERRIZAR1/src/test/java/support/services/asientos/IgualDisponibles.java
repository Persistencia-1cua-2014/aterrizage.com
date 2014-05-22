package support.services.asientos;


import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class IgualDisponibles implements Operation<Boolean>{


	private List<Asiento> expected;
	private Tramo tramo;

	public IgualDisponibles(List<Asiento> expected, Tramo tramo){
		this.expected = expected;
		this.tramo = tramo;
	}

	@Override
	public Boolean execute() {
		List<Asiento> expectedDB = new ArrayList<Asiento>();

		for(Asiento a: expected)
			expectedDB.add((Asiento) SessionManager.getSession().get(Asiento.class, a.getId()));

		Tramo tramoDB = (Tramo) SessionManager.getSession().get(Tramo.class, tramo.getId());

		return expectedDB.equals(tramoDB.getAsientosDisponibles());
	}
}
