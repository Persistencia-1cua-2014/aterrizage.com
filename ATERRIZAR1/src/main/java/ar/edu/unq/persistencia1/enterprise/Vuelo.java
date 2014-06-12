package ar.edu.unq.persistencia1.enterprise;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private List<Tramo> tramos;
    private Integer  id;
    private Integer  price;

	public Vuelo(List<Tramo> tramos) {
        this();
        for(Tramo t: tramos)
            addTramo(t);
	}

	public Vuelo() {
        setPrice(0);
        setTramos(new ArrayList<Tramo>());
    }

	public void addTramo(Tramo tramo){
		getTramos().add(tramo);
		calculatePrice();
	}

	public void calculatePrice(){
		for(Tramo t: getTramos())
			setPrice(getPrice() + t.getPrecioBase());
	}

    public List<Tramo> getTramos() {
        return tramos;
    }

    protected void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Vuelo){
			return this.getId().equals(((Vuelo)o ).getId());
		}
		return super.equals(o);
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
