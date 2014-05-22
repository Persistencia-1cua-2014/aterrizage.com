package ar.edu.unq.persistencia1.enterprise.asientos;

import ar.edu.unq.persistencia1.Usuario;

public class Asiento {
    private Usuario usuario;
    private Categoria categoria;
	private int id;

	public Asiento(){}

    public Asiento(Categoria categoria) {
        this.categoria = categoria;
    }

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
