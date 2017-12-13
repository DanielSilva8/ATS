package veiculo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Coordenada;
import models.veiculos.Carrinha;
import models.veiculos.Carro;
import models.veiculos.Mota;
import models.veiculos.Veiculo;

import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by danys on 09-Dec-17.
 */
public class VeiculoTestes {

    Veiculo v;
    Veiculo v1;
    Veiculo v2;
    Veiculo v3;

    @BeforeClass
    public void before() {

        v = new Carro();
        v.setCoordenadas(new Coordenada(3,4));
        v.setFiabilidade(100);
        v.setMatricula("matricula");
        v.setOcupado(false);
        v.setPrecoBase(100);
        v.setVelocidadeMedia(120);

        v1 = new Carro((Carro) v);
        v2 = new Mota();
        v3 = new Carrinha();
    }

    @Test
    public void testeEqualsObject() {
        Veiculo o = new Carro((Carro) v1);
        Object o1 = new ArrayList<>();

        assertFalse(v.equals(null));
        assertTrue(v.equals(v.clone()));
        assertTrue(v.equals(v1));
        assertTrue(v.equals(o));
        assertTrue(v2.equals(v2));
        assertTrue(v3.equals(v3));
        assertFalse(v3.equals(o));
        assertFalse(v2.equals(o1));
    }

    @Test
    public void testeCompareTo() {

        assertEquals(v.compareTo(v1),0);
        assertNotEquals(v.compareTo(v2),0);
    }

    @AfterClass
    public void after() {
        v.toString();
        v.hashCode();
        v1.setOcupado(true);
        v1.hashCode();
    }
}
