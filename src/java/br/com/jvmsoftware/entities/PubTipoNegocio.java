package br.com.jvmsoftware.entities;
// Generated 1/Set/2015 18:32:02 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubTipoNegocio generated by hbm2java
 */
public class PubTipoNegocio  implements java.io.Serializable {


     private Integer idTipoNegocio;
     private PubTipoEmpresa pubTipoEmpresa;
     private String tipoNegocio;
     private Set<PubConfigEmpresa> pubConfigEmpresas = new HashSet<PubConfigEmpresa>(0);

    public PubTipoNegocio() {
    }

	
    public PubTipoNegocio(PubTipoEmpresa pubTipoEmpresa, String tipoNegocio) {
        this.pubTipoEmpresa = pubTipoEmpresa;
        this.tipoNegocio = tipoNegocio;
    }
    public PubTipoNegocio(PubTipoEmpresa pubTipoEmpresa, String tipoNegocio, Set<PubConfigEmpresa> pubConfigEmpresas) {
       this.pubTipoEmpresa = pubTipoEmpresa;
       this.tipoNegocio = tipoNegocio;
       this.pubConfigEmpresas = pubConfigEmpresas;
    }
   
    public Integer getIdTipoNegocio() {
        return this.idTipoNegocio;
    }
    
    public void setIdTipoNegocio(Integer idTipoNegocio) {
        this.idTipoNegocio = idTipoNegocio;
    }
    public PubTipoEmpresa getPubTipoEmpresa() {
        return this.pubTipoEmpresa;
    }
    
    public void setPubTipoEmpresa(PubTipoEmpresa pubTipoEmpresa) {
        this.pubTipoEmpresa = pubTipoEmpresa;
    }
    public String getTipoNegocio() {
        return this.tipoNegocio;
    }
    
    public void setTipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }
    public Set<PubConfigEmpresa> getPubConfigEmpresas() {
        return this.pubConfigEmpresas;
    }
    
    public void setPubConfigEmpresas(Set<PubConfigEmpresa> pubConfigEmpresas) {
        this.pubConfigEmpresas = pubConfigEmpresas;
    }




}


