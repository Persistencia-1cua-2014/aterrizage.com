package ar.edu.unq.persistencia1.services.neo4j.operations;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import ar.edu.unq.persistencia1.services.neo4j.NeoOperation;
import ar.edu.unq.persistencia1.services.neo4j.RelTypes;
import org.neo4j.graphdb.Node;

public class SendMessage implements NeoOperation<Node> {


    private Usuario sender;
    private Usuario receiver;
    private String message;

    public SendMessage(Usuario usuario,Usuario receiver,String message){
        this.sender = usuario;
        this.receiver = receiver;
        this.message = message;
    }

    @Override
    public Node execute() {
        Node senderNode = NeoManager.runInSession(new GetUserNode(sender));
        Node receiverNode = NeoManager.runInSession(new GetUserNode(receiver));

        Node messageNode = NeoManager.getSession().createNode();
        messageNode.setProperty("message", this.message);


        messageNode.createRelationshipTo(senderNode, RelTypes.SENDER);
        messageNode.createRelationshipTo(receiverNode, RelTypes.RECEIVER);
        return messageNode;
    }




}
