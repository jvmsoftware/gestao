package br.com.jvmsoftware.entities;
// Generated 31/Mar/2016 22:28:08 by Hibernate Tools 4.3.1



/**
 * AcsUsuarioSistema generated by hbm2java
 */
public class AcsUsuarioSistema  implements java.io.Serializable {


     private Integer idUsuarioSistema;
     private PubEmpresa pubEmpresa;
     private PubSistema pubSistema;
     private PubUsuario pubUsuario;
     private boolean ativo;

    public AcsUsuarioSistema() {
    }

    public AcsUsuarioSistema(PubEmpresa pubEmpresa, PubSistema pubSistema, PubUsuario pubUsuario, boolean ativo) {
       this.pubEmpresa = pubEmpresa;
       this.pubSistema = pubSistema;
       this.pubUsuario = pubUsuario;
       this.ativo = ativo;
    }
   
    public Integer getIdUsuarioSistema() {
        return this.idUsuarioSistema;
    }
    
    public void setIdUsuarioSistema(Integer idUsuarioSistema) {
        this.idUsuarioSistema = idUsuarioSistema;
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
    public PubUsuario getPubUsuario() {
        return this.pubUsuario;
    }
    
    public void setPubUsuario(PubUsuario pubUsuario) {
        this.pubUsuario = pubUsuario;
    }
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }




}


