package viagem;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Coordenada;

import static org.testng.Assert.assertEquals;



public class ViagemTestes {


    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);

    private Viagem viagem1 = new Viagem();
    Viagem viagem2 = new Viagem();
    Viagem viagem3 = new Viagem();
    Viagem viagem4 = new Viagem();


    @BeforeClass
    public void before() {
        viagem1.setcinicial(cinicial1);
        viagem1.setcfinal(cfinal1);
        viagem1.setMail("mail1");
        viagem1.setTempo(200);
        viagem1.setDesvio(3);
        viagem1.setPreco(100);

        viagem2.setcinicial(cinicial1);
        viagem2.setcfinal(cfinal1);
        viagem2.setMail("mail2");
        viagem2.setTempo(400);
        viagem2.setDesvio(4);
        viagem2.setPreco(200);

        viagem3.setcinicial(cinicial1);
        viagem3.setcfinal(cfinal1);
        viagem3.setMail("mail1");
        viagem3.setTempo(200);
        viagem3.setDesvio(3);
        viagem3.setPreco(100);

        viagem4.setcinicial(cinicial1);
        viagem4.setcfinal(cfinal1);
        viagem4.setMail("mail4");
        viagem4.setTempo(100);
        viagem4.setDesvio(2);
        viagem4.setPreco(50);
    }

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


}
