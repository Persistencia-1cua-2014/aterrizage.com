package ar.edu.unq.persistencia1.services.usuarios;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.homes.DAOUsuarios;
import ar.edu.unq.persistencia1.services.Operation;

public class GuardarCodigo implements Operation<Usuario>{
    private final String code;
    private Usuario usuario;

    public GuardarCodigo(Usuario u, String code){
        this.usuario = u;
        this.code = code;
    }


    @Override
    public Usuario execute() {
        DAOUsuarios dao = new DAOUsuarios();
        usuario.setCodigoDeValidacion(code);
        dao.save(this.usuario);
        return usuario;
    }
}
