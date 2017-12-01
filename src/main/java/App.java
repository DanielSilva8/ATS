/**
 * Exemplo de aplicação com menu em modo texto.

 * 
 * @author José Creissac Campos
 * @version 1.0
 */

import exceptions.EmailAlreadyInUseException;
import exceptions.NenhumaViagemException;
import menu.Login;
import menu.Menu;
import menu.Registo;
import utilizadores.Cliente;
import utilizadores.Motorista;
import utilizadores.Utilizadores;
import veiculos.Veiculo;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App implements Serializable{
    private Utilizadores utilizadores;
    private HashMap<String,Veiculo> veiculos;

    // Menus da aplicação
    private Menu menuPrincipal;
    private Login menuLogin;
    private Registo menuRegisto;
    
    /**
     * O método main cria a aplicação e invoca o método run()
     */
    public static void main(String[] args) {new App().run();}

    /**
     * Construtor.
     * 
     * Cria os menus e a camada de negócio.
     */    
    private App() {

//        try {
//            this.utilizadores = new Utilizadores();
//            this.utilizadores.adiciona("email","nome","password","morada","17-01-1995",null);
//            this.utilizadores.adiciona("email1","nome1","password","morada","17-01-1995",null);
//            this.utilizadores.adiciona("email2","nome2","password","morada","17-01-1995",null);
//            this.utilizadores.adiciona("email3","nome3","password","morada","17-01-1995",null);
//            this.utilizadores.adiciona("user","user","password","morada","17-01-1995");
//            this.utilizadores.adiciona("user1","user1","password","morada","17-01-1995");
//            this.utilizadores.adiciona("user2","user2","password","morada","17-01-1995");
//            this.utilizadores.adiciona("user3","user3","password","morada","17-01-1995");
//
//        } catch (EmailAlreadyInUseException e) {
//            e.printStackTrace();
//        }

        String[] opcoes = {"Login",
                         "Registar",
                         "Extra"
                        };
        this.menuPrincipal = new Menu(opcoes);
        this.menuLogin = new Login();
        this.menuRegisto = new Registo();
        // Criar a lógica de negócio
        this.utilizadores = new Utilizadores();
        this.veiculos = new HashMap<String,Veiculo>();
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    private void run() {
        System.out.println("A carregar os dados...");
        try{
           carregaEstado("C:\\Users\\danys\\Downloads\\UMER_REFACTOR\\src\\main\\resources\\Appdadosbin");
        }
        catch (IOException e){
            System.out.println("Erro no ficheiro.");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Erro nas classes.");
        }
        do {
            this.menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()) {
                case 1: this.menuLogin.executa(this.utilizadores,this.veiculos);
                        break;
                case 2: this.menuRegisto.executa(this.utilizadores,this.veiculos);
                        break;
                case 3: this.funcionalidades();
                        break;
            }
        } while (menuPrincipal.getOpcao()!=0);
        
        System.out.println("A guardar os dados...");
        try{
           guardaEstado("C:\\Users\\danys\\Downloads\\UMER_REFACTOR\\src\\main\\resources\\Appdadosbin");
           escreveEmFicheiroTxt("C:\\Users\\danys\\Downloads\\UMER_REFACTOR\\src\\main\\resources\\Appdadosbin.txt");
        } 
        catch (IOException e){
            System.out.println("Erro no ficheiro.");
        } 

        System.out.println("Até breve!...");     
    }
    
    private void trataListarUtilizadores() {
        System.out.println(this.utilizadores);
    }
    
    private void listarVeiculos() {
        System.out.println("\n Veiculos");
        if(this.veiculos.isEmpty()) System.out.println("Não existem veiculos no sistema UMeR.");
        for(Veiculo v:this.veiculos.values())
            System.out.println(v.toString());         
    }
    
    public void top10ClientesGastadores(){
        List<Cliente> cs = this.utilizadores.top10ClientesGastadores();
        
        if(cs.size() == 0) System.out.println("Não existem utilizadores a utilizar o sistema UMeR.");
        else cs.forEach(c -> System.out.println("Nome: "+c.getNome()+" e-mail: "+c.getMail()+" Montante: "+String.format("%.2f",c.getMS())+"€"));
    }
    
    public void top5MotoristasComMaiorDesvio(){
        List<Motorista> ms = this.utilizadores.top5MotoristasComMaiorDesvio();
        
        if(ms.size() == 0) System.out.println("Não existem motoristas no sistema UMeR.");
        else for(Motorista m : ms)
                try{
                    System.out.println("Nome: "+m.getNome()+" e-mail: "+m.getMail()+" Desvio: "+String.format("%.2f",m.maiorDesvio().getDesvio()));
                }
                catch(NenhumaViagemException e){System.out.println("Objeto corrompido?");}
    }
    
    public void funcionalidades(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n-----------Extra-----------");
        System.out.println("1 - Listar Utilizadores");
        System.out.println("2 - Listar os 10 clientes que mais gastam");
        System.out.println("3 - Listar viaturas");
        System.out.println("4 - Listar os 5 motoristas com maior desvio entre os valores previstos para as viagens e o valor real");
        System.out.print("Opção: ");
        int op = sc.nextInt();
        
        System.out.println("\n-----------Extra-----------");
        switch(op){
            case 1: trataListarUtilizadores();
                        break;
            case 2: this.top10ClientesGastadores();
                        break;
            case 3: listarVeiculos();
                        break;
            case 4: this.top5MotoristasComMaiorDesvio();
                        break;
            default: System.out.println("Opção Inválida!");
                     break;
        }
    }
    
    public void guardaEstado(String nome) throws IOException {


        FileOutputStream f = new FileOutputStream(nome);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this.utilizadores);
        o.writeObject(this.veiculos);
        o.flush();
        o.close();
    }
    
    public void carregaEstado(String nome) throws FileNotFoundException,
                                            IOException,
                                            ClassNotFoundException {
        FileInputStream f = new FileInputStream(nome);
        ObjectInputStream o = new ObjectInputStream(f);
       // System.out.println(o.readObject());
        this.utilizadores = (Utilizadores) o.readObject();
        this.veiculos = (HashMap<String,Veiculo>) o.readObject();
        o.close();
    }
    
    public void escreveEmFicheiroTxt(String nomeFicheiro) throws IOException{
       PrintWriter fich = new PrintWriter(nomeFicheiro);
       fich.println(this.utilizadores.toString());
       fich.flush();
       fich.close();
     }
}

