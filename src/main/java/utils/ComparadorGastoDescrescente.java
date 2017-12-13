package utils;
/**
 * Escreva a descrição da classe ComparadorGasto aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import models.utilizadores.Cliente;

import java.util.Comparator;

public class ComparadorGastoDescrescente implements Comparator<Cliente>
{
    public int compare(Cliente c1, Cliente c2){
        double ms1 = c1.getMS();
        double ms2 = c2.getMS();

        return ms1 > ms2 ? -1 : ms1 < ms2 ? 1 : 0;
    }
}
