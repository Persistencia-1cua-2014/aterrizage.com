package ar.edu.unq.persistencia1.services.neo4j.operations;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

public class CreateUserNode implements NeoOperation<Node>{

    private final Usuario user;

    public CreateUserNode(Usuario user){
        this.user = user;
    }


    @Override
    public Node execute() {
        Node userName = NeoManager.getSession().createNode();
        userName.setProperty("userName", user.getNombreDeUsuario());
        Index<Node> indexes = NeoManager.getSession().index().forNodes("users");
        indexes.add(userName, "userName", user.getNombreDeUsuario());
        return userName;
    }
}
