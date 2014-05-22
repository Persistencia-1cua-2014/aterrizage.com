package support.services.aerolinea;


import ar.edu.unq.persistencia1.enterprise.Aerolinea;
import ar.edu.unq.persistencia1.services.Operation;
import support.daos.DAOAerolinea;

public class SaveAerolinea implements Operation<Aerolinea> {

	private Aerolinea aerolinea;

	public  SaveAerolinea(Aerolinea aerolinea){
		this.aerolinea = aerolinea;
	}

	@Override
	public Aerolinea execute() {
		DAOAerolinea dao = new DAOAerolinea();
		dao.save(aerolinea);
		return aerolinea;
	}
}
