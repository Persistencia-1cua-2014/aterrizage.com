package neo_integration.operations;

import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.kernel.impl.util.StringLogger;


public class ClearDatabase implements NeoOperation<Boolean> {
    @Override
    public Boolean execute() {
        ExecutionEngine engine = new ExecutionEngine(NeoManager.getSession(), StringLogger.SYSTEM);
        //engine.execute("start n=node(*) where n.name = 'my node' return n, n.name");
        engine.execute("MATCH (n)OPTIONAL MATCH (n)-[r]-() DELETE n,r");
        return true;

    }
}