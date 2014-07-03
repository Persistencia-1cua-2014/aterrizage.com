package neo_integration.operations;

import ar.edu.unq.persistencia1.services.neo4j.NeoManager;
import junit.framework.TestCase;
import org.junit.Before;


public class SaveNodeTest extends TestCase{

    @Before
    public void setUp(){
        NeoManager.runInSession(new ClearDatabase());
    }

    public void testSetProperty(){
        String expected = NeoManager.runInSession(new CreateNode());
        String returned = NeoManager.runInSession(new GetProperty());
        assertEquals(expected, returned);
    }


}
