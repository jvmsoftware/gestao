package br.com.jvmsoftware.entities;
// Generated 3/Abr/2016 2:22:35 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * WflSituacao generated by hbm2java
 */
public class WflSituacao  implements java.io.Serializable {


     private Integer idSituacao;
     private PubEmpresa pubEmpresa;
     private PubSistema pubSistema;
     private String codSituacao;
     private String descricaoSituacao;
     private int prioridade;
     private Set<SupSolicitacao> supSolicitacaos = new HashSet<SupSolicitacao>(0);
     private Set<WflWorkflowEvento> wflWorkflowEventosForSituacaoAnterior = new HashSet<WflWorkflowEvento>(0);
     private Set<WflWorkflowEvento> wflWorkflowEventosForSituacaoAtual = new HashSet<WflWorkflowEvento>(0);
     private Set<WflWorkflow> wflWorkflows = new HashSet<WflWorkflow>(0);

    public WflSituacao() {
    }

	
    public WflSituacao(PubSistema pubSistema, String codSituacao, String descricaoSituacao, int prioridade) {
        this.pubSistema = pubSistema;
        this.codSituacao = codSituacao;
        this.descricaoSituacao = descricaoSituacao;
        this.prioridade = prioridade;
    }
    public WflSituacao(PubEmpresa pubEmpresa, PubSistema pubSistema, String codSituacao, String descricaoSituacao, int prioridade, Set<SupSolicitacao> supSolicitacaos, Set<WflWorkflowEvento> wflWorkflowEventosForSituacaoAnterior, Set<WflWorkflowEvento> wflWorkflowEventosForSituacaoAtual, Set<WflWorkflow> wflWorkflows) {
       this.pubEmpresa = pubEmpresa;
       this.pubSistema = pubSistema;
       this.codSituacao = codSituacao;
       this.descricaoSituacao = descricaoSituacao;
       this.prioridade = prioridade;
       this.supSolicitacaos = supSolicitacaos;
       this.wflWorkflowEventosForSituacaoAnterior = wflWorkflowEventosForSituacaoAnterior;
       this.wflWorkflowEventosForSituacaoAtual = wflWorkflowEventosForSituacaoAtual;
       this.wflWorkflows = wflWorkflows;
    }
   
    public Integer getIdSituacao() {
        return this.idSituacao;
    }
    
    public void setIdSituacao(Integer idSituacao) {
        this.idSituacao = idSituacao;
    }
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }
    public PubSistema getPubSistema() {
        return this.pubSistema;
    }
    
    public void setPubSistema(PubSistema pubSistema) {
        this.pubSistema = pubSistema;
    }
    public String getCodSituacao() {
        return this.codSituacao;
    }
    
    public void setCodSituacao(String codSituacao) {
        this.codSituacao = codSituacao;
    }
    public String getDescricaoSituacao() {
        return this.descricaoSituacao;
    }
    
    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }
    public int getPrioridade() {
        return this.prioridade;
    }
    
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public Set<SupSolicitacao> getSupSolicitacaos() {
        return this.supSolicitacaos;
    }
    
    public void setSupSolicitacaos(Set<SupSolicitacao> supSolicitacaos) {
        this.supSolicitacaos = supSolicitacaos;
    }
    public Set<WflWorkflowEvento> getWflWorkflowEventosForSituacaoAnterior() {
        return this.wflWorkflowEventosForSituacaoAnterior;
    }
    
    public void setWflWorkflowEventosForSituacaoAnterior(Set<WflWorkflowEvento> wflWorkflowEventosForSituacaoAnterior) {
        this.wflWorkflowEventosForSituacaoAnterior = wflWorkflowEventosForSituacaoAnterior;
    }
    public Set<WflWorkflowEvento> getWflWorkflowEventosForSituacaoAtual() {
        return this.wflWorkflowEventosForSituacaoAtual;
    }
    
    public void setWflWorkflowEventosForSituacaoAtual(Set<WflWorkflowEvento> wflWorkflowEventosForSituacaoAtual) {
        this.wflWorkflowEventosForSituacaoAtual = wflWorkflowEventosForSituacaoAtual;
    }
    public Set<WflWorkflow> getWflWorkflows() {
        return this.wflWorkflows;
    }
    
    public void setWflWorkflows(Set<WflWorkflow> wflWorkflows) {
        this.wflWorkflows = wflWorkflows;
    }




}


