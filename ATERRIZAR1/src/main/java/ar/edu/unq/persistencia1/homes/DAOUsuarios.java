package ar.edu.unq.persistencia1.homes;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.SessionManager;

public class DAOUsuarios {

    public Usuario get(int id){
        return (Usuario) SessionManager.getSession().get(Usuario.class,id);
    }

    public void save(Usuario u) {
        SessionManager.getSession().saveOrUpdate(u);
    }
}
