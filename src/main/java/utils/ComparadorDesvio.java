package utils;
/**
 * Escreva a descrição da classe ComparadorDesvio aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import annotations.Testable;
import models.viagem.Viagem;
import java.util.Comparator;

public class ComparadorDesvio implements Comparator<Viagem>
{
    @Testable
    public int compare(Viagem v1, Viagem v2){
        double d1 = v1.getDesvio();
        double d2 = v2.getDesvio();
        
        return d1 > d2 ? 1 : (d1 < d2 ? (-1) : 0);
    }
}
