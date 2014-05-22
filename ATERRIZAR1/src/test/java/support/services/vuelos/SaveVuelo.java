package support.services.vuelos;


import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import support.daos.DAOVuelo;

public class SaveVuelo implements Operation<Vuelo> {

	private Vuelo vuelo;

	public SaveVuelo(Vuelo vuelo){
		this.vuelo = vuelo;
	}

	@Override
	public Vuelo execute() {
		DAOVuelo dao = new DAOVuelo();
		dao.save(vuelo);
		return vuelo;
	}
}
