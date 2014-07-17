package mongodb_integration;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.enterprise.Vuelo;
import ar.edu.unq.persistencia1.homes.CommentsManager;
import ar.edu.unq.persistencia1.homes.SocialNetworkManager;
import ar.edu.unq.persistencia1.services.mongodb.MongoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.operations.CreateUserNode;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class MongoIntegrationTest extends TestCase {

    private Usuario usuario;
    private Lugar lugar;
    private Usuario usuario2;
    private Lugar lugar2;
    private Lugar lugar3;
    private Lugar lugar4;

    public void setUp() {
        getDatabase().dropDatabase();
    }

    public DB getDatabase() {
        return MongoManager.getDatabase("mongoDataBase");
    }

    public void testMongoInsert() {
        DB database = getDatabase();
        DBCollection table = database.getCollection("user");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "mkyong");
        document.put("age", 30);
        document.put("createdDate", new Date());
        table.insert(document);

    }

    public void testAddDestination() {
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);

        List<String> lugares = manager.getDestinations(usuario);
        assertEquals(1, lugares.size());
        assertEquals("Rio de Janeiro", lugares.get(0));
    }

    public void testSetDestinationVisibilityAsPrivate() {
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        manager.setPrivate(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar, "private"));

    }

<<<<<<< HEAD
    public void testSetDestinationVisibilityAsPublic(){
=======

    public void testSetDestinationVisibilityAsPublic() {
>>>>>>> f4904832d630012ffa2277ce1b7a283033dde59b
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        manager.setPubilc(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar, "public"));

    }

    public void testSetDestinationVisibilityAsOnlyFriends() {
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        manager.setOnlyFriends(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar, "friends"));

    }

    public void testGetDestinationVisibility() {
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        assertFalse(manager.isVisibility(usuario, lugar, "private"));

    }


    public void testSetDestinationPublicDefault() {
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar, "public"));

    }


    public void testGetFriendDestinations() {
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        this.lugar2 = new Lugar("Santiago de Chile");
        this.lugar3 = new Lugar("Montevideo");
        this.lugar4 = new Lugar("Quito");
        this.usuario2 = new Usuario("Santiago", "Santiago", "Santiago", "Santiago", new Date(), "13", "");
        CommentsManager manager = new CommentsManager();
        SocialNetworkManager socialNetwork = new SocialNetworkManager();
        NeoManager.runInSession(new CreateUserNode(usuario));
        NeoManager.runInSession(new CreateUserNode(usuario2));
        socialNetwork.addFriend(usuario, usuario2);

        manager.addDestination(usuario2, lugar);
        manager.addDestination(usuario2, lugar2);
        manager.addDestination(usuario2, lugar3);

        manager.setOnlyFriends(usuario2, lugar2);
        manager.setPrivate(usuario2, lugar3);

        List<String> destinations = manager.getUserDestinations(usuario, usuario2);
        assertEquals(new ArrayList<String>() {{
            add(lugar.getNombre());
            add(lugar2.getNombre());
        }}, destinations);

    }
    
    public void testSetComment(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        manager.setComment(usuario, lugar,"re piola el lugar");
        assertEquals(manager.getComment(usuario,lugar),"re piola el lugar");

    }

}
