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

    public void testMongoInsert(){
        DB database = MongoManager.getDatabase("mongo_test");
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

        List<Lugar> lugares =  manager.getDestinations(usuario);
        assertEquals(1, lugares.size());

    }

}
