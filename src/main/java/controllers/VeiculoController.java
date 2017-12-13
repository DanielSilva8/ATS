package controllers;

import exceptions.EmailDoesNotExistException;
import models.DB;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import models.veiculos.Carrinha;
import models.veiculos.Carro;
import models.veiculos.Mota;
import models.veiculos.Veiculo;
import utils.Coordenada;
import views.View;
import views.veiculos.AssociarViatura;

import java.util.*;

/**
 * Created by danys on 12-Dec-17.
 */
public class VeiculoController {

    public void registarVeiculo(String matricula, int velocidademedia, double precobase, int tipo){

        Scanner scin = new Scanner(System.in);
        boolean x = true;
        do{
            if(!x){
                System.out.println("Matricula já em uso.");
                System.out.print("Insira uma nova matricula: ");
                matricula = scin.nextLine();
            } else {
                switch (tipo) {
                    case 1:
                        x = DB.adicionaVeiculo(new Carro(velocidademedia,precobase,100,matricula,new Coordenada(),false));
                        break;
                    case 2:
                        x = DB.adicionaVeiculo(new Carrinha(velocidademedia,precobase,100,matricula,new Coordenada(),false));
                        break;
                    case 3:
                        x = DB.adicionaVeiculo(new Mota(velocidademedia,precobase,100,matricula,new Coordenada(),false));
                        break;
                }
            }
        }while(x);
    }

    public static void listarVeiculos() {
        Map<String, Veiculo> map = DB.getVeiculos();
        System.out.println("\n Veiculos");
        if(map.isEmpty()) System.out.println("Não existem veiculos no sistema UMeR.");
        for(Veiculo v:map.values())
            System.out.println(v.toString());
    }
}
