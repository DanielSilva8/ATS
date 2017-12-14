package outros;

import exceptions.EmailAlreadyInUseException;
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

    private Motorista m;
    private Viagem v;
    private Viagem v1;
    private HashMap<String,Veiculo> veiculos;
    private Utilizadores utilizadores;
    @BeforeClass
    public  void before() {
        utilizadores = new Utilizadores();
        veiculos = new HashMap<String,Veiculo>();
        m = new Motorista("email2",  "nome1",  "pw2",  "morada1",  "dDN1", new Carro());
        v = new Viagem(new Coordenada(3,3), new Coordenada(4,4),3,"mail", new GregorianCalendar(2017, 12, 1), 10, 10.0);
        v1 = new Viagem(new Coordenada(3,3), new Coordenada(4,4),3,"mail", new GregorianCalendar(2017, 12, 1), 10, 10.0);

        try {
            utilizadores.adiciona(m);
        } catch (EmailAlreadyInUseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void chamarViaturaTest() {
        Carro c =  new Carro(10,10,10,"chamarv",new Coordenada(3,3),false);
        Motorista aux = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1", new Mota());

        aux.setVeiculo(c);
        try {
            utilizadores.adiciona(aux);
        } catch (EmailAlreadyInUseException e) {
            e.printStackTrace();
        }
        String data = "3\r\n3\r\n4\n4\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Set<Motorista> motoristas = new TreeSet<>();
        for(Ator u:utilizadores.getUtilizadores().values()){
            if(u instanceof Motorista)
                motoristas.add((Motorista) u);
        }
     //   login.setMotoristas(motoristas);
     //   login.chamarViatura(new Cliente(), utilizadores, 2);
    }


    @Test
    public void closerMotoristaTest() {

        Carro c =  new Carro(10,10,10,"closer",new Coordenada(3,3),true);
        Set<Motorista> motoristas = new TreeSet<>();
        m.setVeiculo(c);
        m.setDisponibilidade(true);
        Utilizadores users = new Utilizadores();
        try {
            users.adiciona(m);
        } catch (EmailAlreadyInUseException e) {
            e.printStackTrace();
        }
        for(Ator u:users.getUtilizadores().values()){
            if(u instanceof Motorista)
                motoristas.add((Motorista) u);
        }
      //  login.setMotoristas(motoristas);

      //  assertEquals(login.closerMotorista(new Coordenada(3,3),2), m.getMail());
    }
    @Test
    public void associarViaturaTest() {
        Carro c =  new Carro(10,10,10,"matricula3",new Coordenada(3,5),true);
        Carro c1 =  new Carro(10,10,10,"matricula2",new Coordenada(3,5),true);
        Carro c2 =  new Carro(10,10,10,"matricula",new Coordenada(3,5),false);

        String data = "matricula\n";
        veiculos.put("matricula3", c);
        veiculos.put("matricula2",c1);

      //  login.associarViatura(m, veiculos);
        assertNull(m.getVeiculo());

        veiculos.put("matricula",c2);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
      //  login.associarViatura(m, veiculos);
        System.setIn(new ByteArrayInputStream(data.getBytes()));
      //  login.associarViatura(m, veiculos);


        assertEquals(m.getVeiculo().getMatricula(), "matricula" );
        assertNotEquals(m.getVeiculo().getMatricula(), "matricula2" );

        data = "matricula3\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
     //   login.associarViatura(m, veiculos);
        assertNull(m.getVeiculo());

        data = "matrtgicula\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
     //   login.associarViatura(m, veiculos);
        assertNull(m.getVeiculo());

    }
    @Test
    public void realizarViagemTest() {
        Carro c =  new Carro(10,10,10,"matricula3",new Coordenada(3,5),true);
        Cliente cliente = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN");
        Motorista motorista = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1", c);
        motorista.setDisponibilidade(true);
        Coordenada inicio = new Coordenada(3,3);
        Coordenada fim = new Coordenada(4,4);
        String data = "3\r\n";
        InputStream old = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
      //  login.realizarViagem(cliente, motorista, inicio.distancia(motorista.getVeiculo().getCoordenadas()), inicio, fim);

        assertNotNull(cliente.getHistorico());
        assertNotNull(motorista.getHistorico());
        assertTrue(cliente.getHistorico().size() > 0);
        assertTrue(motorista.getHistorico().size() > 0);
        System.setIn(old);
    }


    @AfterClass
    public void after() {

    }
}
