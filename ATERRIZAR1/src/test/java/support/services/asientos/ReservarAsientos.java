package support.services.asientos;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;
import ar.edu.unq.persistencia1.homes.ManejadorDeAsientos;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ReservarAsientos implements Operation<Object> {
	private List<Asiento> asientos;
	private Usuario usuario;
	private ManejadorDeAsientos manejador;
	private Tramo tramo;

	public ReservarAsientos(Usuario u, List<Asiento> asientos, Tramo t, ManejadorDeAsientos manejador){
		this.usuario = u;
		this.asientos = asientos;
		this.tramo = t;
		this.manejador = manejador;
	}


	@Override
	public Asiento execute() {
		Usuario u = (Usuario) SessionManager.getSession().get(Usuario.class, this.usuario.getIdUsuario());
		Tramo t = (Tramo) SessionManager.getSession().get(Tramo.class, tramo.getId());
		List<Asiento> asientoList= new ArrayList<Asiento>();
		for(Asiento a: asientos)
			asientoList.add((Asiento) SessionManager.getSession().get(Asiento.class, a.getId()));

		manejador.reservarAsientos(u, asientoList, t);
		return null;
	}
}
