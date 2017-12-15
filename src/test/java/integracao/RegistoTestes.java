package integracao;

import controllers.UtilizadorController;
import controllers.VeiculoController;
import exceptions.EmailDoesNotExistException;
import models.DB;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import models.veiculos.Carrinha;
import models.veiculos.Carro;
import models.veiculos.Mota;
import models.veiculos.Veiculo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

/**
 * Created by danys on 01-Dec-17.
 */
public class RegistoTestes {

    UtilizadorController registoU = new UtilizadorController();
    VeiculoController registoV = new VeiculoController();

    @DataProvider(name = "registoUtilizador")
    public static Object[][] credentialsCS() {
        return new Object[][] { { "email1", "nome", "pass", "morada", "17-01-1995", 1},  { "email2", "nome", "pass", "morada", "17-01-1995", 2}};
    }

    @DataProvider(name = "registoVeiculo")
    public static Object[][] credentialsVS() {
        return new Object[][] { { 120, 1000, "matricula", 1},   { 120, 1000, "matricula2", 2},  { 120, 1000, "matricdcla3", 3}};
    }

    @BeforeClass
    public  void before() {
        DB.resetParaTestes(null, null);
    }

    @Test(dataProvider = "registoUtilizador", priority = 1)
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

        }
    }

    @Test(dataProvider = "registoUtilizador", priority = 2)
    public void testeRegistarUtilizadorFalhado(String email, String nome,String pass, String morada, String nascimento, int tipo) {

        assertFalse(registoU.registarUtilizador(email, nome, pass, morada,nascimento,tipo));
    }


    @Test(dataProvider = "registoVeiculo", priority = 3)
    public void testeRegistarVeiculoSucesso(int vm, double preco, String matricula, int tipo ) {

        registoV.registarVeiculo(matricula, vm, preco, tipo);

        HashMap<String, Veiculo> v = DB.getVeiculos();
        assertEquals(v.get(matricula).getVelocidadeMedia(),vm);
        assertEquals(v.get(matricula).getPrecoBase(),preco);

        switch (tipo) {
            case 1:
                assertTrue(v.get(matricula) instanceof Carro);
                break;
            case 2:
                assertTrue(v.get(matricula) instanceof Carrinha);
                break;
            case 3:
                assertTrue(v.get(matricula) instanceof Mota);
                break;
        }

    }

    @Test(dataProvider = "registoVeiculo", priority = 4)
    public void testeRegistarVeiculoFalhado(int vm, double preco, String matricula, int tipo) {
        assertFalse(registoV.registarVeiculo(matricula, vm, preco, tipo));
    }


}
