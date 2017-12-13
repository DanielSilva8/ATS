package models.veiculos;
/**
 * Write a description of class Veiculo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import utils.Coordenada;

import java.io.Serializable;

public abstract class Veiculo implements Comparable<Veiculo>, Serializable{
    private int velocidademedia;
    private double precobase;
    private int fiabilidade;
    private String matricula;
    private Coordenada coordenadas;
    private int lugares;
    private boolean ocupado;

    public Veiculo(){
        this.velocidademedia = 0;
        this.precobase = 0;
        this.fiabilidade = 0;
        this.matricula = "n/a";
        this.coordenadas = new Coordenada();
        this.lugares = lugares;
        this.ocupado = false;
    }

    public Veiculo(int velocidademedia, double precobase, int fiabilidade, String matricula, Coordenada coordenadas, int lugares, boolean ocupado){
        this.velocidademedia = velocidademedia;
        this.precobase = precobase;
        this.fiabilidade = fiabilidade;
        this.matricula = matricula;
        this.coordenadas = new Coordenada(coordenadas);
        this.lugares = lugares;
        this.ocupado = ocupado;
    }

    public Veiculo(Veiculo v){
        this.velocidademedia = v.getVelocidadeMedia();
        this.precobase = v.getPrecoBase();
        this.fiabilidade = v.getFiabilidade();
        this.matricula = v.getMatricula();
        this.coordenadas = new Coordenada(v.getCoordenadas());
        this.lugares = v.getLugares();
        this.ocupado = v.getOcupado();
    }

    public int getVelocidadeMedia(){
        return this.velocidademedia;
    }
    public double getPrecoBase(){
        return this.precobase;
    }
    public int getFiabilidade(){
        return this.fiabilidade;
    }
    public String getMatricula(){
        return this.matricula;
    }
    public Coordenada getCoordenadas(){
        return this.coordenadas.clone();
    }
    public int getLugares(){
        return this.lugares;
    }
    public boolean getOcupado(){
        return this.ocupado;
    }
    public void setVelocidadeMedia(int velocidademedia){
        this.velocidademedia = velocidademedia;
    }
    public void setPrecoBase(double precobase){
        this.precobase = precobase;
    }
    public void setFiabilidade(int fiabilidade){
        this.fiabilidade = fiabilidade;
    }
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    public void setCoordenadas(Coordenada coordenadas){
        this.coordenadas = new Coordenada(coordenadas);
    }
    public void setOcupado(boolean ocupado){
        this.ocupado = ocupado;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Veiculo v = (Veiculo) o;
        return v.getMatricula().equals(this.matricula) && v.getVelocidadeMedia() == this.velocidademedia 
                && v.getPrecoBase() == this.precobase && v.getFiabilidade() == this.fiabilidade
                && this.coordenadas.equals(v.getCoordenadas()) && v.getLugares() == this.lugares
                && v.getOcupado() == this.ocupado;
    }

    public abstract Veiculo clone();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrícula: ").append(this.matricula).append("\n");
        sb.append("Velocidade Média/km: ").append(this.velocidademedia).append("km/h").append("\n");
        sb.append("Preço Base: ").append(this.precobase).append("€").append("\n");
        sb.append("Fiabilidade: ").append(this.fiabilidade).append("\n");
        sb.append("Lugares: ").append(this.lugares).append("\n");
        sb.append("Coordenadas: ").append(this.getCoordenadas().toString()).append("\n");
        sb.append("Ocupado: ").append(this.ocupado).append("\n");
        return sb.toString();
    }

    public int hashCode() {
        int hash = 2;
        long aux;
        
        hash = 37 * hash + this.velocidademedia;
        aux = Double.doubleToLongBits(this.precobase);
        hash = 37 * hash + (int)(aux^(aux >>> 32));
        hash = 37 * hash + this.fiabilidade;
        hash = 37 * hash + this.matricula.hashCode();
        hash = 37 * hash + this.lugares;
        hash = 37 * hash + (this.ocupado ? 1 : 0);
        
        return hash;
    }

    public int compareTo(Veiculo v) {
        return v.getMatricula()
                .compareTo(this.matricula);
    }
}
