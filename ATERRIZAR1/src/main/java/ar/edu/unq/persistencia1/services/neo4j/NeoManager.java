package ar.edu.unq.persistencia1.services.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class NeoManager {
    private static ThreadLocal<GraphDatabaseService> tlGraphDatabase = new ThreadLocal<GraphDatabaseService>();

    public static <T> T runInSessionInDatabase(NeoOperation<T> operation, String database) {
        if (tlGraphDatabase.get() == null) {
            tlGraphDatabase.set(createDatabase(database));
        }
        //registerShutdownHook( graphDb );
        T result;
        Transaction tx = null;
        try {
            tx = tlGraphDatabase.get().beginTx();
            result = operation.execute();
            // Database operations go here
            tx.success();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (tx != null) {
                tx.close();
            }
        }
        return result;
    }


    public static <T> T runInSession(NeoOperation<T> operation) {
        return runInSessionInDatabase(operation, "neo_4_j_database");
    }

    public static GraphDatabaseService getSession() {
        return tlGraphDatabase.get();
    }

    public static GraphDatabaseService createDatabase(String database) {
        final GraphDatabaseService grp = new GraphDatabaseFactory().newEmbeddedDatabase(database);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                grp.shutdown();
            }
        }));

        return grp;
    }


}
