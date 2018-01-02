package outros;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import rapl.jRAPLWorker;
import utils.ComparadorDesvio;
import utils.Coordenada;
import models.viagem.Viagem;

import java.util.GregorianCalendar;

/**
 * Created by danys on 09-Dec-17.
 */

public class ComparadorDesvioTeste {
    private Viagem v;
    private Viagem v1;
    private Viagem v2;
    private Viagem v3;

    @BeforeClass
    public void before(){
        v = new Viagem(new Coordenada(3,3), new Coordenada(4,4),3,"mail", new GregorianCalendar(2017, 12, 1), 10, 10.0);
        v1 = new Viagem(new Coordenada(3,3), new Coordenada(4,4),3,"mail", new GregorianCalendar(2017, 12, 1), 10, 10.0);
        v2 = new Viagem(new Coordenada(3,3), new Coordenada(4,4),3,"mail", new GregorianCalendar(2017, 12, 1), 10, 12.0);
        v3 = new Viagem(new Coordenada(3,3), new Coordenada(4,4),3,"mail", new GregorianCalendar(2017, 12, 1), 10, 8.0);
    }

    @Test
    public void testeCompare() {

        ComparadorDesvio cd = new ComparadorDesvio();

        jRAPLWorker.start("ComparadorDesvio,testeCompare");
        assertEquals(cd.compare(v,v1),0);
        assertEquals(cd.compare(v,v2),-1);
        assertEquals(cd.compare(v,v3),1);
        jRAPLWorker.end();
    }
}
