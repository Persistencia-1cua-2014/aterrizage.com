package support.services.asientos;

import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ChequearAsientosReservados implements Operation<Boolean> {
	private List<Asiento> reservadas = new ArrayList<Asiento>();

	public ChequearAsientosReservados(List<Asiento> aReservar){

		this.reservadas= aReservar;
	}


	@Override
	public Boolean execute() {
		List<Asiento> asientos =new ArrayList<Asiento>();
		for(Asiento a: reservadas){
			asientos.add((Asiento) SessionManager.getSession().get(Asiento.class, a.getId()));
		}
		Boolean todosReservados = true;
		for (Asiento a: asientos){
			todosReservados = todosReservados && a.estaReservado();

		}



		return todosReservados;
	}
}
