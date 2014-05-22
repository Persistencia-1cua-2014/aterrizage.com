package ar.edu.unq.persistencia1.homes;


import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.classic.Session;

public class DAOTramos {

	public Tramo get(int id) {
		return (Tramo) SessionManager.getSession().get(Tramo.class, id);
	}

	public void save(Tramo l) {
		Session s = SessionManager.getSession();
		s.saveOrUpdate(l);
	}
}
