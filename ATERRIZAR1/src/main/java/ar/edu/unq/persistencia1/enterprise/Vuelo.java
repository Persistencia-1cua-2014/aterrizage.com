package ar.edu.unq.persistencia1.enterprise;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private List<Tramo> tramos;
    private Integer  id;

	public Vuelo(Integer  id) {
        setTramos(new ArrayList<Tramo>());
        setId(id);
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
