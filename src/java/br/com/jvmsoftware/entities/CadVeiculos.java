package br.com.jvmsoftware.entities;
// Generated 3/Abr/2016 2:22:35 by Hibernate Tools 4.3.1



/**
 * CadVeiculos generated by hbm2java
 */
public class CadVeiculos  implements java.io.Serializable {


     private Integer idVeiculo;
     private CadPessoa cadPessoa;
     private PubEmpresa pubEmpresa;
     private PubMarcaVeiculo pubMarcaVeiculo;
     private PubModeloVeiculo pubModeloVeiculo;
     private PubTipoVeiculo pubTipoVeiculo;
     private String placa;
     private String chassis;
     private String observacao;

    public CadVeiculos() {
    }

	
    public CadVeiculos(CadPessoa cadPessoa, PubEmpresa pubEmpresa, PubTipoVeiculo pubTipoVeiculo) {
        this.cadPessoa = cadPessoa;
        this.pubEmpresa = pubEmpresa;
        this.pubTipoVeiculo = pubTipoVeiculo;
    }
    public CadVeiculos(CadPessoa cadPessoa, PubEmpresa pubEmpresa, PubMarcaVeiculo pubMarcaVeiculo, PubModeloVeiculo pubModeloVeiculo, PubTipoVeiculo pubTipoVeiculo, String placa, String chassis, String observacao) {
       this.cadPessoa = cadPessoa;
       this.pubEmpresa = pubEmpresa;
       this.pubMarcaVeiculo = pubMarcaVeiculo;
       this.pubModeloVeiculo = pubModeloVeiculo;
       this.pubTipoVeiculo = pubTipoVeiculo;
       this.placa = placa;
       this.chassis = chassis;
       this.observacao = observacao;
    }
   
    public Integer getIdVeiculo() {
        return this.idVeiculo;
    }
    
    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    public CadPessoa getCadPessoa() {
        return this.cadPessoa;
    }
    
    public void setCadPessoa(CadPessoa cadPessoa) {
        this.cadPessoa = cadPessoa;
    }
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }
    public PubMarcaVeiculo getPubMarcaVeiculo() {
        return this.pubMarcaVeiculo;
    }
    
    public void setPubMarcaVeiculo(PubMarcaVeiculo pubMarcaVeiculo) {
        this.pubMarcaVeiculo = pubMarcaVeiculo;
    }
    public PubModeloVeiculo getPubModeloVeiculo() {
        return this.pubModeloVeiculo;
    }
    
    public void setPubModeloVeiculo(PubModeloVeiculo pubModeloVeiculo) {
        this.pubModeloVeiculo = pubModeloVeiculo;
    }
    public PubTipoVeiculo getPubTipoVeiculo() {
        return this.pubTipoVeiculo;
    }
    
    public void setPubTipoVeiculo(PubTipoVeiculo pubTipoVeiculo) {
        this.pubTipoVeiculo = pubTipoVeiculo;
    }
    public String getPlaca() {
        return this.placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getChassis() {
        return this.chassis;
    }
    
    public void setChassis(String chassis) {
        this.chassis = chassis;
    }
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }




}


