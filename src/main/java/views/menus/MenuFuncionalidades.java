package views.menus;


import controllers.VeiculoController;
import models.DB;
import exceptions.NenhumaViagemException;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;

import java.util.List;

/**
 * Created by danys on 12-Dec-17.
 */
public class MenuFuncionalidades extends Menu {

    static final String[] opcoes = {"Listar Utilizadores", "Listar os 10 clientes que mais gastam", "Listar viaturas", "Listar os 5 motoristas com maior desvio entre os valores previstos para as viagens e o valor real"};
    static final String cabecalho = "\n---------Extra---------";

    public MenuFuncionalidades() {
        super(opcoes, cabecalho);
    }

    @Override
    public void redireciona() {

        switch (getOpcao()) {
            case 1:
                System.out.println(DB.getUtilizadores().toString());
                break;
            case 2:
                List<Cliente> cs = DB.getUtilizadores().top10ClientesGastadores();
                if(cs.size() == 0) System.out.println("Não existem utilizadores a utilizar o sistema UMeR.");
                else cs.forEach(c -> System.out.println("Nome: "+c.getNome()+" e-mail: "+c.getMail()+" Montante: "+String.format("%.2f",c.getMS())+"€"));
                break;
            case 3:
                VeiculoController v = new VeiculoController();
                v.listarVeiculos();
                break;
            case 4:
                List<Motorista> ms = DB.getUtilizadores().top5MotoristasComMaiorDesvio();
                if(ms.size() == 0) System.out.println("Não existem motoristas no sistema UMeR.");
                else for(Motorista m : ms)
                    try{
                        System.out.println("Nome: "+m.getNome()+" e-mail: "+m.getMail()+" Desvio: "+String.format("%.2f",m.maiorDesvio().getDesvio()));
                    }
                    catch(NenhumaViagemException e){System.out.println("Objeto corrompido?");}
                break;
        }
    }
}
