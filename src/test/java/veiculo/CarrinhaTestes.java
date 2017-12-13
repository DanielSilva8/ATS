package veiculo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Coordenada;
import models.veiculos.Carrinha;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by danys on 09-Dec-17.
 */
public class CarrinhaTestes {
    Carrinha m;
    Carrinha m1;
    Carrinha m2;

    @BeforeClass
    public void before(){
        m = new Carrinha();
        m1 = new Carrinha(10,10,10,"matricula",new Coordenada(3,5),false);
        m2 = new Carrinha(m1.clone());
    }

    @Test
    public void testeEqualsObject() {
        Object o = new Carrinha();
        Object o1 = new ArrayList<>();

        assertFalse(m2.equals(null));
        assertTrue(m2.equals(m2));
        assertFalse(m2.equals(o));
        assertFalse(m2.equals(o1));
    }

    @AfterClass
    public void after() {
        m1.toString();
        m1.hashCode();
    }
}
