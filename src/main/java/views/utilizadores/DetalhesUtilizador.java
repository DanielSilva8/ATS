package views.utilizadores;

import models.utilizadores.Ator;
import models.utilizadores.Cliente;
import models.utilizadores.Motorista;
import views.View;

/**
 * Created by danys on 12-Dec-17.
 */
public class DetalhesUtilizador implements View {

    @Override
    public void executa(Object object) {

        Ator a = (Ator) object;

        System.out.println("\n-----------Dados pessoais-----------");
        System.out.println("Email: " + a.getMail());
        System.out.println("Nome: " + a.getNome());
        System.out.println("Password: " + a.getPassword());
        System.out.println("Morada: " + a.getMorada());
        System.out.println("Data de nascimento: " + a.getDataDeNascimento());
        if(a instanceof Cliente){
            Cliente c = (Cliente) a;
            System.out.println("Dinheiro gasto em serviços: " + String.format("%.2f",c.getMS()) +"€");
        }
        else{
            Motorista m = (Motorista) a;
            System.out.println("Grau de cumprimento: " + m.getGrau());
            System.out.println("Classificação: " + m.getClassificacao());
            System.out.println("Kms totais: " + String.format("%.2f",m.getKmsTotais()));
        }
    }
}
