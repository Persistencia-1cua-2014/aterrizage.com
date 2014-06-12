package ar.edu.unq.persistencia1.services.vuelos;


import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.List;

public class VuelosByPrecio implements Operation<List<Vuelo>> {

	@Override
	public List<Vuelo> execute() {
		return (List<Vuelo>) orderByLessCost(SessionManager.getSession().createCriteria(Vuelo.class, "vuelo")).list();
	}

	public Criteria orderByLessCost(Criteria criteria) {
		criteria.addOrder(Order.asc("price"));
		return criteria;
	}
}
