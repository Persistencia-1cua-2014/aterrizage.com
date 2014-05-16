package ar.edu.unq.persistencia1.services.usuarios;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;


public class ExisteUsuarioWithPass implements Operation<Boolean> {
	private final String userName;
	private final String pass;

	public ExisteUsuarioWithPass(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}

	@Override
	public Boolean execute() {
		Session session = SessionManager.getSession();
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.eq("nombreDeUsuario", this.userName));
		c.add(Restrictions.eq("password", this.userName));
		return c.uniqueResult() != null;
	}
}
