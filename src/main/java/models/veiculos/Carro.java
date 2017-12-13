package models.veiculos;
/**
 * Write a description of class Carro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import utils.Coordenada;

import java.io.Serializable;

public class Carro extends Veiculo implements Serializable{

    public Carro(){
        super();
    }

    public Carro(int velocidademedia, double precobase, int fiabilidade, String matricula, Coordenada coordenadas, boolean ocupado){
        super(velocidademedia,precobase,fiabilidade,matricula,coordenadas,4,ocupado);
    }

    public Carro(Carro c){
        super(c);
    }

    public Carro clone() {
        return new Carro(this);
    }

    public boolean equals(Object o) {

        if(o == this) {
            return true;
        }
        if(o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Carro c = (Carro) o;
        return super.equals(o);
    }    

    public String toString(){
        return super.toString();
    }
}
