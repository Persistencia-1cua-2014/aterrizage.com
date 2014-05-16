package ar.edu.unq.persistencia1.services.usuarios;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class GetUsuarioByUserNameAndCode implements Operation<Usuario> {

	private String code;
	private String userName;

	public GetUsuarioByUserNameAndCode(String userName, String code) {
		this.code = code;
		this.userName = userName;
	}

	@Override
	public Usuario execute() {
		Session s = SessionManager.getSession();
		Criteria c = s.createCriteria(Usuario.class);
		c.add(Restrictions.eq("nombreDeUsuario", userName));
		c.add(Restrictions.eq("codigoDeValidacion", code));
		return (Usuario) c.uniqueResult();
	}
}
