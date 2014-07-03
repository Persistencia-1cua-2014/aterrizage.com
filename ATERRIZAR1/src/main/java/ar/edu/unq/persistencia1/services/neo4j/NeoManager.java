package ar.edu.unq.persistencia1.services.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class NeoManager {
    private static ThreadLocal<GraphDatabaseService> tlGraphDatabase = new ThreadLocal<GraphDatabaseService>();

    public static <T> T runInSessionInDatabase(NeoOperation<T> operation, String database) {
        if (tlGraphDatabase.get() == null) {
            tlGraphDatabase.set(new GraphDatabaseFactory().newEmbeddedDatabase(database));
        }
        //registerShutdownHook( graphDb );
        T result;
        try {
            Transaction tx = tlGraphDatabase.get().beginTx();
            result = operation.execute();
            // Database operations go here
            tx.success();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static <T> T runInSession(NeoOperation<T> operation) {
        return runInSessionInDatabase(operation, "neo_4_j_database");
    }

    public static GraphDatabaseService getSession() {
        return tlGraphDatabase.get();
    }


}
