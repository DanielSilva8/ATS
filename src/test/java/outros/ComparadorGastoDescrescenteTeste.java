package outros;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Cliente;
import rapl.jRAPLWorker;
import utils.ComparadorGastoDescrescente;

import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by danys on 09-Dec-17.
 */
public class ComparadorGastoDescrescenteTeste {

    private Cliente v;
    private Cliente v1;
    private Cliente v2;
    private Cliente v3;

    @BeforeClass
    public void before(){

        v = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN", new TreeSet<>(), 10);
        v1 = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN", new TreeSet<>(), 10);
        v2 = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN", new TreeSet<>(), 12);
        v3 = new Cliente( "email",  "nome",  "pw",  "morada",  "dDN", new TreeSet<>(), 8);
    }

    @Test
    public void testeCompare() {

        ComparadorGastoDescrescente cd = new ComparadorGastoDescrescente();
        jRAPLWorker.start("ComparadorGastoDescrescente,testeCompare");
        assertEquals(cd.compare(v,v1),0);
        assertEquals(cd.compare(v,v3),-1);
        assertEquals(cd.compare(v,v2),1);
        jRAPLWorker.end();
    }
}
