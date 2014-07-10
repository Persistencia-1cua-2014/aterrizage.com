package ar.edu.unq.persistencia1.services.mongodb;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoManager {


    public static DB getDatabase(String database){
        MongoClient mongo;
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mongo.getDB(database);
    }
}
