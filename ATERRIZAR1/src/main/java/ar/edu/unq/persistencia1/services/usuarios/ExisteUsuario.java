package ar.edu.unq.persistencia1.services.usuarios;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

public class ExisteUsuario implements Operation<Boolean>{

	private Usuario usuario;

	public ExisteUsuario(Usuario usuario){
		this.usuario = usuario;
	}

	@Override
	public Boolean execute() {
		Session session = SessionManager.getSession();
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.eq("nombreDeUsuario", this.usuario.getNombreDeUsuario()));
		return c.uniqueResult() != null;
	}
}
