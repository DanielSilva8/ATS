package utils;
/**
 * Escreva a descrição da classe ComparadorMotoristaDesvioMaximo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import annotations.Testable;
import exceptions.NenhumaViagemException;
import models.utilizadores.Motorista;

import java.util.Comparator;

public class ComparadorMotoristaDesvioMaximo implements Comparator<Motorista>
{
    @Testable
    public int compare(Motorista m1, Motorista m2){
        double dMAX1 = 0;
        double dMAX2 = 0;
        try{
            dMAX1 = m1.maiorDesvio().getDesvio();
        }
        catch(NenhumaViagemException e){}
        
        try{
            dMAX2 = m2.maiorDesvio().getDesvio();
        }
        catch(NenhumaViagemException e){}
        
        return dMAX1 > dMAX2 ? -1 : (dMAX1 < dMAX2 ? 1 : 0);
    }
}
