package ar.edu.unq.persistencia1.enterprise;

import java.util.ArrayList;
import java.util.List;

public class Aerolinea {
    private List<Vuelo> vueloList;

    public Aerolinea() {
        setVueloList(new ArrayList<Vuelo>());
    }

    public List<Vuelo> getVueloList() {
        return vueloList;
    }

    public void setVueloList(List<Vuelo> vueloList) {
        this.vueloList = vueloList;
    }
}
