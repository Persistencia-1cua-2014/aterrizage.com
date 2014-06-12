package ar.edu.unq.persistencia1.services.vuelos;


import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.List;

public class VuelosByDuracion implements Operation<List<Vuelo>> {

    @Override
    public List<Vuelo> execute() {
        return (List<Vuelo>) orderByDuracion(SessionManager.getSession().createCriteria(Vuelo.class, "vuelo")).list();
    }

    public Criteria orderByDuracion(Criteria criteria) {
        criteria.addOrder(Order.asc("duracion"));
        return criteria;
    }
}
