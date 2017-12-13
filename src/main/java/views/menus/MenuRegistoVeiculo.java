package views.menus;

import views.View;
import views.veiculos.RegistoVeiculos;

/**
 * Created by danys on 12-Dec-17.
 */
public class MenuRegistoVeiculo extends Menu {

    static final String[] opcoes = {"Carro", "Carrinha", "Mota"};
    static final String cabecalho = "-----------Dados sobre o veículo-----------";

    public MenuRegistoVeiculo() {
        super(opcoes, cabecalho);
    }

    @Override
    public void redireciona() {
        View v = new RegistoVeiculos();
        switch (getOpcao()) {
            case 1:
                v.executa(new Integer(1));
                break;
            case 2:
                v.executa(new Integer(2));
                break;
            case 3:
                v.executa(new Integer(3));
                break;
        }
        System.out.println("Registo efetuado com sucesso!");
    }
}
