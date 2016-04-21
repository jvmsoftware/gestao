package br.com.jvmsoftware.entities;
// Generated 17/Abr/2016 23:30:22 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubMarcaVeiculo generated by hbm2java
 */
public class PubMarcaVeiculo  implements java.io.Serializable {


     private Integer idMarcaVeiculo;
     private PubTipoVeiculo pubTipoVeiculo;
     private String marcaVeiculo;
     private Set<CadVeiculos> cadVeiculoses = new HashSet<CadVeiculos>(0);
     private Set<PubModeloVeiculo> pubModeloVeiculos = new HashSet<PubModeloVeiculo>(0);

    public PubMarcaVeiculo() {
    }

	
    public PubMarcaVeiculo(PubTipoVeiculo pubTipoVeiculo, String marcaVeiculo) {
        this.pubTipoVeiculo = pubTipoVeiculo;
        this.marcaVeiculo = marcaVeiculo;
    }
    public PubMarcaVeiculo(PubTipoVeiculo pubTipoVeiculo, String marcaVeiculo, Set<CadVeiculos> cadVeiculoses, Set<PubModeloVeiculo> pubModeloVeiculos) {
       this.pubTipoVeiculo = pubTipoVeiculo;
       this.marcaVeiculo = marcaVeiculo;
       this.cadVeiculoses = cadVeiculoses;
       this.pubModeloVeiculos = pubModeloVeiculos;
    }
   
    public Integer getIdMarcaVeiculo() {
        return this.idMarcaVeiculo;
    }
    
    public void setIdMarcaVeiculo(Integer idMarcaVeiculo) {
        this.idMarcaVeiculo = idMarcaVeiculo;
    }
    public PubTipoVeiculo getPubTipoVeiculo() {
        return this.pubTipoVeiculo;
    }
    
    public void setPubTipoVeiculo(PubTipoVeiculo pubTipoVeiculo) {
        this.pubTipoVeiculo = pubTipoVeiculo;
    }
    public String getMarcaVeiculo() {
        return this.marcaVeiculo;
    }
    
    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }
    public Set<CadVeiculos> getCadVeiculoses() {
        return this.cadVeiculoses;
    }
    
    public void setCadVeiculoses(Set<CadVeiculos> cadVeiculoses) {
        this.cadVeiculoses = cadVeiculoses;
    }
    public Set<PubModeloVeiculo> getPubModeloVeiculos() {
        return this.pubModeloVeiculos;
    }
    
    public void setPubModeloVeiculos(Set<PubModeloVeiculo> pubModeloVeiculos) {
        this.pubModeloVeiculos = pubModeloVeiculos;
    }




}


