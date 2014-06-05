package ar.edu.unq.persistencia1.enterprise.asientos;


public abstract class Categoria {
	private int id;
	private Integer factorPrecio;

	public Integer getFactorPrecio() {
		return factorPrecio;
	}

	public void setFactorPrecio(Integer factorPrecio) {
		this.factorPrecio = factorPrecio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
