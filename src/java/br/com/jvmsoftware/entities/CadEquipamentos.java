package br.com.jvmsoftware.entities;
// Generated 3/Abr/2016 2:22:35 by Hibernate Tools 4.3.1



/**
 * CadEquipamentos generated by hbm2java
 */
public class CadEquipamentos  implements java.io.Serializable {


     private Integer idEquipamento;
     private CadPessoa cadPessoa;
     private PubEmpresa pubEmpresa;
     private PubTipoEquipamento pubTipoEquipamento;
     private String equipamento;
     private String observacao;

    public CadEquipamentos() {
    }

	
    public CadEquipamentos(CadPessoa cadPessoa, PubEmpresa pubEmpresa, PubTipoEquipamento pubTipoEquipamento, String equipamento) {
        this.cadPessoa = cadPessoa;
        this.pubEmpresa = pubEmpresa;
        this.pubTipoEquipamento = pubTipoEquipamento;
        this.equipamento = equipamento;
    }
    public CadEquipamentos(CadPessoa cadPessoa, PubEmpresa pubEmpresa, PubTipoEquipamento pubTipoEquipamento, String equipamento, String observacao) {
       this.cadPessoa = cadPessoa;
       this.pubEmpresa = pubEmpresa;
       this.pubTipoEquipamento = pubTipoEquipamento;
       this.equipamento = equipamento;
       this.observacao = observacao;
    }
   
    public Integer getIdEquipamento() {
        return this.idEquipamento;
    }
    
    public void setIdEquipamento(Integer idEquipamento) {
        this.idEquipamento = idEquipamento;
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
    public PubTipoEquipamento getPubTipoEquipamento() {
        return this.pubTipoEquipamento;
    }
    
    public void setPubTipoEquipamento(PubTipoEquipamento pubTipoEquipamento) {
        this.pubTipoEquipamento = pubTipoEquipamento;
    }
    public String getEquipamento() {
        return this.equipamento;
    }
    
    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }




}

