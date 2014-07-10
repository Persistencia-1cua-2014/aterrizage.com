package ar.edu.unq.persistencia1.services.neo4j.operations;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import ar.edu.unq.persistencia1.services.neo4j.RelTypes;

public class GetAllFriends implements NeoOperation<List<String>> {
	
    private Usuario user;

    public GetAllFriends(Usuario usuario){
        this.user = usuario;
    }

	@Override
	public List<String>  execute() {
		    GraphDatabaseService g = NeoManager.getSession();
            TraversalDescription td = g.traversalDescription().breadthFirst()
		    .relationships( RelTypes.KNOWS, Direction.OUTGOING )
		    .evaluator( Evaluators.excludeStartPosition() );
            Node userNode = NeoManager.runInSession(new GetUserNode(this.user));
		    Traverser t = td.traverse( userNode );
		    List<String> nombres = new ArrayList<String>();
		    for ( Node n:t.nodes() )
		    {
		        nombres.add ((String) n.getProperty("userName"));
		    }

	return nombres;
	
}
}