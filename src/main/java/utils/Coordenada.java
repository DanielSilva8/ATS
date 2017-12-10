package utils;

/**
 * Escreva a descrição da classe Coordenada aqui.
 * 
 * @author (seu nome) 
 * @version v1(02052017)
 */
import java.io.Serializable;
//TODO Serializable??

public class Coordenada implements Serializable
{
    private int coorX;
    private int coorY;

    public Coordenada(){
        coorX=0;
        coorY=0;
    }

    public Coordenada(int x, int y){
        this.coorX=x;
        this.coorY=y;
    }

   public Coordenada(Coordenada a){
        this.coorX=a.getX();
        this.coorY=a.getY();
    }
    

    public int getX(){
        return this.coorX;
   }

    public int getY(){
        return this.coorY;
   }

    public int hashCode(){
        int hash=6;
        
        hash = 37 * hash + this.coorX;   //TODO REFACTOR
        hash = 37 * hash + this.coorY;
        
        return hash;
    }

   public int compareTo(Coordenada b){
       if(this.coorX==b.getX()){
           if(this.coorY==b.getY()) return 0;
           if(this.coorY<b.getY()) return 1;
           else return -1;
       }
       if(this.coorX<b.getX())return 1;
       else return -1;
    }

   public boolean equals(Object o) {
       if(o==this)
           return true;
       if(o==null || o.getClass() != this.getClass()) 
           return false;
       Coordenada v = (Coordenada) o;
       return v.getX()==coorX && v.getY()==coorY;
   }

   public Coordenada clone(){
       return new Coordenada(this);
   }

   public String toString(){
       StringBuilder s = new StringBuilder();
       s.append("Coordenada X: "+coorX+" Coordenada Y: "+coorY);
       return s.toString();
   }
   
   public double distancia(Coordenada b){
       return  Math.sqrt( Math.pow( (coorX - b.getX()),2 ) + Math.pow( (coorY - b.getY()),2 ) );
   }
}
