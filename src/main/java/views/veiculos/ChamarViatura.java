package views.veiculos;

import controllers.VeiculoController;
import controllers.ViagemController;
import exceptions.EmailDoesNotExistException;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import utils.Coordenada;
import utils.Utils;
import views.View;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by danys on 12-Dec-17.
 */
public class ChamarViatura implements View {
    @Override
    public void executa(Object object) {
        ViagemController viagemController = new ViagemController();
        Scanner scin = new Scanner(System.in);

        int nLugares;
        System.out.println("Indique o número de lugares pretendidos.");
        nLugares = scin.nextInt();
        if(nLugares < 0 || nLugares > 10) {
            System.out.println("A UMeR não suporta veículos com essa capacidade.");
            return;
        }

        System.out.println("\n-----------Chamar viatura mais próxima-----------");
        System.out.println("Insira as coordenadas em que se encontra: ");
        Coordenada inicio = Utils.coordenadaInput();

        System.out.println("Insira as coordenadas de destino: ");
        Coordenada fim = Utils.coordenadaInput();

        System.out.println( viagemController.realizarViagem(inicio,fim, nLugares));
    }
}
