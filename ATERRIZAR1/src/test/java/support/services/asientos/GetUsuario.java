package support.services.asientos;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

public class GetUsuario implements Operation<Usuario>{

	private int asientoid;

	public GetUsuario(int asientoid){
		this.asientoid = asientoid;
	}


	@Override
	public Usuario execute() {
		Asiento a = (Asiento) SessionManager.getSession().get(Asiento.class, asientoid);
		return a.getUsuario();
	}
}
