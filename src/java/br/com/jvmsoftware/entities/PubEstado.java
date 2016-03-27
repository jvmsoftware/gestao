package br.com.jvmsoftware.entities;
// Generated 27/Mar/2016 8:26:38 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubEstado generated by hbm2java
 */
public class PubEstado  implements java.io.Serializable {


     private int idEstado;
     private String estado;
     private String uf;
     private Set<CadEnderecos> cadEnderecoses = new HashSet<CadEnderecos>(0);
     private Set<PubUsuario> pubUsuarios = new HashSet<PubUsuario>(0);
     private Set<PubEmpresa> pubEmpresas = new HashSet<PubEmpresa>(0);
     private Set<PubMunicipio> pubMunicipios = new HashSet<PubMunicipio>(0);

    public PubEstado() {
    }

	
    public PubEstado(int idEstado, String estado, String uf) {
        this.idEstado = idEstado;
        this.estado = estado;
        this.uf = uf;
    }
    public PubEstado(int idEstado, String estado, String uf, Set<CadEnderecos> cadEnderecoses, Set<PubUsuario> pubUsuarios, Set<PubEmpresa> pubEmpresas, Set<PubMunicipio> pubMunicipios) {
       this.idEstado = idEstado;
       this.estado = estado;
       this.uf = uf;
       this.cadEnderecoses = cadEnderecoses;
       this.pubUsuarios = pubUsuarios;
       this.pubEmpresas = pubEmpresas;
       this.pubMunicipios = pubMunicipios;
    }
   
    public int getIdEstado() {
        return this.idEstado;
    }
    
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getUf() {
        return this.uf;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }
    public Set<CadEnderecos> getCadEnderecoses() {
        return this.cadEnderecoses;
    }
    
    public void setCadEnderecoses(Set<CadEnderecos> cadEnderecoses) {
        this.cadEnderecoses = cadEnderecoses;
    }
    public Set<PubUsuario> getPubUsuarios() {
        return this.pubUsuarios;
    }
    
    public void setPubUsuarios(Set<PubUsuario> pubUsuarios) {
        this.pubUsuarios = pubUsuarios;
    }
    public Set<PubEmpresa> getPubEmpresas() {
        return this.pubEmpresas;
    }
    
    public void setPubEmpresas(Set<PubEmpresa> pubEmpresas) {
        this.pubEmpresas = pubEmpresas;
    }
    public Set<PubMunicipio> getPubMunicipios() {
        return this.pubMunicipios;
    }
    
    public void setPubMunicipios(Set<PubMunicipio> pubMunicipios) {
        this.pubMunicipios = pubMunicipios;
    }




}


