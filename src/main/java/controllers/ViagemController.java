package controllers;

import annotations.Testable;
import exceptions.EmailDoesNotExistException;
import exceptions.ValueOutOfBoundsException;
import models.DB;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import models.viagem.Viagem;
import utils.Coordenada;
import views.View;
import views.veiculos.RealizarViagem;

import java.util.*;

/**
 * Created by danys on 12-Dec-17.
 */
public class ViagemController {

    @Testable
    public String closerMotorista(Coordenada loc, int nLugares){
        String email = null;
        int d=Integer.MAX_VALUE;
        double distancia = Double.MAX_VALUE;

        for(Motorista m : DB.getMotoristas()){
            if(m.getDisponibilidade() && m.getVeiculo().getLugares() >= nLugares && d>m.getVeiculo().getCoordenadas().distancia(loc))
            {
                if (m.getVeiculo().getCoordenadas().distancia(loc)<distancia ) {
                    email = m.getMail();
                    distancia = m.getVeiculo().getCoordenadas().distancia(loc);
                }
            }

        }
        return email;
    }

    public void realizarViagem( Coordenada inicio, Coordenada fim, int nLugares){
        View v = new RealizarViagem();

        String emailmotorista = closerMotorista(inicio, nLugares);
        if (emailmotorista == null) v.executa(null);

        List<Object> lista = new ArrayList<>();

        lista.add(emailmotorista);
        lista.add(inicio);
        lista.add(fim);

        v.executa(lista);
    }

    @Testable
    public void guardaViagem(Coordenada inicio, Coordenada fim, double preco, Motorista m, double distanciaAteCliente, double distanciaViagem, double fiab, double tempoReal, double tempoTotalEsperado, double diferenca) {

        Cliente c = (Cliente) Sessao.getUtilizador();
        c.registaViagem(new Viagem(inicio, fim, tempoReal, m.getMail(), new GregorianCalendar(), preco, diferenca));
        m.registaViagem(new Viagem(inicio, fim, tempoReal, c.getMail(), new GregorianCalendar(), preco, diferenca));
        m.atualizaDados(fim, distanciaViagem + distanciaAteCliente, fiab, diferenca/tempoTotalEsperado);
    }
}
