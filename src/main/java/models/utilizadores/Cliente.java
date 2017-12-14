package models.utilizadores;

import models.viagem.Viagem;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Cliente extends Ator implements Serializable
{

    private double moneySpent;

    public Cliente(){
        super();
        this.moneySpent = 0;
    }

    public Cliente(Cliente c){
        super(c);
        this.moneySpent = c.getMS();
    }

    public Cliente(String email, String nome, String pw, String morada, String dDN, Set<Viagem> historico, double ms){
        super(email,nome,pw,morada,dDN,historico);
        this.moneySpent = ms;
    }

    public Cliente(String email, String nome, String pw, String morada, String dDN){
        super(email,nome,pw,morada,dDN, new TreeSet<Viagem>());
        this.moneySpent = 0;
    }

    public double getMS(){
        return this.moneySpent;
    }

    public void setMS(double ms){
        this.moneySpent = ms;
    }

    public boolean equals(Object o){
        if(o == this) return true;

        if(o == null || (o.getClass() != this.getClass())) return false;

        Cliente c = (Cliente) o;

        return super.equals(c) && this.moneySpent == c.getMS();
    }

    public int hashCode(){
        int hash = 7;

        hash = 37*hash + super.hashCode();
        long dbTol = Double.doubleToLongBits(this.moneySpent);
        hash = 37*hash + (int)(dbTol ^ (dbTol >>> 32));

        return hash;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        s.append(super.toString())
                .append("Montante de dinheiro utilizado no serviço UMeR: ").append(String.format("%.2f",this.moneySpent)).append("\n");

        return s.toString();
    }

    public Cliente clone(){
        return new Cliente(this);
    }

    public int compareTo(Cliente c){
        int r = super.compareTo(c);

        if(r == 0) r = this.moneySpent == c.getMS() ? 0 : (this.moneySpent > c.getMS() ? 1 : (-1));

        return r;
    }
}
