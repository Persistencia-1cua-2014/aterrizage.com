package ar.edu.unq.persistencia1.services.vuelos;

import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class VuelosByAerolinea implements Operation<List<Vuelo>> {
    private Aerolinea aerolinea;

    public VuelosByAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    @Override
    public List<Vuelo> execute() {
        return aerolinea.getVueloList();
    }

}
