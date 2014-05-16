package ar.edu.unq.persistencia1.services.usuarios;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;


public class GetUsuario implements Operation<Usuario> {
	private String nombreDeUsuario;
	private String password;

	public GetUsuario(String nombreDeUsuario, String password) {
		this.nombreDeUsuario = nombreDeUsuario;
		this.password = password;
	}

	@Override
	public Usuario execute() {
		Session session = SessionManager.getSession();
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.eq("nombreDeUsuario", this.nombreDeUsuario));
		c.add(Restrictions.eq("password", this.password));
		return (Usuario) c.uniqueResult();
	}
}
