package veiculo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Coordenada;
import models.veiculos.Carro;
import rapl.jRAPLWorker;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by danys on 09-Dec-17.
 */
public class CarroTestes {
    Carro m;
    Carro m1;
    Carro m2;

    @BeforeClass
    public void before(){
        m = new Carro();
        m1 = new Carro(10,10,10,"matricula",new Coordenada(3,5),false);
        m2 = new Carro(m1.clone());
    }

    @Test
    public void testeEqualsObject() {
        Object o = new Carro();
        Object o1 = new ArrayList<>();

        jRAPLWorker.start("Carro,testeEqualsObject");
        assertFalse(m2.equals(null));
        assertTrue(m2.equals(m2));
        assertFalse(m2.equals(o));
        assertFalse(m2.equals(o1));
        jRAPLWorker.end();
    }

    @AfterClass
    public void after() {
        m1.toString();
        m1.hashCode();
    }
}
