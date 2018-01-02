package utilizador;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Cliente;
import rapl.jRAPLWorker;
import utils.Coordenada;
import models.viagem.Viagem;

import java.util.GregorianCalendar;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class ClienteTestes {


    private Cliente cliente = new Cliente();
    private TreeSet<Viagem> viagens = new TreeSet();

    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);
    private Viagem viagem1 = new Viagem(cinicial1,cfinal1,200,"mail1", new GregorianCalendar(1995,11,10),100,3);
    private Viagem viagem2 = new Viagem(cinicial1,cfinal1,400,"mail2", new GregorianCalendar(1995,11,12),200,4);
    private Viagem viagem3 = new Viagem(cinicial1,cfinal1,400,"mail2", new GregorianCalendar(1995,11,15),200,5);

    @BeforeClass
    public void before() {


        viagens.add(viagem1);
        viagens.add(viagem2);
        viagens.add(viagem3);

        this.cliente= new Cliente("mail1", "Leonel", "qwerty", "Braga", "29-09-1995", viagens, 11);

    }

    @Test
    public void EqualsTestes(){
        Cliente a = this.cliente.clone();
        Cliente b = new Cliente("mail2", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);

        jRAPLWorker.start("Cliente,EqualsTestes");
        assertEquals(this.cliente.equals(a), true);
        assertEquals(this.cliente.equals(b), false);
        jRAPLWorker.end();

    }

    @Test
    public void compareToTest() {

        Cliente b = new Cliente("mail", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);
        Cliente c = new Cliente("mail11", "Goncalves", "qwerty", "Portugal", "20-09-1995", viagens, 11);
        Cliente d = this.cliente.clone();

        jRAPLWorker.start("Cliente,compareToTest");
        assertEquals(this.cliente.compareTo(d), 0);
        assertEquals(this.cliente.compareTo(b), 1);
        assertEquals(this.cliente.compareTo(c), -1);
        jRAPLWorker.end();

    }
    @AfterClass
    public void after() {
        int i = this.cliente.hashCode();
        String s = this.cliente.toString();
    }
}
