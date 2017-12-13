package views.menus;

import controllers.Sessao;
import controllers.UtilizadorController;
import models.utilizadores.Motorista;
import utils.Coordenada;

/**
 * Created by danys on 12-Dec-17.
 */
public class MenuMotorista extends Menu {

    static final String[] opcoes = {"Mudar status", "Ver dados pessoais", "Ver histórico de viagens", "Associar viatura", "Ver dados da viatura" ,"Ver montante faturado num período de tempo","Logout"};
    static final String cabecalho = "-----------Bem-vindo "+ Sessao.getUtilizador().getNome() + "-----------\n" + (((Motorista)Sessao.getUtilizador()).getDisponibilidade() ? "--Status : Em horário de trabalho--":"--Status : Fora de horário de trabalho--");
    Motorista m = (Motorista)Sessao.getUtilizador();

    public MenuMotorista() {
        super(opcoes, cabecalho);
    }

    @Override
    public void redireciona() {
        UtilizadorController user = new UtilizadorController();
        switch (getOpcao()) {
            case 1:
                if(!m.getDisponibilidade() && m.getVeiculo()==null)
                    System.out.println("Só pode estar em horário de trabalho se tiver uma viatura associada(Opção 4)");
                else if(m.getDisponibilidade()){
                    m.getVeiculo().setCoordenadas(new Coordenada(0,0));
                    m.getVeiculo().setOcupado(false);
                    m.setVeiculo(null);
                    m.setDisponibilidade(false);
                }
                else
                    m.setDisponibilidade(!m.getDisponibilidade());
                break;
            case 2:
                user.getDetalhes();
                break;
            case 3:
                user.getHistorico();
                break;
            case 4:
                user.associarViatura();
                break;
            case 5:
                if(m.getVeiculo()!=null) System.out.println(m.getVeiculo().toString());
                break;
            case 6:
                user.getMontanteFaturado();
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


