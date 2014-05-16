package ar.edu.unq.persistencia1.services.usuarios;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

public class ValidarCodigo implements Operation<Boolean>{

	private String nombreDeUsuario;
	private String codigo;

	public ValidarCodigo(String codigo, String nombreDeUsuario){
		this.codigo = codigo;
		this.nombreDeUsuario = nombreDeUsuario;
	}
	@Override
	public Boolean execute() {
		Usuario u = SessionManager.runInSession(new GetUsuarioByUserNameAndCode(this.nombreDeUsuario, this.codigo));
		u.setVerificado(true);
		SessionManager.runInSession(new CreateUsuario(u));
		return true;
	}
}
