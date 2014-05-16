package ar.edu.unq.persistencia1.services.usuarios;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;


public class GetUsuarioByUserName implements Operation<Usuario> {
	private String nombreDeUsuario;

	public GetUsuarioByUserName(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	@Override
	public Usuario execute() {
		Session session = SessionManager.getSession();
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.eq("nombreDeUsuario", this.nombreDeUsuario));
		return (Usuario) c.uniqueResult();
	}
}
