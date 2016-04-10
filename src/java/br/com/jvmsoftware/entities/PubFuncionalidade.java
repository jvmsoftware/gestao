package br.com.jvmsoftware.entities;
// Generated 9/Abr/2016 18:34:16 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubFuncionalidade generated by hbm2java
 */
public class PubFuncionalidade  implements java.io.Serializable {


     private Integer idFuncionalidade;
     private PubSistema pubSistema;
     private String codFuncionalidade;
     private String descricaoFuncionalidade;
     private Set<AcsEmpresaFuncionalidade> acsEmpresaFuncionalidades = new HashSet<AcsEmpresaFuncionalidade>(0);
     private Set<SupSolicitacao> supSolicitacaos = new HashSet<SupSolicitacao>(0);
     private Set<AcsUsuarioFuncionalidade> acsUsuarioFuncionalidades = new HashSet<AcsUsuarioFuncionalidade>(0);
     private Set<WflWorkflow> wflWorkflows = new HashSet<WflWorkflow>(0);

    public PubFuncionalidade() {
    }

	
    public PubFuncionalidade(PubSistema pubSistema, String codFuncionalidade, String descricaoFuncionalidade) {
        this.pubSistema = pubSistema;
        this.codFuncionalidade = codFuncionalidade;
        this.descricaoFuncionalidade = descricaoFuncionalidade;
    }
    public PubFuncionalidade(PubSistema pubSistema, String codFuncionalidade, String descricaoFuncionalidade, Set<AcsEmpresaFuncionalidade> acsEmpresaFuncionalidades, Set<SupSolicitacao> supSolicitacaos, Set<AcsUsuarioFuncionalidade> acsUsuarioFuncionalidades, Set<WflWorkflow> wflWorkflows) {
       this.pubSistema = pubSistema;
       this.codFuncionalidade = codFuncionalidade;
       this.descricaoFuncionalidade = descricaoFuncionalidade;
       this.acsEmpresaFuncionalidades = acsEmpresaFuncionalidades;
       this.supSolicitacaos = supSolicitacaos;
       this.acsUsuarioFuncionalidades = acsUsuarioFuncionalidades;
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
    public String getCodFuncionalidade() {
        return this.codFuncionalidade;
    }
    
    public void setCodFuncionalidade(String codFuncionalidade) {
        this.codFuncionalidade = codFuncionalidade;
    }
    public String getDescricaoFuncionalidade() {
        return this.descricaoFuncionalidade;
    }
    
    public void setDescricaoFuncionalidade(String descricaoFuncionalidade) {
        this.descricaoFuncionalidade = descricaoFuncionalidade;
    }
    public Set<AcsEmpresaFuncionalidade> getAcsEmpresaFuncionalidades() {
        return this.acsEmpresaFuncionalidades;
    }
    
    public void setAcsEmpresaFuncionalidades(Set<AcsEmpresaFuncionalidade> acsEmpresaFuncionalidades) {
        this.acsEmpresaFuncionalidades = acsEmpresaFuncionalidades;
    }
    public Set<SupSolicitacao> getSupSolicitacaos() {
        return this.supSolicitacaos;
    }
    
    public void setSupSolicitacaos(Set<SupSolicitacao> supSolicitacaos) {
        this.supSolicitacaos = supSolicitacaos;
    }
    public Set<AcsUsuarioFuncionalidade> getAcsUsuarioFuncionalidades() {
        return this.acsUsuarioFuncionalidades;
    }
    
    public void setAcsUsuarioFuncionalidades(Set<AcsUsuarioFuncionalidade> acsUsuarioFuncionalidades) {
        this.acsUsuarioFuncionalidades = acsUsuarioFuncionalidades;
    }
    public Set<WflWorkflow> getWflWorkflows() {
        return this.wflWorkflows;
    }
    
    public void setWflWorkflows(Set<WflWorkflow> wflWorkflows) {
        this.wflWorkflows = wflWorkflows;
    }




}


