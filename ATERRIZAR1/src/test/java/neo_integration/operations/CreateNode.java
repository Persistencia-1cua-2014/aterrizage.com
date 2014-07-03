package neo_integration.operations;

import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

public class CreateNode implements NeoOperation<String> {
    @Override
    public String execute() {
        GraphDatabaseService g = NeoManager.getSession();
        Node node = g.createNode();
        Index<Node> indexes = NeoManager.getSession().index().forNodes("aIndex");
        indexes.add(node, "userName", "aNodeIndex");
        node.setProperty("aProperty", "aValue");
        return (String) node.getProperty( "aProperty" );
    }
}
