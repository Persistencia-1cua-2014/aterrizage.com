package support.services.asientos;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.Date;

public class ReservarAsientoToUser implements Operation<Asiento> {
	private Usuario usuario;
	private ManejadorDeAsientos manejador;
	private Asiento asiento;
	private Tramo tramo;

	public ReservarAsientoToUser(Usuario u, Asiento a, Tramo t, ManejadorDeAsientos manejador){
		this.usuario = u;
		this.asiento = a;
		this.tramo = t;
		this.manejador = manejador;
	}


	@Override
	public Asiento execute() {
		Asiento a = (Asiento) SessionManager.getSession().get(Asiento.class, asiento.getId());
		Usuario u = (Usuario) SessionManager.getSession().get(Usuario.class, this.usuario.getIdUsuario());
		Tramo t = (Tramo) SessionManager.getSession().get(Tramo.class, tramo.getId());

		manejador.reservarAsiento(u, a, t);
		return a;
	}
}
