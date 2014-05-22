package support.daos;

import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.classic.Session;

public class DAOVuelo {

	public Vuelo get(int id) {
		return (Vuelo) SessionManager.getSession().get(Vuelo.class, id);
	}

	public void save(Vuelo a) {
		Session s = SessionManager.getSession();
		s.saveOrUpdate(a);
	}
}
