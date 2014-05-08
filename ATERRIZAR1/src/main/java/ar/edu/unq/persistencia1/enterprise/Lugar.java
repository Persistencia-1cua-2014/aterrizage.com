package ar.edu.unq.persistencia1.enterprise;


public class Lugar {
    private String nombre;

    public Lugar(String nombre) {
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
