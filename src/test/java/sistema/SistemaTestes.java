package sistema;

import models.DB;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Utilizadores;
import rapl.jRAPLWorker;
import utils.Coordenada;
import models.veiculos.Mota;
import models.veiculos.Veiculo;

import java.io.IOException;
import java.util.HashMap;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.fail;

/**
 * Created by danys on 11-Dec-17.
 */

public class SistemaTestes {

    Utilizadores u = new Utilizadores();
    HashMap<String,Veiculo> v = new HashMap<String,Veiculo>();

    @BeforeClass
    public void before() {
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testeDados() {

        DB.resetParaTestes(u, v);
        jRAPLWorker.start("Sistema,testeDados");
        assertTrue(DB.getUtilizadores().equals(u));
        assertTrue(DB.getVeiculos().equals(v));
        jRAPLWorker.end();
        jRAPLWorker.generateTargetreports();
    }
}
