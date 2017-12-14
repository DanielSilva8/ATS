package views.veiculos;

import controllers.VeiculoController;
import views.View;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by danys on 13-Dec-17.
 */
public class RegistoVeiculos implements View {
    @Override
    public void executa(Object a) {
        int vm;
        String matricula;
        double pb;
        int i=0;

        Scanner scin = new Scanner(System.in);

        do{
            System.out.print("Velocidade média: ");

            try {
                vm = scin.nextInt();
            }
            catch (InputMismatchException e) {
                vm = 0;
            }
            if (vm<0) {
                System.out.println("Número Inválido!!!");
            }
        }while(vm<0);

        do{
            System.out.print("Preço base: ");

            try {
                pb = scin.nextInt();
            }
            catch (InputMismatchException e) {
                pb = 0;
            }
            if (pb<0) {
                System.out.println("Preço Inválido!!!");
            }
        }while(pb<0);
        scin.nextLine();

        System.out.print("Matricula: ");
        matricula = scin.nextLine();

        VeiculoController v = new VeiculoController();

        while (!v.registarVeiculo(matricula, vm, pb, (Integer) a)) {
            System.out.println("Matricula já em uso.");
            System.out.print("Insira uma nova matricula: ");
            matricula = scin.nextLine();
        }

    }
}
