package menu;

import exceptions.EmailDoesNotExistException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "registoCliente")
    public static Object[][] credentialsC() {
        return new Object[][] { { "email1", "nome", "pass", "morada", "17-01-1995", ""},  { "email1", "nome", "pass", "morada", "17-01-1995", "email11"}};
    }

    @DataProvider(name = "registoVeiculo")
    public static Object[][] credentialsV() {
        return new Object[][] { { "1", "120", "1000", "matricula", ""},   { "1", "120", "1000", "matricula", "newMatricula"},  { "9\n1", "120", "1000", "matricdcla2", ""},  { "4\n1", "120", "1", "matricula3", ""}};
    }


    @BeforeClass
    public  void before() {
        utilizadores = new Utilizadores();
        veiculos = new HashMap<String, Veiculo>();
        registo = new Registo();
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
    @Test(dataProvider = "registoCliente")
    public void testeRegistarCliente(String email, String nome,String pass, String morada, String nascimento, String newEmail) {
        String data = email+"\r\n"+ nome + "\r\n" + pass + "\r\n" + morada + "\r\n" + nascimento + "\n" + newEmail;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarUtilizador(utilizadores, "cliente");

            assertEquals(utilizadores.getAtor(email).getNome(), nome);
            assertEquals(utilizadores.getAtor(email).getDataDeNascimento(), nascimento);
            assertEquals(utilizadores.getAtor(email).getPassword(), pass);
            assertEquals(utilizadores.getAtor(email).getMorada(), morada);
            assertTrue(utilizadores.getAtor(email) instanceof Cliente);

        } catch (EmailDoesNotExistException e) {
            e.printStackTrace();
        } finally {
            System.setIn(System.in);
        }
    }

    @Test(dataProvider = "registoVeiculo")
    public void testeRegistarCarro(String menu, String vm,String preco, String matricula, String novamatricula ) {
        String data = menu + "\r\n" + vm + "\r\n" + preco+ "\r\n" + matricula + "\r\n" + novamatricula;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarVeiculo(veiculos);

            assertEquals(veiculos.get(matricula).getVelocidadeMedia(),Integer.parseInt(vm));
            assertEquals(veiculos.get(matricula).getPrecoBase(),Double.parseDouble(preco));
            assertTrue(veiculos.get(matricula) instanceof Carro);

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
        String data = "3\r\n120\r\n1000\r\nmatricula33\r\n";

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.registarVeiculo(veiculos);

            assertEquals(veiculos.get("matricula33").getVelocidadeMedia(),120);
            assertEquals(veiculos.get("matricula33").getPrecoBase(),Double.parseDouble("1000"));
            assertTrue(veiculos.get("matricula33") instanceof Mota);

        } finally {
            System.setIn(System.in);
        }
    }

    @AfterClass
    public void after() {
        String data = "1";
        String data2 = "2";
        String data3 = "3";
        String data4 = "4";


        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            registo.executa(utilizadores, veiculos);
            System.setIn(new ByteArrayInputStream(data2.getBytes()));
            registo.executa(utilizadores, veiculos);
            System.setIn(new ByteArrayInputStream(data3.getBytes()));
            registo.executa(utilizadores, veiculos);
            System.setIn(new ByteArrayInputStream(data4.getBytes()));
            registo.executa(utilizadores, veiculos);

        } catch (Exception e) {} finally {
            System.setIn(System.in);
        }
    }
}
