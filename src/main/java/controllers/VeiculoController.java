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
import views.veiculos.ListarVeiculos;

import java.util.*;

/**
 * Created by danys on 12-Dec-17.
 */
public class VeiculoController {

    public boolean registarVeiculo(String matricula, int velocidademedia, double precobase, int tipo){

        boolean x = true;
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
        return x;
    }

    public static void listarVeiculos() {
        Map<String, Veiculo> map = DB.getVeiculos();
        View v = new ListarVeiculos();
        v.executa(map);
    }
}
