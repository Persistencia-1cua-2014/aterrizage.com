package ar.edu.unq.persistencia1.services.usuarios;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

public class ExisteCodigo implements Operation<Boolean> {

	private String nombreDeUsuario;
	private String codigo;

	public ExisteCodigo(String codigo, String nombreDeUsuario) {
		this.codigo = codigo;
		this.nombreDeUsuario = nombreDeUsuario;
	}

	@Override
	public Boolean execute() {
		Session session = SessionManager.getSession();

		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.eq("codigoDeValidacion", this.codigo));
		c.add(Restrictions.eq("nombreDeUsuario", this.nombreDeUsuario));
		return c.uniqueResult() != null;
	}
}
