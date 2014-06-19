package ar.edu.unq.persistencia1.services.vuelos;

import java.util.Date;
import java.util.List;

import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

public class VuelosByOrigenYDestino implements Operation<List<Vuelo>>{
	
    private final Lugar origen;
    
    private final Lugar destino;

    
    public VuelosByOrigenYDestino(Lugar origen,Lugar destino) {
        this.origen = origen;
        this.destino= destino;
    }


    @Override
    public List<Vuelo> execute() {
        return (List<Vuelo>) SessionManager.getSession().createQuery
                ("select distinct this from Vuelo this join this.tramos tramo "
                		+ "where tramo.origen = :origen and tramo.destino = :destino" )
                .setParameter("origen", this.origen)
                .setParameter("destino", this.destino)
                .list();
    }
	
	

}
