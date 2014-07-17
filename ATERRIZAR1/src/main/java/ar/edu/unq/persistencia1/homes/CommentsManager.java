package ar.edu.unq.persistencia1.homes;

import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.enterprise.Lugar;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

import static ar.edu.unq.persistencia1.services.mongodb.MongoManager.getDatabase;

public class CommentsManager {


    public com.mongodb.DBObject getUser(Usuario user){
        DBCollection table = getDatabase("mongoDataBase").getCollection("destination");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("usuario", user.getNombreDeUsuario());

        DBCursor cursor = table.find(searchQuery);

       if (cursor.hasNext()) {
            return (cursor.next());
        }

        BasicDBObject usuario = new BasicDBObject();
        usuario.put("usuario",user.getNombreDeUsuario());
        BasicDBList dbl = new BasicDBList();
        usuario.put("destinos",dbl);
        table.insert(usuario);

        return usuario;

    }


    public void addDestination(Usuario user, Lugar lugar){

        DBCollection table = getDatabase("mongoDataBase").getCollection("destination");

        BasicDBObject query = new BasicDBObject();


        query.put("destino", lugar.getNombre());
        DBObject u = this.getUser(user);

        BasicDBList l = (BasicDBList)u.get("destinos");
        l.add(query);

        table.save(u);
    }


    public List<String> getDestinations(Usuario user){
        DBObject u = this.getUser(user);
        ArrayList<DBObject> l = (ArrayList<DBObject>)u.get("destinos");

        List<String> destinations =  new ArrayList<String>();

        for(DBObject o : l){
            destinations.add((String) o.get("destino"));
        }

        return destinations;
    }


    public void setVisibility(Usuario user,Lugar destino,String visibility){
        this.getUser(user);

        DBCollection table = getDatabase("mongoDataBase").getCollection("destination");

        BasicDBObject query = new BasicDBObject();

        query.put("usuario", user.getNombreDeUsuario());
        query.put("destinos.destino", destino.getNombre());


        BasicDBObject queryUpdate = new BasicDBObject();

        queryUpdate.put("destinos.$.visibility", visibility);

        BasicDBObject updateCommand = new BasicDBObject();
        updateCommand.put("$set", queryUpdate);

        table.update(query, updateCommand);

    }


    public void setPrivate(Usuario user,Lugar destino){
        this.setVisibility(user,destino,"private");

    }

    public boolean isVisibility(Usuario user, Lugar destino,String visibility){

        DBCollection table = getDatabase("mongoDataBase").getCollection("destination");

        BasicDBObject query = new BasicDBObject();

        query.put("usuario", user.getNombreDeUsuario());

        query.put("destinos.destino", destino.getNombre());
        query.put("destinos.visibility", visibility);

        return table.find(query).length() > 0;

    }
}
