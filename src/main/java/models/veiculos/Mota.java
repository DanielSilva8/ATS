package models.veiculos;

import annotations.Testable;
import utils.Coordenada;

import java.io.Serializable;

public class Mota extends Veiculo implements Serializable{

    public Mota(){
        super();
    }

    public Mota(int velocidademedia, double precobase, int fiabilidade, String matricula, Coordenada coordenadas, boolean ocupado){
        super(velocidademedia,precobase,fiabilidade,matricula,coordenadas,1,ocupado);
    }

    public Mota(Mota c){
        super(c);
    }

    public Mota clone() {
        return new Mota(this);
    }

    @Testable
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Mota c = (Mota) o;
        return super.equals(o);
    }
    
    public String toString(){
        return super.toString();
    }
}