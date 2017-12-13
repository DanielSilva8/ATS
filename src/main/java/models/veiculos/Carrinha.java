package models.veiculos;
/**
 * Write a description of class Carrinha here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import utils.Coordenada;

import java.io.Serializable;

public class Carrinha extends Veiculo implements Serializable{

    public Carrinha(){
        super();
    }

    public Carrinha(int velocidademedia, double precobase, int fiabilidade, String matricula, Coordenada coordenadas, boolean ocupado){
        super(velocidademedia,precobase,fiabilidade,matricula,coordenadas,9,ocupado);
    }

    public Carrinha(Carrinha c){
        super(c);
    }

    public Carrinha clone() {
        return new Carrinha(this);
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Carrinha c = (Carrinha) o;
        return super.equals(o);
    }

    public String toString(){
        return super.toString();
    }
}