package views;

import conf.Configuracao;
import controllers.UtilizadorController;
import controllers.VeiculoController;
import models.utilizadores.Utilizadores;
import exceptions.EmailAlreadyInUseException;
import exceptions.EmailDoesNotExistException;
import models.DB;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import models.veiculos.Carrinha;
import models.veiculos.Carro;
import models.veiculos.Mota;
import models.veiculos.Veiculo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by danys on 01-Dec-17.
 */
public class RegistoTestes {

    private HashMap<String,Veiculo> veiculos;
    private Utilizadores utilizadores;
    UtilizadorController registoU = new UtilizadorController();
    VeiculoController registoV = new VeiculoController();

    @DataProvider(name = "registoUtilizador")
    public static Object[][] credentialsCS() {
        return new Object[][] { { "email1", "nome", "pass", "morada", "17-01-1995", 1},  { "email2", "nome", "pass", "morada", "17-01-1995", 2}};
    }

    @DataProvider(name = "registoVeiculoSucesso")
    public static Object[][] credentialsVS() {
        return new Object[][] { { "1", "120", "1000", "matricula", ""},   { "1", "120", "1000", "matricula", "newMatricula"},  { "9\n1", "120", "1000", "matricdcla2", ""},  { "4\n1", "120", "1", "matricula3", ""}};
    }

    @BeforeClass
    public  void before() {
        DB.resetParaTestes(null, null);
    }

    @Test(dataProvider = "registoUtilizador")
    public void testeRegistarUtilizadorSucesso(String email, String nome,String pass, String morada, String nascimento, int tipo) {

        registoU.registarUtilizador(email, nome, pass, morada,nascimento,tipo);

        Utilizadores u = DB.getUtilizadores();
        try {
            assertEquals(u.getAtor(email).getNome(), nome);
            assertEquals(u.getAtor(email).getDataDeNascimento(), nascimento);
            assertEquals(u.getAtor(email).getPassword(), pass);
            assertEquals(u.getAtor(email).getMorada(), morada);

            switch (tipo) {
                case 1:
                    assertTrue(u.getAtor(email) instanceof Cliente);
                    break;
                case 2:
                    assertTrue(u.getAtor(email) instanceof Motorista);
                    break;

            }
        } catch (EmailDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "registoVeiculo")
    public void testeRegistarCarro(String menu, String vm,String preco, String matricula, String novamatricula ) {
        String data = menu + "\r\n" + vm + "\r\n" + preco+ "\r\n" + matricula + "\r\n" + novamatricula;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
        //    registo.registarVeiculo(1);

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
        //    registo.registarVeiculo(1);

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
         //   registo.registarVeiculo(3);

            assertEquals(veiculos.get("matricula33").getVelocidadeMedia(),120);
            assertEquals(veiculos.get("matricula33").getPrecoBase(),Double.parseDouble("1000"));
            assertTrue(veiculos.get("matricula33") instanceof Mota);

        } finally {
            System.setIn(System.in);
        }
    }


}
