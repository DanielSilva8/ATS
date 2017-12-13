package models;

import conf.Configuracao;
import exceptions.EmailAlreadyInUseException;
import models.utilizadores.Ator;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import models.veiculos.Mota;
import models.veiculos.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danys on 12-Dec-17.
 */
public class DB {
    private static Utilizadores utilizadores = new Utilizadores();
    private static HashMap<String,Veiculo> veiculos = new HashMap<>();

    public static boolean inicializa() {
        System.out.println("A carregar os dados...");
        try{
            carregaEstado();
            return true;
        }
        catch (IOException e){
            System.out.println("Erro no ficheiro.");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Erro nas classes.");
        }
        return false;
    }
    public static void termina() {
        System.out.println("A guardar os dados...");
        try{
            guardaEstado();
            escreveEmFicheiroTxt();
        }
        catch (IOException e){
            System.out.println("Erro no ficheiro.");
        }
        System.out.println("Até breve!...");
    }

    public static HashMap<String, Veiculo> getVeiculos() {
        return veiculos;
    }
    public static void setVeiculos(HashMap<String, Veiculo> veiculos) {
        DB.veiculos = veiculos;
    }
    public static void setUtilizadores(Utilizadores utilizadores) {
        DB.utilizadores = utilizadores;
    }
    public static Utilizadores getUtilizadores() {
        return utilizadores;
    }
    public static List<Motorista> getMotoristas() {
        List<Motorista> m = new ArrayList<Motorista>();
        for (Map.Entry<String,Ator> entry: utilizadores.getUtilizadores().entrySet()) {
            if (entry.getValue() instanceof Motorista)
                m.add((Motorista)entry.getValue());
        }
        return m;
    }
    public static void adicionaCliente(String email, String nome, String password, String morada, String data) throws EmailAlreadyInUseException {
            utilizadores.adiciona(email, nome, password, morada, data);
    }
    public static void adicionaMotorista(String email, String nome, String password, String morada, String data) throws EmailAlreadyInUseException {
            utilizadores.adiciona(email, nome, password, morada, data, null);
    }
    public static boolean adicionaVeiculo(Veiculo veiculo){
        if(veiculos.containsKey(veiculo.getMatricula()))
            return false;
        veiculos.put(veiculo.getMatricula(), veiculo);
        return true;
    }
    public static void guardaEstado() throws IOException {

        FileOutputStream f = new FileOutputStream(Configuracao.getCaminhoBin());
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(utilizadores);
        o.writeObject(veiculos);
        o.flush();
        o.close();
    }
    public static void carregaEstado() throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(Configuracao.getCaminhoBin());
        ObjectInputStream o = new ObjectInputStream(f);
        utilizadores = (Utilizadores) o.readObject();
        veiculos = (HashMap<String,Veiculo>) o.readObject();
        o.close();
    }
    public static void escreveEmFicheiroTxt() throws IOException{
        PrintWriter fich = new PrintWriter(Configuracao.getCaminhoTxt());
        fich.println(utilizadores.toString());
        fich.flush();
        fich.close();
    }
    public static void resetParaTestes(Utilizadores _utilizadores, HashMap<String,Veiculo> _veiculos) {

        Configuracao.setCaminhoBin("/src/test/resources/AppdadosbinTest");
        Configuracao.setCaminhoTxt("/src/test/resources/AppdadosbinTest.txt");

        setUtilizadores(new Utilizadores());
        setVeiculos(null);
        termina();

        if (_utilizadores != null) setUtilizadores(_utilizadores);
        if (_veiculos != null) setVeiculos(_veiculos);
        termina();

        try {
            carregaEstado();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
