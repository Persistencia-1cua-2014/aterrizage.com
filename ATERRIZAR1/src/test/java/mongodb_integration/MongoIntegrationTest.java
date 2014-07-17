package mongodb_integration;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import ar.edu.unq.persistencia1.homes.CommentsManager;
import ar.edu.unq.persistencia1.services.mongodb.MongoManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import junit.framework.TestCase;

import java.util.Date;
import java.util.List;

public class MongoIntegrationTest extends TestCase{

    private Usuario usuario;
    private Lugar lugar;

    public void setUp(){
        getDatabase().dropDatabase();
    }

    public DB getDatabase(){
        return MongoManager.getDatabase("mongoDataBase");
    }

    public void testMongoInsert(){
        DB database = getDatabase();
        DBCollection table = database.getCollection("user");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "mkyong");
        document.put("age", 30);
        document.put("createdDate", new Date());
        table.insert(document);

    }

    public void testAddDestination(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);

        List<String> lugares =  manager.getDestinations(usuario);
        assertEquals(1, lugares.size());
        assertEquals("Rio de Janeiro",lugares.get(0));
    }

    public void testSetDestinationVisibilityAsPrivate(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        manager.setPrivate(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar,"private"));

    }


    public void testSetDestinationVisibilityAsPublic(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        manager.setPubilc(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar,"public"));

    }

    public void testSetDestinationVisibilityAsOnlyFriends(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        manager.setOnlyFriends(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar,"friends"));

    }

    public void testGetDestinationVisibility(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        assertFalse(manager.isVisibility(usuario, lugar,"private"));

    }


    public void testSetDestinationPublicDefault(){
        this.usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date(), "12", "");
        this.lugar = new Lugar("Rio de Janeiro");
        CommentsManager manager = new CommentsManager();
        manager.addDestination(usuario, lugar);
        assertTrue(manager.isVisibility(usuario, lugar,"public"));

    }

}
