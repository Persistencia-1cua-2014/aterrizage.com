package ar.edu.unq.persistencia1.enterprise;

import ar.edu.unq.persistencia1.Usuario;

public class Asiento {
    private Usuario usuario;

    public void reservar(Usuario usuario) {
        setUsuario(usuario);
    }

    public boolean estaReservado() {
        return getUsuario() != null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
