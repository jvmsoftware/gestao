package br.com.jvmsoftware.entities;
// Generated 4/Ago/2015 22:25:45 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubPosicao generated by hbm2java
 */
public class PubPosicao  implements java.io.Serializable {


     private Integer idPosicao;
     private String posicao;
     private String descricao;
     private Set<PubConteudo> pubConteudos = new HashSet<PubConteudo>(0);

    public PubPosicao() {
    }

	
    public PubPosicao(String posicao, String descricao) {
        this.posicao = posicao;
        this.descricao = descricao;
    }
    public PubPosicao(String posicao, String descricao, Set<PubConteudo> pubConteudos) {
       this.posicao = posicao;
       this.descricao = descricao;
       this.pubConteudos = pubConteudos;
    }
   
    public Integer getIdPosicao() {
        return this.idPosicao;
    }
    
    public void setIdPosicao(Integer idPosicao) {
        this.idPosicao = idPosicao;
    }
    public String getPosicao() {
        return this.posicao;
    }
    
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set<PubConteudo> getPubConteudos() {
        return this.pubConteudos;
    }
    
    public void setPubConteudos(Set<PubConteudo> pubConteudos) {
        this.pubConteudos = pubConteudos;
    }




}


