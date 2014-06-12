package ar.edu.unq.persistencia1.services.vuelos;

import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.List;


public class VuelosByEscalas implements Operation<List<Vuelo>> {

    @Override
    public List<Vuelo> execute() {
        return (List<Vuelo>)SessionManager.getSession().createQuery("from Vuelo order by tramos.size").list();
    }

}
