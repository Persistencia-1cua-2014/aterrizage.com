package ar.edu.unq.persistencia1.homes;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.operations.AddFriend;

public class SocialNetworkManager {

    public void addFriend(Usuario usuario, Usuario newFriend){
        NeoManager.runInSession(new AddFriend(usuario, newFriend));
    }

}
