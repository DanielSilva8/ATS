package views.veiculos;

import controllers.Sessao;
import controllers.ViagemController;
import exceptions.EmailDoesNotExistException;
import exceptions.ValueOutOfBoundsException;
import models.DB;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import utils.Coordenada;
import views.View;

import java.util.*;

/**
 * Created by danys on 14-Dec-17.
 */
public class RealizarViagem implements View {
    @Override
    public void executa(Object a) {

        if(a==null){
             System.out.println( "Não existem motoristas que cumpram os seus requisitos disponíveis de momento.");
        }
        else{
            List<Object> lista = (ArrayList) a;
            String emailmotorista = (String) lista.get(0);
            Coordenada inicio = (Coordenada) lista.get(1);
            Coordenada fim = (Coordenada) lista.get(2);
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

                if(fiab > 1) fiab = 1;
                double tempoReal = tempoTotalEsperado + (1 - fiab)*tempoTotalEsperado;
                double preco = m.precoViagem(distanciaViagem);
                double diferenca = tempoReal - tempoTotalEsperado;

                if(diferenca > 0.25 * tempoTotalEsperado){
                    System.out.println("Desculpe pelo atraso, terá um desconto de "+String.format("%.2f",50*diferenca/tempoTotalEsperado)+"%.");
                    preco = preco*(diferenca/tempoTotalEsperado);
                }
                System.out.println("Preço a pagar: "+ String.format("%.2f",preco)+"€");
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
                        m.atualizaClassificacao(classificacao);
                        flag = false;
                    }
                    catch (ValueOutOfBoundsException e){
                        System.out.println("Por favor, introduza uma classificação válida.");
                    }
                }while(flag);

                ViagemController v = new ViagemController();
                v.guardaViagem(inicio,  fim,  preco,  m,  distanciaAteCliente,  distanciaViagem,  fiab, tempoReal, tempoTotalEsperado,  diferenca);

            }
            catch(EmailDoesNotExistException e){System.out.println("Unexpected Error. Estrutura corrompida?");}
        }
    }
}
