package ar.edu.unq.persistencia1.services.neo4j.operations;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import java.util.List;

public class AreFriends implements NeoOperation<Boolean> {

    private Usuario user;
    private Usuario user2;

    public AreFriends(Usuario user, Usuario user2){
        this.user = user;
        this.user2 = user2;
    }


    @Override
    public Boolean execute() {
        List<String> amigos = NeoManager.runInSession(new GetFriends(this.user));

        for(String a: amigos){
            if(a.equals(user2.getNombreDeUsuario())){
                return true;
            }

        }
        return false;
    }
}
