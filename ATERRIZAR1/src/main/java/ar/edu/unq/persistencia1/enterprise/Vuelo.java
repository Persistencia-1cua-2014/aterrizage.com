package ar.edu.unq.persistencia1.enterprise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Vuelo {
    private List<Tramo> tramos;
    private Integer  id;
    private Integer  price;
    private long duracion;


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
        calculateDuration();
	}

    private void calculateDuration() {
        Date salida = getfirstDate();
        Date llegada = getLastDate();
        long diffInMillies = llegada.getTime() - salida.getTime();
        long duration = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        setDuracion(duration);
    }

    private Date getLastDate() {
        List<Date> dates = new ArrayList<Date>();
        for(Tramo t: getTramos()){
            dates.add(t.getLlegada());
        }
        Collections.sort(dates);
        return dates.get(dates.size() - 1);
    }

    private Date getfirstDate() {
        List<Date> dates = new ArrayList<Date>();
        for(Tramo t: getTramos()){
            dates.add(t.getSalida());
        }
        Collections.sort(dates);
        return dates.get(0);
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

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public long getDuracion() {
        return duracion;
    }
}
