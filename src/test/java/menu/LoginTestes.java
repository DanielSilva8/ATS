package menu;

import exceptions.EmailAlreadyInUseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilizadores.Cliente;
import utilizadores.Motorista;
import utilizadores.Utilizadores;
import veiculos.Mota;
import veiculos.Veiculo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.HashMap;
import static org.testng.Assert.assertTrue;

/**
 * Created by danys on 01-Dec-17.
 */
public class LoginTestes {

    private HashMap<String,Veiculo> veiculos;
    private Utilizadores utilizadores;
    private Cliente cliente;
    private Motorista motorista;
    private Login login = new Login();

    @DataProvider(name = "loginCliente")
    public static Object[][] credentialsC() {
        return new Object[][] { { "email", "pw", "Login efetuado com sucesso!" }, { "email2", "pw" ,"Dados de Login inválidos!"}, { "email", "pw2" ,"Dados de Login inválidos!"}};
    }

    @DataProvider(name = "loginMotorista")
    public static Object[][] credentialsM() {
        return new Object[][] { { "email1", "pw1", "Login efetuado com sucesso!" }, { "email2", "pw" ,"Dados de Login inválidos!"}, { "email", "pw2" ,"Dados de Login inválidos!"}};
    }

    @BeforeClass
    public  void before() {
        utilizadores = new Utilizadores();
        veiculos = new HashMap<String, Veiculo>();
        cliente = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN");
        motorista = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1", new Mota());
        try {
            utilizadores.adiciona(cliente);
            utilizadores.adiciona(motorista);
        } catch (EmailAlreadyInUseException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "loginCliente")
    public void testeLoginCliente(String email, String pw, String expected) {

        String data = email+ "\n" + pw + "\n";
        assertTrue(testeLogin(data).contains(expected));
}

    @Test(dataProvider = "loginMotorista")
    public void testeLoginMotorista(String email, String pw, String expected) {

        String data = email+ "\n" + pw + "\n";
        assertTrue(testeLogin(data).contains(expected));
    }

    private String testeLogin(String data) {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream response = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(response));
            login.executa(utilizadores, veiculos);

        } catch (NoSuchElementException e) {
        } finally {
            System.setIn(System.in);
            System.setOut(oldOut);
        }
        return response.toString();
    }
}
