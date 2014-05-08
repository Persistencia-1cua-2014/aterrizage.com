package ar.edu.unq.persistencia1.enterprise;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private List<Tramo> tramos;

    public Vuelo() {
        setTramos(new ArrayList<Tramo>());
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }
}
