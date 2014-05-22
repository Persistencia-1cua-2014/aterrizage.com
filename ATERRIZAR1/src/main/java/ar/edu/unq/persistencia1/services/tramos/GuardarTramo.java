package ar.edu.unq.persistencia1.services.tramos;


import ar.edu.unq.persistencia1.enterprise.Tramo;
import ar.edu.unq.persistencia1.homes.DAOTramos;
import ar.edu.unq.persistencia1.services.Operation;

public class GuardarTramo implements Operation<Tramo>{

	private Tramo tramo;

	public GuardarTramo(Tramo Tramo){
		this.tramo = Tramo;
	}

	@Override
	public Tramo execute() {
		DAOTramos daoTramoes = new DAOTramos();
		daoTramoes.save(tramo);
		return tramo;
	}
}
