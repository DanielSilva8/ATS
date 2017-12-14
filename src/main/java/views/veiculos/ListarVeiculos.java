package views.veiculos;

import models.DB;
import models.veiculos.Veiculo;
import views.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danys on 14-Dec-17.
 */
public class ListarVeiculos implements View {
    @Override
    public void executa(Object a) {
        Map<String, Veiculo> map = (HashMap<String, Veiculo>) a;
        System.out.println("\n Veiculos");
        if(map.isEmpty()) System.out.println("Não existem veiculos no sistema UMeR.");
        for(Veiculo v:map.values())
            System.out.println(v.toString());
    }
}
