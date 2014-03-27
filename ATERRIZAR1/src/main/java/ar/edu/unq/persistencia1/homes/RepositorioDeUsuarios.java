package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.Service;

public class RepositorioDeUsuarios {

    static String databaseName = "Usuario";

    public void guardarUsuario(Usuario usuario){

    }

    public boolean usuarioExiste(Usuario usuario) throws Exception {
        return this.getService().existeUsuario(usuario);
    }

    private Service getService(){
        return new Service(databaseName);
    }
}
