package br.com.jvmsoftware.entities;
// Generated 4/Ago/2015 22:25:45 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubMunicipio generated by hbm2java
 */
public class PubMunicipio  implements java.io.Serializable {


     private int idMunicipio;
     private PubEstado pubEstado;
     private String municipio;
     private Set<PubEmpresa> pubEmpresas = new HashSet<PubEmpresa>(0);
     private Set<CadPessoaJuridica> cadPessoaJuridicas = new HashSet<CadPessoaJuridica>(0);
     private Set<PubUsuario> pubUsuarios = new HashSet<PubUsuario>(0);
     private Set<CadPessoaFisica> cadPessoaFisicas = new HashSet<CadPessoaFisica>(0);

    public PubMunicipio() {
    }

	
    public PubMunicipio(int idMunicipio, PubEstado pubEstado, String municipio) {
        this.idMunicipio = idMunicipio;
        this.pubEstado = pubEstado;
        this.municipio = municipio;
    }
    public PubMunicipio(int idMunicipio, PubEstado pubEstado, String municipio, Set<PubEmpresa> pubEmpresas, Set<CadPessoaJuridica> cadPessoaJuridicas, Set<PubUsuario> pubUsuarios, Set<CadPessoaFisica> cadPessoaFisicas) {
       this.idMunicipio = idMunicipio;
       this.pubEstado = pubEstado;
       this.municipio = municipio;
       this.pubEmpresas = pubEmpresas;
       this.cadPessoaJuridicas = cadPessoaJuridicas;
       this.pubUsuarios = pubUsuarios;
       this.cadPessoaFisicas = cadPessoaFisicas;
    }
   
    public int getIdMunicipio() {
        return this.idMunicipio;
    }
    
    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    public PubEstado getPubEstado() {
        return this.pubEstado;
    }
    
    public void setPubEstado(PubEstado pubEstado) {
        this.pubEstado = pubEstado;
    }
    public String getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public Set<PubEmpresa> getPubEmpresas() {
        return this.pubEmpresas;
    }
    
    public void setPubEmpresas(Set<PubEmpresa> pubEmpresas) {
        this.pubEmpresas = pubEmpresas;
    }
    public Set<CadPessoaJuridica> getCadPessoaJuridicas() {
        return this.cadPessoaJuridicas;
    }
    
    public void setCadPessoaJuridicas(Set<CadPessoaJuridica> cadPessoaJuridicas) {
        this.cadPessoaJuridicas = cadPessoaJuridicas;
    }
    public Set<PubUsuario> getPubUsuarios() {
        return this.pubUsuarios;
    }
    
    public void setPubUsuarios(Set<PubUsuario> pubUsuarios) {
        this.pubUsuarios = pubUsuarios;
    }
    public Set<CadPessoaFisica> getCadPessoaFisicas() {
        return this.cadPessoaFisicas;
    }
    
    public void setCadPessoaFisicas(Set<CadPessoaFisica> cadPessoaFisicas) {
        this.cadPessoaFisicas = cadPessoaFisicas;
    }




}

