package br.com.jvmsoftware.entities;
// Generated 17/Abr/2016 23:30:22 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubTipoVeiculo generated by hbm2java
 */
public class PubTipoVeiculo  implements java.io.Serializable {


     private Integer idTipoVeiculo;
     private String tipoVeiculo;
     private Set<CadVeiculos> cadVeiculoses = new HashSet<CadVeiculos>(0);
     private Set<PubMarcaVeiculo> pubMarcaVeiculos = new HashSet<PubMarcaVeiculo>(0);

    public PubTipoVeiculo() {
    }

	
    public PubTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
    public PubTipoVeiculo(String tipoVeiculo, Set<CadVeiculos> cadVeiculoses, Set<PubMarcaVeiculo> pubMarcaVeiculos) {
       this.tipoVeiculo = tipoVeiculo;
       this.cadVeiculoses = cadVeiculoses;
       this.pubMarcaVeiculos = pubMarcaVeiculos;
    }
   
    public Integer getIdTipoVeiculo() {
        return this.idTipoVeiculo;
    }
    
    public void setIdTipoVeiculo(Integer idTipoVeiculo) {
        this.idTipoVeiculo = idTipoVeiculo;
    }
    public String getTipoVeiculo() {
        return this.tipoVeiculo;
    }
    
    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
    public Set<CadVeiculos> getCadVeiculoses() {
        return this.cadVeiculoses;
    }
    
    public void setCadVeiculoses(Set<CadVeiculos> cadVeiculoses) {
        this.cadVeiculoses = cadVeiculoses;
    }
    public Set<PubMarcaVeiculo> getPubMarcaVeiculos() {
        return this.pubMarcaVeiculos;
    }
    
    public void setPubMarcaVeiculos(Set<PubMarcaVeiculo> pubMarcaVeiculos) {
        this.pubMarcaVeiculos = pubMarcaVeiculos;
    }




}


