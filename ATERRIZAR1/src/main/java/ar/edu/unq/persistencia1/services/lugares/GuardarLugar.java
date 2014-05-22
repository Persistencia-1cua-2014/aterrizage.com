package ar.edu.unq.persistencia1.services.lugares;


import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.homes.DAOLugares;
import ar.edu.unq.persistencia1.services.Operation;

public class GuardarLugar implements Operation<Lugar>{

	private Lugar lugar;

	public GuardarLugar(Lugar lugar){
		this.lugar = lugar;
	}

	@Override
	public Lugar execute() {
		DAOLugares daoLugares = new DAOLugares();
		daoLugares.save(lugar);
		return lugar;
	}
}
