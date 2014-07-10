package neo_integration;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.homes.SocialNetworkManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.operations.CreateUserNode;
import ar.edu.unq.persistencia1.services.neo4j.operations.GetAllFriends;
import ar.edu.unq.persistencia1.services.neo4j.operations.GetFriends;
import junit.framework.TestCase;
import neo_integration.operations.ClearDatabase;

import org.junit.Before;

import java.util.Date;
import java.util.List;

public class SocialNetworkTest extends TestCase{

    private Usuario usuario;
    private Usuario friend;
    private Usuario friend2;

    @Before
    public void setUp(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.friend = new Usuario("Santiago", "DeSantiago", "Santy", "Santiagoooooooo", new Date(), "12", "");
        this.friend2 = new Usuario("Juampi", "DeJuampi", "chaun", "Juampiii", new Date(), "12", "");
        NeoManager.runInSession(new ClearDatabase());
    }

    public void testAddFriend(){
        SocialNetworkManager social = new SocialNetworkManager();

        NeoManager.runInSession(new CreateUserNode(usuario));
        NeoManager.runInSession(new CreateUserNode(friend));

        social.addFriend(usuario, friend); 

        List<String> friends = NeoManager.runInSession(new GetFriends(usuario));
        assertEquals(1, friends.size());
        
        NeoManager.runInSession(new CreateUserNode(friend2));
        social.addFriend(friend, friend2);
        List<String> friendsAll = NeoManager.runInSession(new GetAllFriends(usuario));
        assertEquals(2, friendsAll.size());
        

    }
  

}
