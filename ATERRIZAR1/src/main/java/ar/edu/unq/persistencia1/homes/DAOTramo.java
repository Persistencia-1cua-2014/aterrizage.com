package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.services.SessionManager;

public class DAOTramo {

	public Tramo get(int id){
		return (Tramo) SessionManager.getSession().get(Tramo.class,id);
	}

	public void save(Tramo t) {
		SessionManager.getSession().saveOrUpdate(t);
	}
}
