package views.utilizadores;

import exceptions.InvalidIntervalException;
import models.utilizadores.Ator;
import models.viagem.Viagem;
import utils.Utils;
import views.View;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by danys on 12-Dec-17.
 */
public class HistoricoUtilizador implements View{
    @Override
    public void executa(Object object) {

        Ator a = (Ator) object;
        Utils utils = new Utils();
        int op;
        boolean cond = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("\n-----------Historico de viagens-----------");
        System.out.println("1 - Todas as viagens");
        System.out.println("2 - Viagens entre datas");
        System.out.print("Opção: ");
        op = sc.nextInt();

        if(op == 1){
            System.out.println("\n-----------Historico de viagens-----------");
            Set<Viagem> historico = a.getHistorico();

            if(historico.size() > 0){
                for(Viagem v:historico){
                    System.out.println("\n--Viagem--");
                    System.out.println(v.toString());
                }
            }
            else System.out.println("O utilizador não realizou nenhuma viagem até ao momento.");
        }
        else if(op == 2){
            while(cond){
                System.out.println("\nIntroduza a data de início.");
                GregorianCalendar inicio = utils.dataInput();

                System.out.println("\nIntroduza a data final.");
                GregorianCalendar fim = utils.dataInput();

                try{
                    List<Viagem> hist = a.viagensEntreDatas(inicio, fim);

                    System.out.println("\n-----------Historico de viagens-----------");
                    if(hist.size() > 0) hist.forEach(v -> System.out.println(v.toString()));
                    else System.out.println("Não existem registos de viagem no período considerado.");
                    cond = false;
                }
                catch(InvalidIntervalException e){System.out.println("Certefique-se de que a data de inicio é mais antiga que a de fim."); cond = true;}
            }
        }
        else System.out.println("Opção inválida!");
    }
}
