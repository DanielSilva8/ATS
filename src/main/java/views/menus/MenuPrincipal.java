package views.menus;

import views.View;
import views.utilizadores.LoginUtilizadores;

/**
 * Created by danys on 11-Dec-17.
 */
public class MenuPrincipal extends Menu {

    static final String[] opcoes = {"Login", "Registar", "Extra", "Sair"};
    static final String cabecalho = " *** Menu *** ";
    static Menu menuRegisto = new MenuRegisto();
    static Menu menuFuncionalidades = new MenuFuncionalidades();
    View v = new LoginUtilizadores();

    public MenuPrincipal() {
        super(opcoes, cabecalho);
    }

    @Override
    public void redireciona() {

        switch (this.getOpcao()) {
            case 1:
                v.executa(null);
                break;
            case 2:
                menuRegisto.executa();
                break;
            case 3:
                menuFuncionalidades.executa();
                break;
        }
        if (getOpcao() != 0)
            this.executa();
    }
}
