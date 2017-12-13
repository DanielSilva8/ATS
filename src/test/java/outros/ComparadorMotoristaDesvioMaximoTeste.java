package outros;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import models.utilizadores.Motorista;
import utils.ComparadorMotoristaDesvioMaximo;
import utils.Coordenada;
import models.veiculos.Mota;
import models.viagem.Viagem;

import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by danys on 09-Dec-17.
 */
public class ComparadorMotoristaDesvioMaximoTeste {

    private Motorista m;
    private Motorista m1;
    private Motorista m2;
    private Motorista m3;
    private Motorista m4;
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

        Set t1 = new TreeSet<Viagem>(); t1.add(v);
        Set t2 = new TreeSet<Viagem>(); t2.add(v1);
        Set t3 = new TreeSet<Viagem>(); t3.add(v2);
        Set t4 = new TreeSet<Viagem>(); t4.add(v3);

        m = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1", t1, 0,0,0,0, true, new Mota());
        m1 = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1",t2, 0,0,0,0, true, new Mota());
        m2 = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1", t3, 0,0,0,0, true, new Mota());
        m3 = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1",t4, 0,0,0,0, true, new Mota());
        m4 = new Motorista("email1",  "nome1",  "pw1",  "morada1",  "dDN1",new TreeSet<>(), 0,0,0,0, true, new Mota());
    }

    @Test
    public void testeCompare() {

        ComparadorMotoristaDesvioMaximo cd = new ComparadorMotoristaDesvioMaximo();

        assertEquals(cd.compare(m,m1),0);
        assertEquals(cd.compare(m,m1),0);
        assertEquals(cd.compare(m,m3),-1);
        assertEquals(cd.compare(m,m2),1);
        assertEquals(cd.compare(m,m4),-1);
        assertEquals(cd.compare(m4,m),1);
        assertEquals(cd.compare(m4, m4.clone()),0);
    }


}
