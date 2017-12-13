package views.utilizadores;

import models.utilizadores.Motorista;
import utils.Utils;
import views.View;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by danys on 13-Dec-17.
 */
public class MotoristaMontanteFaturado implements View {
    @Override
    public void executa(Object a) {
        Motorista m = (Motorista) a;

        Utils utils = new Utils();
        Scanner sc = new Scanner(System.in);
        int op;

        System.out.println("\n-----------Montante faturado-----------");
        System.out.println("1 - Até ao momento");
        System.out.println("2 - Entre datas");
        System.out.print("Opção: ");

        op = sc.nextInt();

        if(op == 1){
            System.out.println("\n-----------Montante faturado-----------");
            System.out.println(String.format("%.2f",m.totalFaturado())+"€");
        }
        else if(op == 2){
            System.out.println("\nIntroduza a data de início.");
            GregorianCalendar inicio = utils.dataInput();

            System.out.println("\nIntroduza a data final.");
            GregorianCalendar fim = utils.dataInput();

            System.out.println("\n-----------Montante faturado-----------");
            System.out.println(String.format("%.2f",m.totalFaturado(inicio, fim))+"€");
        }
        else System.out.println("Opção inválida!!");

    }
}
