

import exceptions.EmailAlreadyInUseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilizadores.Cliente;
import utilizadores.Motorista;
import utilizadores.Utilizadores;
import utils.Coordenada;
import veiculos.Carro;
import veiculos.Veiculo;
import viagem.Viagem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;


public class AppTestes {


    private Motorista motorista = new Motorista();
    private Cliente cliente = new Cliente();

    private TreeSet<Viagem> viagens = new TreeSet();
    private Coordenada cinicial1 = new Coordenada(1, 1);
    private Coordenada cfinal1 = new Coordenada(2, 2);
    private Viagem viagem1 = new Viagem(cinicial1, cfinal1, 200, "mail1", new GregorianCalendar(1995, 11, 10), 100, 3);
    private Veiculo veiculo = new Carro(120, 5, 80, "99-AA-99", cinicial1, true);
    private  Utilizadores a = new Utilizadores();
    private App app = new App();

    @BeforeClass
    public void before(){

        viagens.add(viagem1);
        this.cliente = new Cliente("mail2", "Goncalves", "qwerty", "Braga", "29-09-1995", viagens, 11);
//        this.motorista = new Motorista("email", "Leonel", "password", "Portugal", "29-09-1995", viagens, 80, 80, 2, 1200, true, veiculo);

        try{
//            a.adiciona(this.motorista);
            a.adiciona(this.cliente);
        }catch (EmailAlreadyInUseException e){
            e.getStackTrace();
        }



    }

    @Test
    public void guardaEstadotest(){

        try{
            app.guardaEstado("teste.txt");
        }catch(IOException e){
            e.getStackTrace();
        }
    }

    @Test
    public void escreverEmFicheiroEstadotest(){

        try{
            app.escreveEmFicheiroTxt("teste.txt");
        }catch(IOException e){
            e.getStackTrace();
        }
    }
    @Test
    public void carregaEstadotest(){

        try{
            app.carregaEstado("teste.txt");
        }catch(IOException e){
            e.getStackTrace();
        }catch (ClassNotFoundException e){
            e.getStackTrace();
        }
    }

    @AfterClass
    public void after(){
    String qq = "";

    app.top10ClientesGastadores();
    app.top5MotoristasComMaiorDesvio();
    app.listarVeiculos();
    app.trataListarUtilizadores();
    app.funcionalidades();
    app.run();


    }
}
