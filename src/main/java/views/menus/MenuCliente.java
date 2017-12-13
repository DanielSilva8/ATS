package views.menus;

import controllers.Sessao;
import controllers.UtilizadorController;
import views.View;
import views.veiculos.ChamarViatura;

public class MenuCliente extends Menu {
    UtilizadorController user = new UtilizadorController();
    View chamarViatura = new ChamarViatura();
    static final String[] opcoes = {"Chamar viatura", "Ver dados pessoais", "Ver histórico de viagens", "Logout"};
    static final String cabecalho = "-----------Bem-vindo "+ Sessao.getUtilizador().getNome() + "-----------";

    public MenuCliente() {
        super(opcoes, cabecalho);
    }

    @Override
    public void redireciona() {

        switch (getOpcao()) {
            case 1:
                chamarViatura.executa(null);
                break;
            case 2:
                user.getDetalhes();
                break;
            case 3:
                user.getHistorico();
                break;
            case 0:
                System.out.println("Até breve!");
                Sessao.terminar();
                break;
        }
        if (getOpcao() != 0)
            this.executa();
    }
}


