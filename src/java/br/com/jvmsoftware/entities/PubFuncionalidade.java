package br.com.jvmsoftware.entities;
// Generated 3/Abr/2016 2:22:35 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubFuncionalidade generated by hbm2java
 */
public class PubFuncionalidade  implements java.io.Serializable {


     private Integer idFuncionalidade;
     private PubSistema pubSistema;
     private String descricaoFuncionalidade;
     private Set<SupSolicitacao> supSolicitacaos = new HashSet<SupSolicitacao>(0);
     private Set<WflWorkflow> wflWorkflows = new HashSet<WflWorkflow>(0);

    public PubFuncionalidade() {
    }

	
    public PubFuncionalidade(PubSistema pubSistema, String descricaoFuncionalidade) {
        this.pubSistema = pubSistema;
        this.descricaoFuncionalidade = descricaoFuncionalidade;
    }
    public PubFuncionalidade(PubSistema pubSistema, String descricaoFuncionalidade, Set<SupSolicitacao> supSolicitacaos, Set<WflWorkflow> wflWorkflows) {
       this.pubSistema = pubSistema;
       this.descricaoFuncionalidade = descricaoFuncionalidade;
       this.supSolicitacaos = supSolicitacaos;
       this.wflWorkflows = wflWorkflows;
    }
   
    public Integer getIdFuncionalidade() {
        return this.idFuncionalidade;
    }
    
    public void setIdFuncionalidade(Integer idFuncionalidade) {
        this.idFuncionalidade = idFuncionalidade;
    }
    public PubSistema getPubSistema() {
        return this.pubSistema;
    }
    
    public void setPubSistema(PubSistema pubSistema) {
        this.pubSistema = pubSistema;
    }
    public String getDescricaoFuncionalidade() {
        return this.descricaoFuncionalidade;
    }
    
    public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
        this.descricaoFuncionalidade = descricaoFuncionalidade;
    }
    public Set<SupSolicitacao> getSupSolicitacaos() {
        return this.supSolicitacaos;
    }
    
    public void setSupSolicitacaos(Set<SupSolicitacao> supSolicitacaos) {
        this.supSolicitacaos = supSolicitacaos;
    }
    public Set<WflWorkflow> getWflWorkflows() {
        return this.wflWorkflows;
    }
    
    public void setWflWorkflows(Set<WflWorkflow> wflWorkflows) {
        this.wflWorkflows = wflWorkflows;
    }




}


