package utilizador;


import exceptions.InvalidIntervalException;
import exceptions.NenhumaViagemException;
import models.utilizadores.Ator;
import models.utilizadores.Cliente;
import models.viagem.Viagem;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rapl.jRAPLWorker;
import utils.Coordenada;


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

        jRAPLWorker.start("Ator,ViagemEntreDatasTeste");
        try {
            assertEquals(ator.viagensEntreDatas(a, b), viagensexpected);
        }catch (InvalidIntervalException e){
            e.getStackTrace();
        }
        jRAPLWorker.end();

    }

    @Test
    public void maiorDesvioTeste(){

        jRAPLWorker.start("Ator,maiorDesvioTeste");
        try {
            assertEquals(ator.maiorDesvio(), viagem3);
        }catch (NenhumaViagemException e){
            e.getStackTrace();
        }
        jRAPLWorker.end();
    }
    @Test
    public void equalsTeste(){

        Ator a = this.ator.clone();
        Ator b = new Cliente("mail2", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);

        jRAPLWorker.start("Ator,equalsTeste");
        assertEquals(ator.equals(a), true);
        assertEquals(ator.equals(b), false);
        jRAPLWorker.end();
    }

    @Test
    public void compareToTest() {

        Ator b = new Cliente("mail", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);
        Ator c = new Cliente("mail11", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);
        jRAPLWorker.start("Cliente,compareToTest");
        assertEquals(ator.compareTo(ator), 0);
        assertEquals(ator.compareTo(b), 1);
        assertEquals(ator.compareTo(c), -1);
        jRAPLWorker.end();

    }
    @AfterClass
    public void after() {
        int i = this.ator.hashCode();
        String g = this.ator.toString();
    }
}
