package utilizador;


import exceptions.InvalidIntervalException;
import exceptions.NenhumaViagemException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilizadores.Ator;
import utilizadores.Cliente;
import utils.Coordenada;
import viagem.Viagem;


import java.util.*;

import static org.testng.Assert.assertEquals;


public class AtorTestes {

   private  TreeSet<Viagem> viagens = new TreeSet();

    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);
    private Viagem viagem1 = new Viagem(cinicial1,cfinal1,200,"mail1", new GregorianCalendar(1995,11,10),100,3);
   private Viagem viagem2 = new Viagem(cinicial1,cfinal1,400,"mail2", new GregorianCalendar(1995,11,12),200,4);
   private Viagem viagem3 = new Viagem(cinicial1,cfinal1,400,"mail2", new GregorianCalendar(1995,11,15),200,5);


   private Ator ator = new Cliente();


    @BeforeClass
    public void before() {

       viagens.add(viagem1);
        viagens.add(viagem2);
        viagens.add(viagem3);


        this.ator = new Cliente("mail1", "Leonel", "qwerty", "Braga", "29-09-1995", viagens, 11);


    }

    @Test
    public void ViagemEntreDatasTeste(){
        GregorianCalendar a = new GregorianCalendar(1995,11,9);
        GregorianCalendar b = new GregorianCalendar(1995,11,13);

        TreeSet<Viagem> viagensexpected = new TreeSet<>();
        viagensexpected.add(viagem1);
        viagensexpected.add(viagem2);

        try {
            assertEquals(ator.viagensEntreDatas(a, b), viagensexpected);
        }catch (InvalidIntervalException e){
            e.getStackTrace();
        }

    }

    @Test
    public void maiorDesvioTeste(){

        try {
            assertEquals(ator.maiorDesvio(), viagem3);
        }catch (NenhumaViagemException e){
            e.getStackTrace();
        }
    }
    @Test
    public void equalsTeste(){

    Ator a = new Cliente("mail1", "Leonel", "qwerty", "Braga", "29-09-1995", viagens, 11);
    Ator b = new Cliente("mail2", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);
        assertEquals(ator.equals(a), true);
        assertEquals(ator.equals(b), false);

    }

    @Test
    public void compareToTest() {

        Ator b = new Cliente("mail", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);
        Ator c = new Cliente("mail11", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);
        assertEquals(ator.compareTo(ator), 0);
        assertEquals(ator.compareTo(b), 1);
        assertEquals(ator.compareTo(c), -1);

    }
}
