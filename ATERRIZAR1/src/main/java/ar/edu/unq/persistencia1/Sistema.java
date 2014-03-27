package ar.edu.unq.persistencia1;


import ar.edu.unq.persistencia1.homes.RepositorioDeUsuarios;

public class Sistema {

    static String databaseName = "aterrizage";

    public void registrarUsuario(Usuario usuario) throws Exception {

        RepositorioDeUsuarios repo = this.getRepositorioDeUsuarios();
        repo.guardarUsuario(usuario);
    }

    public RepositorioDeUsuarios getRepositorioDeUsuarios(){
        return new RepositorioDeUsuarios(databaseName);

    }

}
