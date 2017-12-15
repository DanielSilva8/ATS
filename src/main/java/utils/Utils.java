package utils;

import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by danys on 12-Dec-17.
 */
public class Utils {

    public static GregorianCalendar dataInput(){
        int anoI, mesI, diaI;
        anoI = mesI = diaI = 0;
        boolean cond = true;
        GregorianCalendar aux = new GregorianCalendar();
        Scanner sc = new Scanner(System.in);

        System.out.print("Ano:");
        while(cond){
            System.out.println( sc.nextInt());
            anoI = sc.nextInt();
            if(anoI < 0) System.out.println("Introduza um ano válido!");
            else cond = false;
        }

        System.out.print("Mês:");
        cond = true;
        while(cond){
            mesI = sc.nextInt();
            if(mesI < 0 || mesI > 12) System.out.println("Introduza um mês válido!");
            else cond = false;
        }

        System.out.print("Dia:");
        cond = true;
        while(cond){
            diaI = sc.nextInt();
            if(diaI > 0 && diaI < 29
                    || diaI > 28 && diaI < 32 && (mesI == 1 || mesI == 3 || mesI == 5 || mesI == 7 || mesI == 8 || mesI == 10 || mesI == 12)
                    || diaI > 28 && diaI < 31 && (mesI == 4 || mesI == 6 || mesI == 9 || mesI == 11)
                    || diaI == 29 && aux.isLeapYear(anoI)) cond = false;
            else System.out.println("Introduza um dia válido!");
        }

        return new GregorianCalendar(anoI,mesI,diaI);
    }

    public static Coordenada coordenadaInput(){
        Scanner scin = new Scanner(System.in);
        double x = 0;
        double y = 0;
        boolean sucesso;
        do{
            sucesso = true;
            System.out.print("X: ");
            try {
                x = scin.nextDouble();
            }
            catch (InputMismatchException e) {
                sucesso = false;
            }
            System.out.print("Y: ");
            try {
                y = scin.nextDouble();
            }
            catch (InputMismatchException e) {
                sucesso = false;
            }
            if(!sucesso) System.out.println("Erro nas coordenadas!!!");
        }while(!sucesso);

        return new Coordenada(x,y);
    }
}
