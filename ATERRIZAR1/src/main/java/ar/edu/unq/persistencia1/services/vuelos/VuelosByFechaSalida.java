package ar.edu.unq.persistencia1.services.vuelos;

import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.Date;
import java.util.List;


public class VuelosByFechaSalida implements Operation<List<Vuelo>> {
    private final Date fechaSalida;

    public VuelosByFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;

    }


    @Override
    public List<Vuelo> execute() {
        return (List<Vuelo>) SessionManager.getSession().createQuery
                ("select distinct this from Vuelo this join this.tramos tramo  where tramo.salida = :salida")
                .setParameter("salida", this.fechaSalida)
                .list();
    }
}
