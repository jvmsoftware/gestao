package br.com.jvmsoftware.entities;
// Generated 1/Set/2015 18:32:02 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SupProjeto generated by hbm2java
 */
public class SupProjeto  implements java.io.Serializable {


     private Integer idProjeto;
     private PubEmpresa pubEmpresa;
     private PubUsuario pubUsuario;
     private Date dataHoraCriacao;
     private String titulo;
     private String descricao;
     private String detalhamento;
     private Set<SupSolicitacao> supSolicitacaos = new HashSet<SupSolicitacao>(0);

    public SupProjeto() {
    }

	
    public SupProjeto(PubEmpresa pubEmpresa, PubUsuario pubUsuario, Date dataHoraCriacao, String titulo, String descricao, String detalhamento) {
        this.pubEmpresa = pubEmpresa;
        this.pubUsuario = pubUsuario;
        this.dataHoraCriacao = dataHoraCriacao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.detalhamento = detalhamento;
    }
    public SupProjeto(PubEmpresa pubEmpresa, PubUsuario pubUsuario, Date dataHoraCriacao, String titulo, String descricao, String detalhamento, Set<SupSolicitacao> supSolicitacaos) {
       this.pubEmpresa = pubEmpresa;
       this.pubUsuario = pubUsuario;
       this.dataHoraCriacao = dataHoraCriacao;
       this.titulo = titulo;
       this.descricao = descricao;
       this.detalhamento = detalhamento;
       this.supSolicitacaos = supSolicitacaos;
    }
   
    public Integer getIdProjeto() {
        return this.idProjeto;
    }
    
    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }
    public PubUsuario getPubUsuario() {
        return this.pubUsuario;
    }
    
    public void setPubUsuario(PubUsuario pubUsuario) {
        this.pubUsuario = pubUsuario;
    }
    public Date getDataHoraCriacao() {
        return this.dataHoraCriacao;
    }
    
    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDetalhamento() {
        return this.detalhamento;
    }
    
    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }
    public Set<SupSolicitacao> getSupSolicitacaos() {
        return this.supSolicitacaos;
    }
    
    public void setSupSolicitacaos(Set<SupSolicitacao> supSolicitacaos) {
        this.supSolicitacaos = supSolicitacaos;
    }




}


