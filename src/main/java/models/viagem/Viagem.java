package models.viagem;

import annotations.Testable;
import utils.Coordenada;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Viagem implements Comparable<Viagem>, Serializable
{

    private Coordenada cinicial;
    private Coordenada cfinal;
    private double tempo;
    private String mail;
    private GregorianCalendar data;
    private double preco;
    private double desvio;

    public Viagem(){
        cinicial = new Coordenada();
        cfinal = new Coordenada ();
        tempo = preco = desvio = 0;
        mail = "";
        data = new GregorianCalendar();
    }

    public Viagem (Coordenada cinicial , Coordenada cfinal , double tempo , String mail, GregorianCalendar data, double preco, double desvio){
        this.cinicial = cinicial.clone();
        this.cfinal = cfinal.clone();
        this.tempo = tempo;
        this.mail = mail;
        this.data = (GregorianCalendar)data.clone();
        this.preco = preco;
        this.desvio = desvio;
    }

    public Viagem(Viagem a){
        this.cinicial = a.getcinicial();
        this.cfinal = a.getcfinal();
        this.tempo = a.getTempo();
        this.mail = a.getMail();
        this.data = (GregorianCalendar)a.getData().clone();
        this.preco = a.getPreco();
        this.desvio = a.getDesvio();
    }

    public void setcinicial(Coordenada i){
        this.cinicial=i;
    }

    public void setcfinal(Coordenada i){
        this.cfinal=i;
    }

    public void setTempo(double i){
        this.tempo=i;
    }

    public void setMail(String i){
        this.mail=i;
    }

    public void setPreco(double i){
        this.preco=i;
    }

    public void setDesvio(double i){
        this.desvio=i;
    }

    public Coordenada getcinicial(){
        return this.cinicial.clone();
    }

    public Coordenada getcfinal(){
        return this.cfinal.clone();
    }

    public double getTempo(){
        return this.tempo;
    }

    public String getMail(){
        return this.mail;
    }

    public GregorianCalendar getData(){
        return this.data;
    }

    public double getPreco(){
        return this.preco;
    }

    public double getDesvio(){
        return this.desvio;
    }

    public int hashCode(){
        int hash = 5;
        long aux;

        hash = 37 * hash + this.cinicial.hashCode();
        hash = 37 * hash + this.cfinal.hashCode();
        aux = Double.doubleToLongBits(this.tempo);
        hash = 37 * hash + (int)(aux^(aux >>> 32));
        hash = 37 * hash + this.mail.hashCode();
        hash = 37*hash + this.data.hashCode();
        aux = Double.doubleToLongBits(this.preco);
        hash = 37 * hash + (int)(aux^(aux >>> 32));
        aux = Double.doubleToLongBits(this.desvio);
        hash = 37 * hash + (int)(aux^(aux >>> 32));

        return hash;
    }

    @Testable
    public int compareTo(Viagem b){


        int r;
        if (cinicial.compareTo(b.getcinicial() )==0 &&  cfinal.compareTo(b.getcfinal()) == 0 && this.tempo==b.getTempo() && this.desvio==b.getDesvio() ){
            r = 0;
        }else if(cinicial.compareTo(b.getcinicial() )<0 ||  cfinal.compareTo(b.getcfinal()) <0 || this.tempo<b.getTempo() || this.desvio<b.getDesvio()){
            r = -1;
        }else {
            r = 1;
        }
        return r;
    }

    @Testable
    public boolean equals(Object o) {
        if(o==this)
            return true;
        if(o==null || o.getClass() != this.getClass())
            return false;
        Viagem v = (Viagem) o;
        return v.getcinicial().equals(cinicial) && v.getcfinal().equals(cfinal) &&
                v.getMail().equals(mail) && v.getTempo() == tempo && this.data.equals(v.getData())
                && v.getPreco() == this.preco && v.getDesvio() == this.desvio;
    }

    public Viagem clone(){
        return new Viagem(this);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Coordenada inicial: "+cinicial.toString()+"\nCoordenada final: "+cfinal.toString());
        s.append("\nTempo que demorou a viagem: "+String.format("%.2f",tempo));
        s.append("\nMail do ator: "+mail);
        s.append("\nData da viagem: "+this.data.get(GregorianCalendar.DAY_OF_MONTH)+"-"+this.data.get(GregorianCalendar.MONTH)+"-"+this.data.get(GregorianCalendar.YEAR));
        s.append("\nPreço real da viagem: ").append(String.format("%.2f",this.preco))
                .append("\nDesvio entre o valor previsto e o preço real faturado: ").append(String.format("%.2f",this.desvio));
        return s.toString();
    }

}
