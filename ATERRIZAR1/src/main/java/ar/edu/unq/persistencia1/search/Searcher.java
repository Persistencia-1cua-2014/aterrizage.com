package ar.edu.unq.persistencia1.search;

import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.vuelos.VuelosByEscalas;
import ar.edu.unq.persistencia1.services.vuelos.VuelosByPrecio;
import ar.edu.unq.persistencia1.services.vuelos.VuelosDisponibles;

import java.util.List;

public class Searcher {

	public List<Vuelo> getVuelosDisponibles() {
		return SessionManager.runInSession(new VuelosDisponibles());
	}

	public List<Vuelo> getVuelosByPrecioASC() {
		return SessionManager.runInSession(new VuelosByPrecio());
	}

    public List<Vuelo> getVuelosByEscalaASC() {
        return SessionManager.runInSession(new VuelosByEscalas());
    }
}
