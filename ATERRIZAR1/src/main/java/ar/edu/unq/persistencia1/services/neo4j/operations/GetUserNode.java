package ar.edu.unq.persistencia1.services.neo4j.operations;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;


public class GetUserNode implements NeoOperation<Node> {

    private Usuario user;

    public GetUserNode(Usuario user) {
        this.user = user;
    }

    @Override
    public Node execute() {
        GraphDatabaseService g = NeoManager.getSession();
        ResourceIterator<Node> nodes;
        nodes = g.index().forNodes("users").query("userName", user.getNombreDeUsuario());
        return nodes.next();

    }
}
