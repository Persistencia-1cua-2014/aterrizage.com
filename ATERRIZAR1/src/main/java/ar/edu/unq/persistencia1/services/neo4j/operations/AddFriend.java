package ar.edu.unq.persistencia1.services.neo4j.operations;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import ar.edu.unq.persistencia1.services.neo4j.RelTypes;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class AddFriend implements NeoOperation<Relationship> {

    private Usuario newFriend;
    private Usuario user;

    public AddFriend(Usuario user, Usuario newFriend){
        this.user = user;
        this.newFriend = newFriend;
    }


    @Override
    public Relationship execute() {
        Node userNode = NeoManager.runInSession(new GetUserNode(user));
        Node friendNode = NeoManager.runInSession(new GetUserNode(newFriend));

        return userNode.createRelationshipTo(friendNode, RelTypes.KNOWS);
    }
}
