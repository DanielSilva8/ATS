

import exceptions.EmailAlreadyInUseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import utils.Coordenada;
import models.veiculos.Carro;
import models.veiculos.Veiculo;
import models.viagem.Viagem;

import java.io.ByteArrayInputStream;
import java.util.GregorianCalendar;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;


public class AppTestes {


    private Motorista motorista = new Motorista();
    private Cliente cliente = new Cliente();

    private TreeSet<Viagem> viagens = new TreeSet();
    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);
    private Viagem viagem1 = new Viagem(cinicial1, cfinal1, 200, "mail1", new GregorianCalendar(1995, 11, 10), 100, 3);
    private Veiculo veiculo = new Carro(120, 5, 80, "99-AA-99", cinicial1, true);
    private  Utilizadores a = new Utilizadores();
    private App app = new App();

    @BeforeClass
    public void before(){

        viagens.add(viagem1);
        this.cliente = new Cliente("mail2", "Goncalves", "qwerty", "Braga", "29-09-1995", viagens, 11);
//        this.motorista = new Motorista("email", "Leonel", "password", "Portugal", "29-09-1995", viagens, 80, 80, 2, 1200, true, veiculo);

        try{
//            a.adiciona(this.motorista);
            a.adiciona(this.cliente);
        }catch (EmailAlreadyInUseException e){
            e.getStackTrace();
        }
    }

    @Test
    public void runTeste(){
        String data2 = "0\r\n";
        try {
            System.setIn(new ByteArrayInputStream(data2.getBytes()));
           // app.run();

        } finally {
            System.setIn(System.in);
        }
    }

    @AfterClass
    public void after(){
        String qq = "";
    }
}