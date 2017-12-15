package viagem;

import controllers.ViagemController;
import models.viagem.Viagem;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Coordenada;

import java.util.GregorianCalendar;

import static org.testng.Assert.assertEquals;



public class ViagemTestes {
    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);
    private Viagem viagem1 = new Viagem(cinicial1,cfinal1,200,"mail1", new GregorianCalendar(1995,11,10),100,3);
    private Viagem viagem2 = new Viagem(cinicial1,cfinal1,400,"mail22", new GregorianCalendar(1995,11,12),200,4);
    private Viagem viagem3 = viagem1.clone();
    private Viagem viagem4 = new Viagem(cinicial1,cfinal1,100,"mail", new GregorianCalendar(1995,11,15),50,2);


    @Test
    public void TestCompare() {
        assertEquals(viagem1.compareTo(viagem2), -1);
        assertEquals(viagem1.compareTo(viagem3), 0);
        assertEquals(viagem1.compareTo(viagem4), 1);
    }

    @Test
    public void TestEquals() {
        assertEquals(viagem1.equals(viagem1), true);
        assertEquals(viagem1.equals(viagem2), false);
    }

    @AfterClass
    public void after() {
        this.viagem1.hashCode();
        this.viagem1.toString();
        viagem1.setcfinal(new Coordenada(0,0));
        viagem1.setcinicial(new Coordenada(0,0));
        viagem1.setTempo(0);
        viagem1.setDesvio(0);
        viagem1.setMail("f");
        viagem1.setPreco(0);
        new Viagem();
    }

}
