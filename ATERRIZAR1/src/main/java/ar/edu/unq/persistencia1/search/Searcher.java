package ar.edu.unq.persistencia1.search;

import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.vuelos.*;

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

    public List<Vuelo> getVuelosByDuracionASC(){
        return SessionManager.runInSession(new VuelosByDuracion());
    }

    public List<Vuelo> getVuelosByAeroline(Aerolinea aerolinea) {
        return SessionManager.runInSession(new VuelosByAerolinea(aerolinea));
    }
}
