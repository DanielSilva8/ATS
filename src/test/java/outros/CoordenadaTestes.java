package outros;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Coordenada;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
/**
 * Created by danys on 09-Dec-17.
 */
public class CoordenadaTestes {

    Coordenada c1;
    Coordenada c2;
    Coordenada c3;
    Coordenada c4;
    Coordenada c5;

    @BeforeClass
    public void before(){

        c1 = new Coordenada();
        c2 = new Coordenada(4,3);
        c3 = new Coordenada(c2);
        c4 = new Coordenada(4,4);
        c5 = new Coordenada(c2.getX(),c2.getY());
    }

    @Test
    public void testeCompareTo() {
        assertEquals(c2.compareTo(c3), 0);
        assertEquals(c2.compareTo(c4), 1);
        assertEquals(c4.compareTo(c2), -1);
        assertEquals(c2.compareTo(c1), -1);
    }

    @Test
    public void testeEqualsObject() {
        Object o = new Coordenada(4,3);
        Object o1 = new ArrayList<>();

        assertFalse(c2.equals(null));
        assertTrue(c2.equals(c2));
        assertTrue(c2.equals(o));
        assertFalse(c2.equals(o1));
    }

    @AfterClass
    public void after() {
        c1.hashCode();
        c1.toString();
    }
}
