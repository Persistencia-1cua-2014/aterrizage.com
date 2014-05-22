package ar.edu.unq.persistencia1.enterprise;

import java.util.ArrayList;
import java.util.List;

public class Aerolinea {
    private List<Vuelo> vueloList;
    private int idAerolinea;

	public Aerolinea(){}

    public Aerolinea(ArrayList<Vuelo> vuelos) {
        setVueloList(vuelos);
    }

    public List<Vuelo> getVueloList() {
        return vueloList;
    }

    public void setVueloList(List<Vuelo> vueloList) {
        this.vueloList = vueloList;
    }

    public int getIdAerolinea() {
        return idAerolinea;
    }

    public void setIdAerolinea(int idAerolinea) {
        this.idAerolinea = idAerolinea;
    }
}
