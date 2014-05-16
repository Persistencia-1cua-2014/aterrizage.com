package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;
import ar.edu.unq.persistencia1.services.usuarios.CreateUsuario;
import ar.edu.unq.persistencia1.services.usuarios.GetUsuarioByUserName;


public class ChangePassword implements Operation<Void> {
	private String nuevaPass;
	private String userName;

	public ChangePassword(String userName, String nuevaPass) {
		this.userName = userName;
		this.nuevaPass = nuevaPass;
	}

	@Override
	public Void execute() {
		Usuario u = SessionManager.runInSession(new GetUsuarioByUserName(userName));
		u.setPassword(nuevaPass);
		SessionManager.runInSession(new CreateUsuario(u));
		return null;
	}
}
