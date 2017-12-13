package views.utilizadores;

import controllers.UtilizadorController;
import models.utilizadores.Cliente;
import views.View;
import views.menus.MenuCliente;
import views.menus.MenuMotorista;

import java.util.Scanner;

public class LoginUtilizadores implements View {
    @Override
    public void executa(Object a) {
        UtilizadorController l = new UtilizadorController();
        String email,password;
        Scanner scin = new Scanner(System.in);

        System.out.println("\n-----------Login-----------");
        System.out.print("Email: ");
        email = scin.nextLine();
        System.out.print("Password: ");
        password = scin.nextLine();

        switch (l.login(email, password)) {
            case -1:
                System.out.println("Dados de Login inválidos!");
                break;
            case 0:
                System.out.println("Login efetuado com sucesso!");
                new MenuCliente().executa();
                break;
            case 1:
                System.out.println("Login efetuado com sucesso!");
                new MenuMotorista().executa();
                break;
        }
    }
}
