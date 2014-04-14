package ar.edu.unq.persistencia1;

import java.util.Date;

public class Usuario {
    String nombre;
    String apellido;
    String nombreDeUsuario;
    String email;
    String password;
    String codigoDeValidacion;
    Date birthday;
    boolean verificado;
    private int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(){

    }

    public Usuario(String nombre, String apellido, String nombreDeUsuario, String email, Date birthday,String pass, String codigoDeValidacion ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.birthday = birthday;
        this.verificado = false;
        this.password= pass;
        this.codigoDeValidacion= codigoDeValidacion;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }
    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

    public String getCodigoDeValidacion() {
        return codigoDeValidacion;
    }

    public void setCodigoDeValidacion(String codigoDeValidacion) {
        this.codigoDeValidacion = codigoDeValidacion;
    }

    public boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}
