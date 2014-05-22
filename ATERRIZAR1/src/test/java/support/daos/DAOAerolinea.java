package support.daos;

import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.classic.Session;

public class DAOAerolinea {

	public Aerolinea get(int id) {
		return (Aerolinea) SessionManager.getSession().get(Aerolinea.class, id);
	}

	public void save(Aerolinea a) {
		Session s = SessionManager.getSession();
		s.saveOrUpdate(a);
	}
}
