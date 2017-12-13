package views.veiculos;

import models.DB;
import models.utilizadores.Motorista;
import models.veiculos.Veiculo;
import views.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by danys on 13-Dec-17.
 */
public class AssociarViatura implements View{


    @Override
    public void executa(Object a) {

        List<Object> list = (ArrayList) a;
        HashMap<String,Veiculo> veiculos = (HashMap<String, Veiculo>) list.get(0);
        Motorista m = (Motorista) list.get(1);

        int i=0;
        System.out.println("\n-----------Lista de viaturas disponiveis-----------");
        for(Veiculo v:veiculos.values())
            if(!v.getOcupado()){
                System.out.println(v.getMatricula().toString());
                i++;
            }

        if(i==0)
            System.out.println("\nNão existem viaturas disponiveis");
        else{
            System.out.println("\nIndique a matricula da viatura que quer associar:");
            Scanner scin = new Scanner(System.in);
            String matricula = scin.nextLine();

            if(veiculos.containsKey(matricula) && veiculos.get(matricula).getOcupado()==false){
                m.setVeiculo(veiculos.get(matricula));
                veiculos.get(matricula).setOcupado(true);
                System.out.println("\nViatura adicionada com sucesso");
            }
            else
                System.out.println("\nMatricula inexistente");
        }
    }
}
