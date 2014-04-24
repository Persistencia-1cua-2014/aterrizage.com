package ar.edu.unq.persistencia1.services.usuarios;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.homes.DAOUsuarios;
import ar.edu.unq.persistencia1.services.Operation;

public class CreateUsuario implements Operation<Usuario>{
    private Usuario usuario;

    public CreateUsuario(Usuario u){
        this.usuario = u;
    }

    @Override
    public Usuario execute() {
        new DAOUsuarios().save(this.usuario);
        return usuario;
    }
}
