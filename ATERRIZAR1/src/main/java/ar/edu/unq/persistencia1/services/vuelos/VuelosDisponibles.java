package ar.edu.unq.persistencia1.services.vuelos;

import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class VuelosDisponibles implements Operation<List<Vuelo>> {
	@Override
	public List<Vuelo> execute() {
		Session session = SessionManager.getSession();

		Criteria c = session.createCriteria(Vuelo.class);
		c.createCriteria("tramos").createCriteria("asientos").add(Restrictions.isNull("usuario"));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return (List<Vuelo>) c.list();

	}
}
