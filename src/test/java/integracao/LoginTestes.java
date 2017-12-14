package integracao;

import conf.Configuracao;
import controllers.Sessao;
import controllers.UtilizadorController;
import exceptions.EmailAlreadyInUseException;
import models.DB;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import models.veiculos.Mota;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import static org.testng.Assert.*;

/**
 * Created by danys on 01-Dec-17.
 */
public class LoginTestes {

    private UtilizadorController user = new UtilizadorController();
    private Utilizadores utilizadores;
    private Cliente cliente;
    private Motorista motorista;

    @DataProvider(name = "loginSucesso")
    public static Object[][] credentialsC() {
        return new Object[][] { {"email", "pw"}, { "email1", "pw1"}};
    }

    @DataProvider(name = "loginFalhado")
    public static Object[][] credentialsM() {
        return new Object[][] { { "email2", "pw1" }, { "email", "pw5"}, {"email5", "pw3"}};
    }

    @BeforeClass
    public  void before() {

        utilizadores = new Utilizadores();
        cliente = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN");
        motorista = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1", new Mota());
        try {
            utilizadores.adiciona(cliente);
            utilizadores.adiciona(motorista);
        } catch (EmailAlreadyInUseException e) {
            e.printStackTrace();
        }
        DB.resetParaTestes(utilizadores, null);
    }

    @BeforeMethod
    public void beforeMethod() {
        Sessao.criar(null);
    }

    @Test(dataProvider = "loginSucesso")
    public void testeLoginSucesso(String email, String pw) {

        assertNotEquals(user.login(email,pw), -1);
        assertTrue(Sessao.getUtilizador().getMail().equals(email));
    }

    @Test(dataProvider = "loginFalhado")
    public void testeLoginFalhado(String email, String pw) {

        assertEquals(user.login(email,pw), -1);
        assertNull(Sessao.getUtilizador());
    }

}
