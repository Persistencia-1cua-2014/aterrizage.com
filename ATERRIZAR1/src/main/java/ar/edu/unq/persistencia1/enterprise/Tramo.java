package ar.edu.unq.persistencia1.enterprise;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.asientos.Asiento;

import java.util.Date;
import java.util.List;

public class Tramo {
    private Lugar origen;
    private Lugar destino;
    private Date salida;
    private Date llegada;
    private List<Asiento> asientos;

    public Tramo(Lugar origen, Lugar destino, Date salida, Date llegada) {
        setOrigen(origen);
        setDestino(destino);
        setSalida(salida);
        setLlegada(llegada);
    }

    public void addAsiento(Asiento asiento) {
        getAsientos().add(asiento);
    }

    public void reservar(Asiento asiento, Usuario usuario){
        asiento.reservar(usuario);
    }

    /* ******************** */
    /*  Getter and Setters  */
    /* ******************** */

    public Lugar getOrigen() {
        return origen;
    }

    public void setOrigen(Lugar origen) {
        this.origen = origen;
    }

    public Lugar getDestino() {
        return destino;
    }

    public void setDestino(Lugar destino) {
        this.destino = destino;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public Date getLlegada() {
        return llegada;
    }

    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }
}
