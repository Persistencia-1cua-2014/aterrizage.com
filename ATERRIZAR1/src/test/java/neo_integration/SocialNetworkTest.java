package neo_integration;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.homes.SocialNetworkManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;

import ar.edu.unq.persistencia1.services.neo4j.operations.CreateUserNode;
import ar.edu.unq.persistencia1.services.neo4j.operations.GetFriends;
import junit.framework.TestCase;
import neo_integration.operations.ClearDatabase;
import org.junit.Before;
import org.neo4j.graphdb.Node;

import java.util.Date;
import java.util.List;

public class SocialNetworkTest extends TestCase{

    @Before
    public void setUp(){
        NeoManager.runInSession(new ClearDatabase());
    }

    public void testAddFriend(){
        SocialNetworkManager social = new SocialNetworkManager();

        Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        Usuario friend = new Usuario("Santiago", "DeSantiago", "Santy", "Santiagoooooooo", new Date(), "12", "");

        NeoManager.runInSession(new CreateUserNode(usuario));
        NeoManager.runInSession(new CreateUserNode(friend));

        social.addFriend(usuario, friend);

        List<String> friends = NeoManager.runInSession(new GetFriends(usuario));
        assertEquals(1, friends.size());

    }


}
