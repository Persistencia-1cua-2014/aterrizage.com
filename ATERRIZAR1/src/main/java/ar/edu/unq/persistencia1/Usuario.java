package ar.edu.unq.persistencia1;

import java.util.Date;

public class Usuario {
    String nombre, apellido, nombreDeUsuario, email;
    Date birthday;

    public Usuario(String nombre, String apellido, String nombreDeUsuario, String email, Date birthday) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.birthday = birthday;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }
}
