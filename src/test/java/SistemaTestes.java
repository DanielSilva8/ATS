import exceptions.EmailAlreadyInUseException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilizadores.Utilizadores;
import utils.Coordenada;
import veiculos.Carrinha;
import veiculos.Carro;
import veiculos.Mota;
import veiculos.Veiculo;

import java.io.IOException;
import java.util.HashMap;

import static org.testng.AssertJUnit.fail;
import static org.testng.Assert.*;

/**
 * Created by danys on 11-Dec-17.
 */

public class SistemaTestes {

    App app = new App();
    Utilizadores u = new Utilizadores();
    HashMap<String,Veiculo> v = new HashMap<String,Veiculo>();

    @BeforeClass
    public void injectDoubles() {
        Veiculo v2 = new Mota(10,10,10,"matricula1",new Coordenada(3,5),false);
        Veiculo v3 = new Mota(10,10,10,"matricula2",new Coordenada(3,5),false);
        Veiculo v4 = new Mota(10,10,10,"matricula",new Coordenada(3,5),false);

        try {
            u.adiciona("email","nome","password","morada","17-01-1995",v2);
            u.adiciona("email1","nome1","password","morada","17-01-1995",v3);
            u.adiciona("email2","nome2","password","morada","17-01-1995",v4);
            u.adiciona("user","user","password","morada","17-01-1995");
            u.adiciona("user1","user1","password","morada","17-01-1995");
            u.adiciona("user2","user2","password","morada","17-01-1995");
            u.adiciona("user3","user3","password","morada","17-01-1995");

            v.put("n/a", v2);
            v.put("n/a", v3);
            v.put("n/a", v4);

            app.setUtilizadores(u.clone());
            app.setVeiculos((HashMap<String, Veiculo>) v.clone());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testeDados() {

        try {
            app.guardaEstado(System.getProperty("user.dir") + "/src/test/resources/AppdadosbinTest");
            app.escreveEmFicheiroTxt(System.getProperty("user.dir") + "/src/test/resources/AppdadosbinTest.txt");
            app.carregaEstado(System.getProperty("user.dir") + "/src/test/resources/AppdadosbinTest");
        } catch (IOException e) {
            fail("Erro ao guardar");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            fail("Erro ao carregar");
            e.printStackTrace();
        }

        u.toString();
        assertTrue(app.getUtilizadores().equals(u));
        assertTrue(app.getVeiculos().equals(v));
    }
}
