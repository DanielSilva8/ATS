package views.menus;

import views.View;
import views.utilizadores.RegistoUtilizadores;

/**
 * Created by danys on 12-Dec-17.
 */
public class MenuRegisto extends Menu {

    static final String[] opcoes = {"Motorista", "Cliente", "Veiculo"};
    static final String cabecalho = "-----------Tipo de registo-----------";
    View v = new RegistoUtilizadores();

    public MenuRegisto() {
        super(opcoes, cabecalho);
    }

    @Override
    public void redireciona() {

        switch (getOpcao()) {
            case 1:
                v.executa(Integer.parseInt("1"));
                break;
            case 2:
                v.executa(Integer.parseInt("2"));
                break;
            case 3:
                new MenuRegistoVeiculo().executa();
                break;

        }
        System.out.println("Registo efetuado com sucesso!");
    }
}
