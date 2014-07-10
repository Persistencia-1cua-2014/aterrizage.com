package mongodb_integration;

import ar.edu.unq.persistencia1.services.mongodb.MongoManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import junit.framework.TestCase;

import java.util.Date;

public class MongoIntegrationTest extends TestCase{

    public void testMongoInsert(){
        DB database = MongoManager.getDatabase("mongo_test");
        DBCollection table = database.getCollection("user");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "mkyong");
        document.put("age", 30);
        document.put("createdDate", new Date());
        table.insert(document);




    }

}
