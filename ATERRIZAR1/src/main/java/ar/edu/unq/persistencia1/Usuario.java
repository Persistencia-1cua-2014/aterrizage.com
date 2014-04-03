package ar.edu.unq.persistencia1;

import java.util.Date;

public class Usuario {
    String nombre, apellido, nombreDeUsuario, email, password, codigoDeValidacion;
    Date birthday;
    Boolean verificado;

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
}
