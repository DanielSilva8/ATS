package views.utilizadores;

import controllers.UtilizadorController;
import exceptions.EmailAlreadyInUseException;
import models.DB;
import views.View;

import java.util.Scanner;

/**
 * Created by danys on 13-Dec-17.
 */
public class RegistoUtilizadores implements View {

    @Override
    public void executa(Object a) {

        int tipo = (Integer) a;
        Scanner scin = new Scanner(System.in);
        String email,nome,password,morada,data;

        System.out.println("-----------Dados de registo-----------");
        System.out.print("Email: ");
        email = scin.nextLine();

        System.out.print("Nome: ");
        nome = scin.nextLine();

        System.out.print("Password: ");
        password = scin.nextLine();

        System.out.print("Morada: ");
        morada = scin.nextLine();

        System.out.print("Data de nascimento(dd-mm-aaaa): ");
        data = scin.nextLine();

        UtilizadorController user = new UtilizadorController();

        while (!user.registarUtilizador(email, nome, password, morada, data, tipo)) {
            System.out.println("Matricula já em uso.");
            System.out.print("Insira uma nova matricula: ");
            email = scin.nextLine();
        }
    }
}
