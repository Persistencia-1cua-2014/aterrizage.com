package ar.edu.unq.persistencia1.homes;


import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.classic.Session;

public class DAOLugares {

	public Lugar get(int id) {
		return (Lugar) SessionManager.getSession().get(Lugar.class, id);
	}

	public void save(Lugar l) {
		Session s = SessionManager.getSession();
		s.saveOrUpdate(l);
	}
}
