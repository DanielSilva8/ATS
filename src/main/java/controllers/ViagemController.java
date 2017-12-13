package controllers;

import exceptions.EmailDoesNotExistException;
import exceptions.ValueOutOfBoundsException;
import models.DB;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import models.utilizadores.Utilizadores;
import models.viagem.Viagem;
import utils.Coordenada;

import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by danys on 12-Dec-17.
 */
public class ViagemController {

    public String closerMotorista(Coordenada loc, int nLugares){
        String email = null;
        int d=Integer.MAX_VALUE;

        for(Motorista m : DB.getMotoristas()){
            if(m.getDisponibilidade() && m.getVeiculo().getLugares() >= nLugares && d>m.getVeiculo().getCoordenadas().distancia(loc))
                email = m.getMail();
        }
        return email;
    }

    public String realizarViagem( Coordenada inicio, Coordenada fim, int nLugares){

        String emailmotorista = closerMotorista(inicio, nLugares);

        if(emailmotorista==null){
            return "Não existem motoristas que cumpram os seus requisitos disponíveis de momento.";
        }
        else{
            try{
                Motorista m =  (Motorista) DB.getUtilizadores().getAtor(emailmotorista);
                Cliente c = (Cliente) Sessao.getUtilizador();
                double distanciaAteCliente = inicio.distancia(m.getVeiculo().getCoordenadas());
                m.setDisponibilidade(false);
                double distanciaViagem = inicio.distancia(fim);
                double tempoTotalEsperado = m.tempoViagem(distanciaViagem + distanciaAteCliente);

                /** Calcula-se aqui o tempo real tendo em conta os condicionantes.*/
                Random r = new Random();
                double fiab = r.nextDouble() * (1+(double)(m.getVeiculo().getFiabilidade()/100));

                if(fiab > 1) fiab = 1; /** Garantimos que a fiabilidade não passa de 100 */

                double tempoReal = tempoTotalEsperado + (1 - fiab)*tempoTotalEsperado;

                double preco = m.precoViagem(distanciaViagem);

                double diferenca = tempoReal - tempoTotalEsperado;

                if(diferenca > 0.25 * tempoTotalEsperado){
                    System.out.println("Desculpe pelo atraso, terá um desconto de "+String.format("%.2f",50*diferenca/tempoTotalEsperado)+"%.");
                    preco = preco*(diferenca/tempoTotalEsperado);
                }
                System.out.println("Preço a pagar: "+ String.format("%.2f",preco)+"€");
                /** Atualização do dinheiro total investido no sistema UMeR por este cliente. */
                c.setMS(c.getMS() + preco);

                System.out.println("Atribua ao motorista um classificação entre 0 e 100.");
                Scanner sc = new Scanner(System.in);
                int classificacao;
                boolean flag = true;
                do{
                    try{
                        classificacao = sc.nextInt();
                    }
                    catch (InputMismatchException e){classificacao = (-1);}

                    try{
                        /** Atualização da classificação do motorista. */
                        m.atualizaClassificacao(classificacao);
                        flag = false;
                    }
                    catch (ValueOutOfBoundsException e){
                        System.out.println("Por favor, introduza uma classificação válida.");
                    }
                }while(flag);

                c.registaViagem(new Viagem(inicio, fim, tempoReal, m.getMail(), new GregorianCalendar(), preco, diferenca));
                m.registaViagem(new Viagem(inicio, fim, tempoReal, c.getMail(), new GregorianCalendar(), preco, diferenca));
                m.atualizaDados(fim, distanciaViagem + distanciaAteCliente, fiab, diferenca/tempoTotalEsperado);

                return "O motorista " + emailmotorista +" vem a caminho.";
            }
            catch(EmailDoesNotExistException e){System.out.println("Unexpected Error. Estrutura corrompida?");}
        }

        return "";
    }
}
