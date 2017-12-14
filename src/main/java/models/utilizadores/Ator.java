package models.utilizadores;

import exceptions.InvalidIntervalException;
import exceptions.NenhumaViagemException;
import models.viagem.Viagem;
import utils.ComparadorDesvio;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Ator implements Comparable<Ator>, Serializable
{
    private String email;
    private String nome;
    private String pw;
    private String morada;
    private String dataDeNascimento;
    private Set<Viagem> historico;

    public Ator(){
        this.email = this.nome = this.pw = this.morada = this.dataDeNascimento = "";
        this.historico = new TreeSet<>();
    }

    public Ator(Ator a){
        this.email = a.getMail();
        this.nome = a.getNome();
        this.pw = a.getPassword();
        this.morada = a.getMorada();
        this.dataDeNascimento = a.getDataDeNascimento();
        this.historico = a.getHistorico();
    }

    public Ator(String email, String nome, String pw, String morada, String dDN, Set<Viagem> hist){
        this.email = email;
        this.nome = nome;
        this.pw = pw;
        this.morada = morada;
        this.dataDeNascimento = dDN;
        this.historico = new TreeSet<>();

        for(Viagem v : hist)
            this.historico.add(v.clone());
    }

    public String getMail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getPassword(){
        return this.pw;
    }

    public String getMorada(){
        return this.morada;
    }

    public String getDataDeNascimento(){
        return this.dataDeNascimento;
    }

    public Set<Viagem> getHistorico(){

        return this.historico;
    }

    public void registaViagem(Viagem rv){
        this.historico.add(rv.clone());
    }

    public List<Viagem> viagensEntreDatas(GregorianCalendar inicio, GregorianCalendar fim) throws InvalidIntervalException {
        if(inicio.compareTo(fim) >0) throw new InvalidIntervalException();
        else{
            return this.historico.stream()
                    .filter(v -> v.getData().compareTo(inicio) >= 0 && v.getData().compareTo(fim) <= 0)
                    .map(Viagem :: clone)
                    .collect(Collectors.toList());
        }
    }

    public Viagem maiorDesvio() throws NenhumaViagemException {
        if(this.historico.size() == 0) throw new NenhumaViagemException();
        else return this.historico.stream()
                .max(new ComparadorDesvio())
                .get()
                .clone();
    }

    public boolean equals(Object o){
        if(o == this) return true;

        if(o == null || (o.getClass() != this.getClass())) return false;
        Ator at = (Ator) o;
        return (this.hashCode() == at.hashCode()) && this.email.equals(at.getMail()) && this.nome.equals(at.getNome()) && this.pw.equals(at.getPassword()) && this.morada.equals(at.getMorada())
                && this.dataDeNascimento.equals(at.getDataDeNascimento()) && this.getHistorico() == at.getHistorico();
    }

    public int hashCode(){
        int hash = 7;

        hash = 37*hash + this.email.hashCode();
        hash = 37*hash + this.nome.hashCode();
        hash = 37*hash + this.pw.hashCode();
        hash = 37*hash + this.morada.hashCode();
        hash = 37*hash + this.dataDeNascimento.hashCode();
        for(Viagem v : this.historico)
            hash = 37*hash + v.hashCode();

        return hash;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("E-mail: ").append(this.email).append("\n")
                .append("Nome: ").append(this.nome).append("\n")
                .append("Password: ").append(this.pw).append("\n")
                .append("Morada: ").append(this.morada).append("\n")
                .append("Data de nascimento: ").append(this.dataDeNascimento);

        this.historico.forEach(v -> s.append(v.toString()).append("\n"));

        return s.toString();
    }

    public abstract Ator clone();

    public int compareTo(Ator a){
        int r;
        r = this.email.compareTo(a.getMail());
        if(r == 0) r = this.nome.compareTo(a.getNome());
        if(r == 0) r = this.pw.compareTo(a.getPassword());
        if(r == 0) r = this.morada.compareTo(a.getMorada());
        if(r == 0) r = this.dataDeNascimento.compareTo(a.getDataDeNascimento());
        if(r == 0){
            Iterator<Viagem> it1 = this.historico.iterator();
            Iterator<Viagem> it2 = a.getHistorico().iterator();
            while(r == 0 && it1.hasNext() && it2.hasNext()){
                Viagem v1 = it1.next();
                Viagem v2 = it2.next();
                r = v1.compareTo(v2);
            }
            if(r == 0 && it1.hasNext()) r = 1;
            else if(r == 0 && it2.hasNext()) r = (-1);
        }

        return r;
    }
}
