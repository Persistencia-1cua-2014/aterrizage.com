package neo_integration.operations;

import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;

public class CreateNode implements NeoOperation<String> {
    @Override
    public String execute() {
        GraphDatabaseService g = NeoManager.getSession();
        Label label = DynamicLabel.label("User");
        Node node = g.createNode(label);
        node.setProperty("aProperty", "aValue");
        return (String) node.getProperty( "aProperty" );
    }
}
