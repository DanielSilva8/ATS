package utilizador;

import exceptions.ValueOutOfBoundsException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Motorista;
import rapl.jRAPLWorker;
import utils.Coordenada;
import models.veiculos.Carro;
import models.veiculos.Veiculo;
import models.viagem.Viagem;

import java.util.GregorianCalendar;
import java.util.TreeSet;

import static org.testng.Assert.*;

public class MotoristaTestes {

    Motorista motorista = new Motorista();
    private TreeSet<Viagem> viagens = new TreeSet();

    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);
    private Viagem viagem1 = new Viagem(cinicial1, cfinal1, 200, "mail1", new GregorianCalendar(1995, 11, 10), 100, 3);
    private Viagem viagem2 = new Viagem(cinicial1, cfinal1, 400, "mail2", new GregorianCalendar(1995, 11, 12), 200, 4);
    private Viagem viagem3 = new Viagem(cinicial1, cfinal1, 400, "mail2", new GregorianCalendar(1995, 11, 15), 200, 5);
    Veiculo veiculo = new Carro(120, 5, 80, "99-AA-99", cinicial1, true);

    @BeforeClass
    public void before() {

        viagens.add(viagem1);
        viagens.add(viagem2);
        viagens.add(viagem3);
        this.motorista = new Motorista("email", "Leonel", "password", "Portugal", "29-09-1995", viagens, 80, 80, 2, 1200, true, veiculo);
    }

    @Test
    public void AtualizaClassificacaoTeste(){
        try{

            int expected = ((this.motorista.getClassificacao()*this.motorista.getNumClassi())+ 90 )/(this.motorista.getNumClassi()+1);
            jRAPLWorker.start("Motorista,AtualizaClassificacaoTeste");
            this.motorista.atualizaClassificacao(90);
            jRAPLWorker.end();
            assertEquals(this.motorista.getClassificacao(),expected);

        }catch (ValueOutOfBoundsException e){
            e.getStackTrace();
        }
    }

    @Test
    public void tempoViagemTeste(){


        double expected = ((double)200/this.motorista.getVeiculo().getVelocidadeMedia());

        jRAPLWorker.start("Motorista,tempoViagemTeste");
        assertEquals(this.motorista.tempoViagem(200),expected);
        jRAPLWorker.end();
    }

    @Test
    public void totalfaturadoTeste(){

        jRAPLWorker.start("Motorista,totalfaturadoTeste");
        assertEquals(this.motorista.totalFaturado(),500.0);
        jRAPLWorker.end();
    }

    @Test
    public void totalfaturadoDataTeste(){
        GregorianCalendar a = new GregorianCalendar(1995,11,9);
        GregorianCalendar b = new GregorianCalendar(1995,11,13);
        jRAPLWorker.start("Motorista,totalfaturadoDataTeste");
        assertEquals(this.motorista.totalFaturado(a,b),300.0);
        jRAPLWorker.end();
    }
    @Test
    public void EqualsTeste(){

        jRAPLWorker.start("Motorista,EqualsTeste");
        assertTrue(this.motorista.equals(this.motorista));
        assertFalse(this.motorista.equals(null));
        jRAPLWorker.end();
    }
    @Test
    public void CompareToTeste(){

        Motorista m1 = this.motorista.clone();
        Motorista m2 = new Motorista("email", "Leonel", "password", "Portugal", "29-09-1995", viagens, 80, 85, 2, 1200, true, veiculo);
        Motorista m3 = new Motorista("email", "Leonel", "password", "Portugal", "29-09-1995", viagens, 80, 75, 2, 1200, true, veiculo);

        jRAPLWorker.start("Motorista,CompareToTeste");
        assertEquals(this.motorista.compareTo(m1),0);
        assertEquals(this.motorista.compareTo(m2),-1);
        assertEquals(this.motorista.compareTo(m3),1);
        jRAPLWorker.end();
    }
    @AfterClass
    public void after() {
        int i = this.motorista.hashCode();
       String g =  this.motorista.toString();
        Coordenada coordenada = new Coordenada(3,3);
        this.motorista.atualizaDados(coordenada,120, 80, 80);

    }
}
