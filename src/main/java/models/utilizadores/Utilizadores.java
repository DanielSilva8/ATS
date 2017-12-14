package models.utilizadores;

import exceptions.EmailAlreadyInUseException;
import exceptions.EmailDoesNotExistException;
import utils.ComparadorGastoDescrescente;
import utils.ComparadorMotoristaDesvioMaximo;
import models.veiculos.Veiculo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utilizadores implements Serializable
{

    private Map<String,Ator> atores;

    public Utilizadores(){
        this.atores = new HashMap<>();
    }

    public Utilizadores(Utilizadores u){
        this.atores = new HashMap<>();
        
        for(Ator a : u.getUtilizadores().values())
            this.atores.put(a.getMail(),a.clone());
        
    }

    public Utilizadores(Map<String,Ator> utilizadores){
        this.atores = new HashMap<>();
        
        utilizadores.values().forEach(a -> this.atores.put(a.getMail(),a.clone()));
    }

    public Map<String,Ator> getUtilizadores(){
        return this.atores;
    }

    public void adiciona(Ator a) throws EmailAlreadyInUseException {
        if(this.atores.containsKey(a.getMail())) throw new EmailAlreadyInUseException("Já existe um utilizador com esse e-mail.");
        else this.atores.put(a.getMail(),a.clone());
    }

    public void adiciona(String mail, String nome, String pw, String morada, String dDN) throws EmailAlreadyInUseException{
        if(this.atores.containsKey(mail)) throw new EmailAlreadyInUseException("Já existe um utilizador com esse e-mail.");
        else this.atores.put(mail, new Cliente(mail,nome,pw,morada,dDN));
    }

    public void adiciona(String mail, String nome, String pw, String morada, String dDN, Veiculo taxi) throws EmailAlreadyInUseException{
        if(this.atores.containsKey(mail)) throw new EmailAlreadyInUseException("Já existe um utilizador com esse e-mail.");
        else this.atores.put(mail, new Motorista(mail,nome,pw,morada,dDN,taxi));
    }

    public Ator getAtor(String email) throws EmailDoesNotExistException {
        Ator a = this.atores.get(email);
        if(a == null) throw new EmailDoesNotExistException();
        else return a;
    }

    public List<Cliente> top10ClientesGastadores(){
        return this.atores.values().stream()
                                   .filter(a -> a instanceof Cliente)
                                   .map(a -> (Cliente)a)
                                   .map(Cliente :: clone)
                                   .sorted(new ComparadorGastoDescrescente())
                                   .limit(10)
                                   .collect(Collectors.toList());
                                    
    }

    public List<Motorista> top5MotoristasComMaiorDesvio(){
        return this.atores.values().stream()
                                   .filter(a -> a instanceof Motorista)
                                   .map(a -> (Motorista)a)
                                   .filter(m -> m.getHistorico().size() != 0)
                                   .map(Motorista :: clone)
                                   .sorted(new ComparadorMotoristaDesvioMaximo())
                                   .limit(5)
                                   .collect(Collectors.toList());
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append("------- UMeR --------").append("\n");
        this.atores.values().forEach(a -> s.append(a.toString()).append("\n"));
        
        return s.toString();
    }

    public Utilizadores clone(){
        return new Utilizadores(this);
    }

    public int hashCode(){
        int hash = 7;
        
        for(Ator a : this.atores.values())
          hash = 37*hash + a.hashCode();
        
        return hash;
    }

    public boolean equals(Object o){
        if(o == this) return true;
        
        if(o == null || (o.getClass() != this.getClass())) return false;
        
        Utilizadores u = (Utilizadores) o;
        
        if(this.atores.size() != u.getUtilizadores().size() || this.hashCode() != u.hashCode()) return false;
        
        boolean iguais = true;
        Iterator<Ator> it1 = this.atores.values().iterator();
        Iterator<Ator> it2 = u.getUtilizadores().values().iterator();
        
        while(iguais && it1.hasNext() && it2.hasNext()){
            Ator a1 = it1.next();
            Ator a2 = it2.next();
            iguais = a1.equals(a2);
        }
        
        return iguais;
    }

    public int compareTo(Utilizadores u){
        int r = 0;
        
        Iterator<Ator> it1 = this.atores.values().iterator();
        Iterator<Ator> it2 = u.getUtilizadores().values().iterator();
        
        while(r == 0 && it1.hasNext() && it2.hasNext()){
            Ator a1 = it1.next();
            Ator a2 = it2.next();
            r = a1.compareTo(a2);
        }
        if(r == 0 && it1.hasNext()) r = 1;
        else if(r == 0 && it2.hasNext()) r = (-1);
        
        return r;
    }
}
