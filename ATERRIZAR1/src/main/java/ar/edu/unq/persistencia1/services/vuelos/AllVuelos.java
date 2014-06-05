package ar.edu.unq.persistencia1.services.vuelos;


import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.List;

public class AllVuelos implements Operation<List<Vuelo>>{
	@Override
	public List<Vuelo> execute() {
		return SessionManager.getSession().createCriteria(Vuelo.class).list();
	}
}
