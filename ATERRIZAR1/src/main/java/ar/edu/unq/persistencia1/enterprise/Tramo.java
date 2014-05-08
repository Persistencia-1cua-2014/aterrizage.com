package ar.edu.unq.persistencia1.enterprise;


import java.util.Date;

public class Tramo {
    private Lugar origen;
    private Lugar destino;
    private Date salida;
    private Date llegada;


    public Tramo(Lugar origen, Lugar destino, Date salida, Date llegada) {
        setOrigen(origen);
        setDestino(destino);
        setSalida(salida);
        setLlegada(llegada);
    }

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
}
