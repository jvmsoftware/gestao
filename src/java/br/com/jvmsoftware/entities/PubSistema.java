package br.com.jvmsoftware.entities;
// Generated 27/Mar/2016 8:26:38 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubSistema generated by hbm2java
 */
public class PubSistema  implements java.io.Serializable {


     private Integer idSistema;
     private String nomeSistema;
     private String descricaoSistema;
     private String imagemSistema;
     private boolean ativo;
     private Set<PubMenu> pubMenus = new HashSet<PubMenu>(0);
     private Set<PubFuncionalidade> pubFuncionalidades = new HashSet<PubFuncionalidade>(0);
     private Set<SupSolicitacao> supSolicitacaos = new HashSet<SupSolicitacao>(0);
     private Set<WflWorkflow> wflWorkflows = new HashSet<WflWorkflow>(0);
     private Set<WflSituacao> wflSituacaos = new HashSet<WflSituacao>(0);
     private Set<CtrLog> ctrLogs = new HashSet<CtrLog>(0);
     private Set<AcsEmpresaSistema> acsEmpresaSistemas = new HashSet<AcsEmpresaSistema>(0);
     private Set<AcsUsuarioSistema> acsUsuarioSistemas = new HashSet<AcsUsuarioSistema>(0);

    public PubSistema() {
    }

	
    public PubSistema(String nomeSistema, String descricaoSistema, boolean ativo) {
        this.nomeSistema = nomeSistema;
        this.descricaoSistema = descricaoSistema;
        this.ativo = ativo;
    }
    public PubSistema(String nomeSistema, String descricaoSistema, String imagemSistema, boolean ativo, Set<PubMenu> pubMenus, Set<PubFuncionalidade> pubFuncionalidades, Set<SupSolicitacao> supSolicitacaos, Set<WflWorkflow> wflWorkflows, Set<WflSituacao> wflSituacaos, Set<CtrLog> ctrLogs, Set<AcsEmpresaSistema> acsEmpresaSistemas, Set<AcsUsuarioSistema> acsUsuarioSistemas) {
       this.nomeSistema = nomeSistema;
       this.descricaoSistema = descricaoSistema;
       this.imagemSistema = imagemSistema;
       this.ativo = ativo;
       this.pubMenus = pubMenus;
       this.pubFuncionalidades = pubFuncionalidades;
       this.supSolicitacaos = supSolicitacaos;
       this.wflWorkflows = wflWorkflows;
       this.wflSituacaos = wflSituacaos;
       this.ctrLogs = ctrLogs;
       this.acsEmpresaSistemas = acsEmpresaSistemas;
       this.acsUsuarioSistemas = acsUsuarioSistemas;
    }
   
    public Integer getIdSistema() {
        return this.idSistema;
    }
    
    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }
    public String getNomeSistema() {
        return this.nomeSistema;
    }
    
    public void setNomeSistema(String nomeSistema) {
        this.nomeSistema = nomeSistema;
    }
    public String getDescricaoSistema() {
        return this.descricaoSistema;
    }
    
    public void setDescricaoSistema(String descricaoSistema) {
        this.descricaoSistema = descricaoSistema;
    }
    public String getImagemSistema() {
        return this.imagemSistema;
    }
    
    public void setImagemSistema(String imagemSistema) {
        this.imagemSistema = imagemSistema;
    }
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public Set<PubMenu> getPubMenus() {
        return this.pubMenus;
    }
    
    public void setPubMenus(Set<PubMenu> pubMenus) {
        this.pubMenus = pubMenus;
    }
    public Set<PubFuncionalidade> getPubFuncionalidades() {
        return this.pubFuncionalidades;
    }
    
    public void setPubFuncionalidades(Set<PubFuncionalidade> pubFuncionalidades) {
        this.pubFuncionalidades = pubFuncionalidades;
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
    public Set<WflSituacao> getWflSituacaos() {
        return this.wflSituacaos;
    }
    
    public void setWflSituacaos(Set<WflSituacao> wflSituacaos) {
        this.wflSituacaos = wflSituacaos;
    }
    public Set<CtrLog> getCtrLogs() {
        return this.ctrLogs;
    }
    
    public void setCtrLogs(Set<CtrLog> ctrLogs) {
        this.ctrLogs = ctrLogs;
    }
    public Set<AcsEmpresaSistema> getAcsEmpresaSistemas() {
        return this.acsEmpresaSistemas;
    }
    
    public void setAcsEmpresaSistemas(Set<AcsEmpresaSistema> acsEmpresaSistemas) {
        this.acsEmpresaSistemas = acsEmpresaSistemas;
    }
    public Set<AcsUsuarioSistema> getAcsUsuarioSistemas() {
        return this.acsUsuarioSistemas;
    }
    
    public void setAcsUsuarioSistemas(Set<AcsUsuarioSistema> acsUsuarioSistemas) {
        this.acsUsuarioSistemas = acsUsuarioSistemas;
    }




}


