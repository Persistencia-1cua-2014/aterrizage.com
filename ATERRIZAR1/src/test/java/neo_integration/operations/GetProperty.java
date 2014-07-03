package neo_integration.operations;


import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import org.neo4j.graphdb.*;

public class GetProperty implements NeoOperation<String>{
    @Override
    public String execute() {
        GraphDatabaseService g = NeoManager.getSession();
        Label label = DynamicLabel.label("User");

        ResourceIterator<Node> nodes;
        nodes = g.findNodesByLabelAndProperty( label, "aProperty", "aValue" ).iterator();
        Node node = nodes.next();

        return (String) node.getProperty("aProperty");
    }
}
