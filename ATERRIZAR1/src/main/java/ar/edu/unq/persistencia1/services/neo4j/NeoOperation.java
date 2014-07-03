package ar.edu.unq.persistencia1.services.neo4j;


public interface NeoOperation<T> {
    public T execute();
}
