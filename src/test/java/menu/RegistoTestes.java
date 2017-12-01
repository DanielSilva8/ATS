package menu;

import exceptions.EmailDoesNotExistException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilizadores.Cliente;
import utilizadores.Motorista;
import utilizadores.Utilizadores;
import veiculos.Carrinha;
import veiculos.Carro;
import veiculos.Mota;
import veiculos.Veiculo;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by danys on 01-Dec-17.
 */
public class RegistoTestes {

    private HashMap<String,Veiculo> veiculos;
    private Utilizadores utilizadores;
    private Registo registo;
    private Cliente cliente;
    private Motorista motorista;

    @BeforeClass
    public  void before() {
        utilizadores = new Utilizadores();
        veiculos = new HashMap<String, Veiculo>();
        registo = new Registo();

        cliente = new Cliente();
        motorista = new Motorista();
    }
    @Test
    public void testeRegistarMotorista() {
        String data = "email\r\nnome\r\npass\r\nmorada\r\n17-01-1995";

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarUtilizador(utilizadores, "motorista");

        } finally {
            System.setIn(System.in);
        }

        try {
            assertEquals(utilizadores.getAtor("email").getNome(), "nome");
            assertEquals(utilizadores.getAtor("email").getDataDeNascimento(), "17-01-1995");
            assertEquals(utilizadores.getAtor("email").getPassword(), "pass");
            assertEquals(utilizadores.getAtor("email").getMorada(), "morada");
            assertTrue(utilizadores.getAtor("email") instanceof Motorista);
        } catch (EmailDoesNotExistException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testeRegistarCliente() {
        String data = "email1\r\nnome1\r\npass1\r\nmorada1\r\n18-01-1995";

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarUtilizador(utilizadores, "cliente");

            assertEquals(utilizadores.getAtor("email1").getNome(), "nome1");
            assertEquals(utilizadores.getAtor("email1").getDataDeNascimento(), "18-01-1995");
            assertEquals(utilizadores.getAtor("email1").getPassword(), "pass1");
            assertEquals(utilizadores.getAtor("email1").getMorada(), "morada1");
            assertTrue(utilizadores.getAtor("email1") instanceof Cliente);

        } catch (EmailDoesNotExistException e) {
            e.printStackTrace();
        } finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void testeRegistarCarro() {
        String data = "1\r\n120\r\n1000\r\nmatricula\r\n";

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarVeiculo(veiculos);

            assertEquals(veiculos.get("matricula").getVelocidadeMedia(),120);
            assertEquals(veiculos.get("matricula").getPrecoBase(),Double.parseDouble("1000"));
            assertTrue(veiculos.get("matricula") instanceof Carro);

        } finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void testeRegistarCarrinha() {
        String data = "2\r\n120\r\n1000\r\nmatricula2\r\n";

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarVeiculo(veiculos);

            assertEquals(veiculos.get("matricula2").getVelocidadeMedia(),120);
            assertEquals(veiculos.get("matricula2").getPrecoBase(),Double.parseDouble("1000"));
            assertTrue(veiculos.get("matricula2") instanceof Carrinha);

        } finally {
            System.setIn(System.in);
        }
    }

    @Test
    public void testeRegistarMota() {
        String data = "3\r\n120\r\n1000\r\nmatricula3\r\n";

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarVeiculo(veiculos);

            assertEquals(veiculos.get("matricula3").getVelocidadeMedia(),120);
            assertEquals(veiculos.get("matricula3").getPrecoBase(),Double.parseDouble("1000"));
            assertTrue(veiculos.get("matricula3") instanceof Mota);

        } finally {
            System.setIn(System.in);
        }
    }
}
