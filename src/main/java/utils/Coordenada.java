package utils;

/**
 * Escreva a descrição da classe Coordenada aqui.
 * 
 * @author (seu nome) 
 * @version v1(02052017)
 */
import annotations.Testable;

import java.io.Serializable;

public class Coordenada implements Serializable
{
    private double coorX;
    private double coorY;

    public Coordenada(){
        coorX=0;
        coorY=0;
    }

    public Coordenada(double x, double y){
        this.coorX=x;
        this.coorY=y;
    }

    public Coordenada(Coordenada a){
        this.coorX=a.getX();
        this.coorY=a.getY();
    }

    public double getX(){
        return this.coorX;
    }

    public double getY(){
        return this.coorY;
    }

    public int hashCode(){
        int hash=6;

        hash = 37 * hash + (int) this.coorX;
        hash = 37 * hash + (int) this.coorY;

        return hash;
    }

    @Testable
    public int compareTo(Coordenada b){
        return this.coorX == b.getX() ? (this.coorY==b.getY() ? 0 : this.coorY < b.getY() ? 1 : -1) : (this.coorX<b.getX() ? 1 : -1);
    }

    @Testable
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

    @Testable
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Coordenada X: "+coorX+" Coordenada Y: "+coorY);
        return s.toString();
    }

    @Testable
    public double distancia(Coordenada b){
        return  Math.sqrt( Math.pow( (coorX - b.getX()),2 ) + Math.pow( (coorY - b.getY()),2 ) );
    }
}
