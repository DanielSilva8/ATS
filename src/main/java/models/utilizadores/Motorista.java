package models.utilizadores;
/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import exceptions.ValueOutOfBoundsException;
import utils.Coordenada;
import models.veiculos.Veiculo;
import models.viagem.Viagem;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;

public class Motorista extends Ator implements Serializable
{
    // variáveis de instância 
    private int grau; //Grau de cumprimento de horário estabelecido com o cliente, dado por um fator entre 0 e 100.
    private int classificacao; //Classificação do motorista, calculada com base na classificacao pelo cliente no final viagem.
    private int numClassi; //Número de classificações feitas ao cliente.
    private double kmsTotais; //Número de kms ja realizados na UMeR
    private boolean disponivel; //true -> o motorista está em horario de trabalho; false caso contrário
    private Veiculo taxi; //Veiculo utilizado pelo motorista.
    
    /**
     * Construtor Vazio
     */
    public Motorista(){
        super();
        this.grau = this.classificacao = this.numClassi = 0;
        this.kmsTotais = 0;
        this.disponivel = false;
        this.taxi = null;
    }
    
    /**
     * Construtor por cópia.
     * @param m
     */
    public Motorista(Motorista m){
        super(m);
        this.grau = m.getGrau();
        this.classificacao = m.getClassificacao();
        this.numClassi = m.getNumClassi();
        this.kmsTotais = m.getKmsTotais();
        this.disponivel = m.getDisponibilidade();
        this.taxi = m.getVeiculo().clone();
    }

    public Motorista(String email, String nome, String pw, String morada, String dDN, Set<Viagem> hv, int grau, int classificacao, int numClassi, double kmsTotais, boolean disponivel, Veiculo taxi){
        super(email,nome,pw,morada,dDN,hv);
        this.grau = grau;
        this.classificacao = classificacao;
        this.numClassi = numClassi;
        this.kmsTotais = kmsTotais;
        this.disponivel = disponivel;
        this.taxi = taxi.clone();
    }

    public Motorista(String email, String nome, String pw, String morada, String dDN, Veiculo taxi){
        super(email,nome,pw,morada,dDN,new TreeSet<Viagem>());
        this.grau = this.classificacao = 100;
        this.kmsTotais = 0;
        this.disponivel = false;
        if(taxi != null) this.taxi = taxi.clone();
    }

    public int getGrau(){
        return this.grau;
    }

    public int getClassificacao(){
        return this.classificacao;
    }

    public int getNumClassi(){
        return this.numClassi;
    }

    public double getKmsTotais(){
        return this.kmsTotais;
    }

    public boolean getDisponibilidade(){
        return this.disponivel;
    }

    public void setDisponibilidade(boolean disp){
        this.disponivel = disp;
    }

    public Veiculo getVeiculo(){
        return this.taxi;
    }

    public void setVeiculo(Veiculo v){
        this.taxi = v;
    }

    public void atualizaClassificacao(int classi) throws ValueOutOfBoundsException {
        if(classi < 0 || classi > 100) throw new ValueOutOfBoundsException();
        else{
            //Primeira classificação ao motorista
            if(this.numClassi == 0){
                this.classificacao = classi;
                this.numClassi++;
            }
            else{
                this.classificacao = ((this.classificacao * this.numClassi) + classi) / (this.numClassi + 1);
                this.numClassi++;
            }
        }
    }

    public double tempoViagem(double distancia){
        double v = (double)this.taxi.getVelocidadeMedia();
        return distancia/v;
    }

    public double precoViagem(double dist){
        return this.taxi.getPrecoBase()*dist;
    }

    //TODO alterar dif para grau
    public void atualizaDados(Coordenada novaLoc, double distPercorrida, double fiabilidade, double dif){
        this.kmsTotais += distPercorrida;
        this.taxi.setCoordenadas(novaLoc);
        this.grau = (int)((this.grau * (this.getHistorico().size()-1) + 100*dif)/ this.getHistorico().size());
        this.taxi.setFiabilidade((int)(fiabilidade*100));
        this.disponivel = true; //Após realizar a viagem o motorista volta a estar disponível para ser solicitado por outro cliente.
    }

    public double totalFaturado(){
        return this.getHistorico().stream()
                                  .mapToDouble(Viagem :: getPreco)
                                  .sum();
    }

    public double totalFaturado(GregorianCalendar inicio, GregorianCalendar fim){
        return this.getHistorico().stream()
                                  .filter(v -> v.getData().compareTo(inicio) >= 0 && v.getData().compareTo(fim) <= 0)
                                  .mapToDouble(Viagem :: getPreco)
                                  .sum();
    }
    
    //Métodos usuais
    public boolean equals(Object o){
        if(o == this) return true;
        
        if(o == null || (o.getClass() != this.getClass())) return false;
        
        Motorista m = (Motorista)o;
        
        return super.equals(m) && (this.hashCode() == m.hashCode()) && (this.grau == m.getGrau()) 
                               && (this.classificacao == m.getClassificacao()) && (this.kmsTotais == m.getKmsTotais()) 
                               && (this.disponivel == m.getDisponibilidade()) && this.taxi.equals(m.getVeiculo());
    }

    public int hashCode(){
        int hash = 7;
        
        hash = 37*hash + super.hashCode();
        hash = 37*hash + this.grau;
        hash = 37*hash + this.classificacao;
        long dbTol = Double.doubleToLongBits(this.kmsTotais);
        hash = 37*hash + (int)(dbTol ^ (dbTol >>> 32));
        hash = 37*hash + (this.disponivel ? 1 : 0);
        hash = 37*hash + this.taxi.hashCode();
        
        return hash;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append("\n")
         .append("Grau de cumprimento de horário: ").append(this.grau).append("\n")
         .append("Classificação do motorista: ").append(this.classificacao).append("\n")
         .append("Número de Kms realizados na UMeR até ao momento: ").append(String.format("%.2f",this.kmsTotais)).append("\n")
         .append("O motorista encontra-se em horário de trabalho: ").append(this.disponivel).append("\n");
        if(taxi != null) s.append("Veiculo:"+this.taxi.toString());
           
        return s.toString();
    }

    public Motorista clone(){
        return new Motorista(this);
    }

    public int compareTo(Motorista m){
        int r;
        r = super.compareTo(m);
        
        if(r == 0){
            int g = m.getGrau();
            if(this.grau > g) r = 1;
            else if(this.grau < g) r = -1;
                 else r = 0;
        }
        
        if(r == 0){
            int c = m.getClassificacao();
            if(this.classificacao > c) r = 1;
            else if(this.classificacao < c) r = -1;
                 else r = 0;
        }
        
        if(r == 0){
            double k = m.getKmsTotais();
            if(this.kmsTotais > k) r = 1;
            else if(this.kmsTotais < k) r = -1;
                 else r = 0;
        }
        
        if(r == 0) r = (this.disponivel == m.getDisponibilidade() ? 0 : (m.getDisponibilidade() ? 1 : -1));
        
        if(r == 0) r = this.taxi.compareTo(m.getVeiculo());
        
        return r;
    }
}
