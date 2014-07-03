package ar.edu.unq.persistencia1.services.neo4j;

import org.neo4j.graphdb.RelationshipType;

public enum RelTypes implements RelationshipType{
    SENDER, RECEIVER, KNOWS
}