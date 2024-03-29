package veiculo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Coordenada;
import models.veiculos.Mota;
import rapl.jRAPLWorker;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by danys on 09-Dec-17.
 */
public class MotaTestes {

    Mota m;
    Mota m1;
    Mota m2;

    @BeforeClass
    public void before(){
        m = new Mota(10,10,10,"matricula2",new Coordenada(3,5),false);
        m1 = new Mota(10,10,10,"matricula",new Coordenada(3,5),false);
        m2 = new Mota(m1);
    }

    @Test
    public void testeEqualsObject() {
        Object o = new Mota();
        Object o1 = new ArrayList<>();

        jRAPLWorker.start("Mota,testeEqualsObject");
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
