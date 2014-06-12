package ar.edu.unq.persistencia1.services.vuelos;

import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.enterprise.asientos.Categoria;
import ar.edu.unq.persistencia1.services.Operation;
import ar.edu.unq.persistencia1.services.SessionManager;

import java.util.List;

public class VuelosByCategoria implements Operation<List<Vuelo>> {

    private Categoria categoria;

    public VuelosByCategoria(Categoria categoria) {
       this.categoria = categoria;

    }

    @Override
    public List<Vuelo> execute() {
        return (List<Vuelo>) SessionManager.getSession().createQuery("select distinct this from Vuelo this join this.tramos tramo join tramo.asientos asiento where asiento.categoria = :categoria")
                .setParameter("categoria", this.categoria)
                .list();
    }
}
