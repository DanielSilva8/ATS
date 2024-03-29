package utilizador;

import exceptions.EmailAlreadyInUseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import rapl.jRAPLWorker;
import utils.Coordenada;
import models.veiculos.Carro;
import models.veiculos.Veiculo;
import models.viagem.Viagem;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class UtilizadorTestes {


    private Motorista motorista = new Motorista();
    private Cliente cliente = new Cliente();

    private TreeSet<Viagem> viagens = new TreeSet();
    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);
    private Viagem viagem1 = new Viagem(cinicial1, cfinal1, 200, "mail1", new GregorianCalendar(1995, 11, 10), 100, 3);
    private Veiculo veiculo = new Carro(120, 5, 80, "99-AA-99", cinicial1, true);
   private  Utilizadores a = new Utilizadores();


    @BeforeClass
    public void before() {

        viagens.add(viagem1);
        this.cliente = new Cliente("mail2", "Goncalves", "qwerty", "Braga", "29-09-1995", viagens, 11);
        this.motorista = new Motorista("email", "Leonel", "password", "Portugal", "29-09-1995", viagens, 80, 80, 2, 1200, true, veiculo);

        try{
            a.adiciona(this.motorista);
            a.adiciona(this.cliente);
        }catch (EmailAlreadyInUseException e){
            e.getStackTrace();
        }


    }

    @Test
    public void top10ClientesGastadoresTestes() {

        jRAPLWorker.start("Utilizador,top10ClientesGastadoresTestes");
        List<Cliente> clientes = a.top10ClientesGastadores();
        jRAPLWorker.end();
        List<Cliente> expected = new ArrayList<>();
        expected.add(this.cliente);

            assertEquals(clientes,expected);
    }

    @Test
    public void top5MotoristasTestes() {

        jRAPLWorker.start("Utilizador,top5MotoristasTestes");
        List<Motorista> motoristas = a.top5MotoristasComMaiorDesvio();
        jRAPLWorker.end();
        List<Motorista> expected = new ArrayList<>();
        expected.add(this.motorista);

        assertEquals(motoristas,expected);

    }

    @Test
    public void EqualsTeste(){
        Utilizadores utilizador = new Utilizadores();
        Utilizadores b = a.clone();

        jRAPLWorker.start("Utilizador,EqualsTeste");
        assertTrue(this.a.equals(b));
        assertFalse(this.a.equals(null));
        assertFalse(this.a.equals(utilizador));
        jRAPLWorker.end();
    }

    @Test
    public void CompareToTeste(){
        Utilizadores utilizador1 = a.clone();
        Utilizadores utilizador2 = new Utilizadores();
        Utilizadores utilizador3 = new Utilizadores();
        Cliente cliente2 = new Cliente("mail22", "Goncalves", "qwerty", "Braga", "29-09-1995", viagens, 11);
        Cliente cliente3 = new Cliente("mail", "Goncalves", "qwerty", "Braga", "29-09-1995", viagens, 11);

        try{
            utilizador2.adiciona(cliente2);
            utilizador3.adiciona(cliente3);

        }catch (EmailAlreadyInUseException e){
            e.getStackTrace();
        }

        jRAPLWorker.start("Utilizador,CompareToTeste");
        assertEquals(this.a.compareTo(utilizador1),0);
        assertEquals(this.a.compareTo(utilizador2),-1);
        assertEquals(this.a.compareTo(utilizador3),1);
        jRAPLWorker.end();
    }

    @AfterClass
    public void after() {
        motorista.hashCode();
        motorista.toString();
    }
}
