package br.com.jvmsoftware.entities;
// Generated 31/Mar/2016 22:28:08 by Hibernate Tools 4.3.1



/**
 * AcsEmpresaSistema generated by hbm2java
 */
public class AcsEmpresaSistema  implements java.io.Serializable {


     private Integer idEmpresaSistema;
     private PubEmpresa pubEmpresa;
     private PubSistema pubSistema;
     private boolean ativo;

    public AcsEmpresaSistema() {
    }

    public AcsEmpresaSistema(PubEmpresa pubEmpresa, PubSistema pubSistema, boolean ativo) {
       this.pubEmpresa = pubEmpresa;
       this.pubSistema = pubSistema;
       this.ativo = ativo;
    }
   
    public Integer getIdEmpresaSistema() {
        return this.idEmpresaSistema;
    }
    
    public void setIdEmpresaSistema(Integer idEmpresaSistema) {
        this.idEmpresaSistema = idEmpresaSistema;
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
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }




}


