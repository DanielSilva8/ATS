package integracao;

import controllers.Sessao;
import controllers.UtilizadorController;
import controllers.ViagemController;
import exceptions.EmailAlreadyInUseException;
import exceptions.EmailDoesNotExistException;
import models.DB;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Ator;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import utils.Coordenada;
import models.veiculos.Carro;
import models.veiculos.Mota;
import models.veiculos.Veiculo;
import models.viagem.Viagem;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.*;

/**
 * Created by danys on 10-Dec-17.
 */
public class FuncionalidadesTestes {


    private Viagem v;
    private Viagem v1;
    private HashMap<String,Veiculo> veiculos;
    private Utilizadores utilizadores;
    Carro c;
    Carro d;
    Carro e;
    Motorista m;
    Motorista m2;
    Motorista m3;
    Cliente cliente;

    @BeforeClass
    public  void before() {
        utilizadores = new Utilizadores();
        veiculos = new HashMap<>();
        try {
            c = new Carro(10,10,10,"closer",new Coordenada(3,3),true);
            d = new Carro(10,10,10,"closer2",new Coordenada(6,6),true);
            e = new Carro(10,10,10,"closer3",new Coordenada(0,0),true);
            m = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1", c);
            m2 = new Motorista("email2",  "nome1",  "pw1",  "morada1",  "dDN1", d);
            m3 = new Motorista("email3",  "nome1",  "pw1",  "morada1",  "dDN1", e);
            cliente = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN");

            m.setDisponibilidade(true);
            m2.setDisponibilidade(true);
            m3.setDisponibilidade(true);


            utilizadores.adiciona(cliente);
            utilizadores.adiciona(m);
            utilizadores.adiciona(m2);
            utilizadores.adiciona(m3);

            veiculos.put("closer", c);
            veiculos.put("closer2", d);
            veiculos.put("closer2", e);
        } catch (EmailAlreadyInUseException e) {
            e.printStackTrace();
        }
        DB.resetParaTestes(utilizadores, veiculos);

    }

    @Test
    public void chamarViaturaTest() {

        Sessao.criar(cliente);
        Viagem v = new Viagem(new Coordenada(0,0), new Coordenada(0,0), 0, m.getMail(), new GregorianCalendar(), 0, 0);
        Motorista aux = m.clone();

        new ViagemController().guardaViagem(new Coordenada(0,0), new Coordenada(0,0), 0, m, 0, 0, 0, 0, 0,0);

        assertTrue(cliente.getHistorico().contains(v));
        assertTrue(m.getHistorico().contains(v));
        assertFalse(m.equals(aux));

    }


    @Test
    public void closerMotoristaTest() {
       assertEquals(new ViagemController().closerMotorista(new Coordenada(2,2),2), "email1" );
        try {
            ((Motorista) DB.getUtilizadores().getAtor("email1")).setDisponibilidade(false);
        } catch (EmailDoesNotExistException e1) {
            e1.printStackTrace();
        }
       assertEquals(new ViagemController().closerMotorista(new Coordenada(2,2),2), "email3" );
    }

    @Test
    public void associarViaturaTest() {

        Sessao.criar(m);

        String data = "0";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            new UtilizadorController().associarViatura();
        } catch (Exception e) {}finally {
            System.setIn(stdin);
        }

        assertNull(m.getVeiculo());
        m.setVeiculo(c);
        assertTrue(m.getVeiculo().equals(c));
    }

    @AfterClass
    public void after() {
        String data = "0";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            new ViagemController().realizarViagem(new Coordenada(0,0), new Coordenada(0,0), 2);
        } catch (Exception e) {}finally {
            System.setIn(stdin);
        }
    }
}
