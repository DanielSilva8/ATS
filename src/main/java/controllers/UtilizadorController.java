package controllers;

import annotations.Testable;
import exceptions.EmailAlreadyInUseException;
import exceptions.EmailDoesNotExistException;
import models.DB;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import views.View;
import views.utilizadores.DetalhesUtilizador;
import views.utilizadores.HistoricoUtilizador;
import views.utilizadores.MotoristaMontanteFaturado;
import views.veiculos.AssociarViatura;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danys on 12-Dec-17.
 */
public class UtilizadorController {

    public void getDetalhes(){
        View v = new DetalhesUtilizador();
        v.executa(Sessao.getUtilizador());
    }
    public void getHistorico(){
        View v = new HistoricoUtilizador();
        v.executa(Sessao.getUtilizador());
    }
    public void getMontanteFaturado(){

        View v = new MotoristaMontanteFaturado();
        v.executa(Sessao.getUtilizador());
    }
    public void associarViatura(){

        Motorista m = (Motorista) Sessao.getUtilizador();

        if(m.getVeiculo()!=null){
            m.getVeiculo().setOcupado(false);
            m.setVeiculo(null);
        }
        List<Object> lista = new ArrayList<>();
        lista.add(DB.getVeiculos());
        lista.add(m);
        View v = new AssociarViatura();
        v.executa(lista);
    }

    @Testable
    public boolean registarUtilizador(String email, String nome, String password, String morada, String data, int tipo) {

        try {
            switch (tipo) {
                case 1:
                    DB.adicionaCliente(email, nome, password, morada, data);
                    break;
                case 2:
                    DB.adicionaMotorista(email, nome, password, morada, data);
                    break;
            }
            return true;
        }
        catch(EmailAlreadyInUseException e){
            return false;
        }
    }

    @Testable
    public int login(String email, String password) {
        Utilizadores utilizadores = DB.getUtilizadores();
        if(utilizadores.getUtilizadores().containsKey(email) && utilizadores.getUtilizadores().get(email).getPassword().equals(password)){
            try {
                Sessao.criar(utilizadores.getAtor(email));
            } catch (EmailDoesNotExistException e) {
                e.printStackTrace();
            }
            if (utilizadores.getUtilizadores().get(email) instanceof Cliente)
                return 0;
            else
                return 1;
        }
        else
            return -1;
    }
}
