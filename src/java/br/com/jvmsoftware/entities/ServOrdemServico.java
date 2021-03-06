package br.com.jvmsoftware.entities;
// Generated 17/Abr/2016 23:30:22 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ServOrdemServico generated by hbm2java
 */
public class ServOrdemServico  implements java.io.Serializable {


     private Integer idOrdem;
     private CadEquipamentos cadEquipamentos;
     private CadImoveis cadImoveis;
     private CadPessoa cadPessoa;
     private CadVeiculos cadVeiculos;
     private PubEmpresa pubEmpresa;
     private PubUsuario pubUsuario;
     private WflWorkflow wflWorkflow;
     private Date dataHora;
     private String observacao;
     private Set<ServItemOrdemServico> servItemOrdemServicos = new HashSet<ServItemOrdemServico>(0);

    public ServOrdemServico() {
    }

	
    public ServOrdemServico(PubEmpresa pubEmpresa, PubUsuario pubUsuario, WflWorkflow wflWorkflow, Date dataHora) {
        this.pubEmpresa = pubEmpresa;
        this.pubUsuario = pubUsuario;
        this.wflWorkflow = wflWorkflow;
        this.dataHora = dataHora;
    }
    public ServOrdemServico(CadEquipamentos cadEquipamentos, CadImoveis cadImoveis, CadPessoa cadPessoa, CadVeiculos cadVeiculos, PubEmpresa pubEmpresa, PubUsuario pubUsuario, WflWorkflow wflWorkflow, Date dataHora, String observacao, Set<ServItemOrdemServico> servItemOrdemServicos) {
       this.cadEquipamentos = cadEquipamentos;
       this.cadImoveis = cadImoveis;
       this.cadPessoa = cadPessoa;
       this.cadVeiculos = cadVeiculos;
       this.pubEmpresa = pubEmpresa;
       this.pubUsuario = pubUsuario;
       this.wflWorkflow = wflWorkflow;
       this.dataHora = dataHora;
       this.observacao = observacao;
       this.servItemOrdemServicos = servItemOrdemServicos;
    }
   
    public Integer getIdOrdem() {
        return this.idOrdem;
    }
    
    public void setIdOrdem(Integer idOrdem) {
        this.idOrdem = idOrdem;
    }
    public CadEquipamentos getCadEquipamentos() {
        return this.cadEquipamentos;
    }
    
    public void setCadEquipamentos(CadEquipamentos cadEquipamentos) {
        this.cadEquipamentos = cadEquipamentos;
    }
    public CadImoveis getCadImoveis() {
        return this.cadImoveis;
    }
    
    public void setCadImoveis(CadImoveis cadImoveis) {
        this.cadImoveis = cadImoveis;
    }
    public CadPessoa getCadPessoa() {
        return this.cadPessoa;
    }
    
    public void setCadPessoa(CadPessoa cadPessoa) {
        this.cadPessoa = cadPessoa;
    }
    public CadVeiculos getCadVeiculos() {
        return this.cadVeiculos;
    }
    
    public void setCadVeiculos(CadVeiculos cadVeiculos) {
        this.cadVeiculos = cadVeiculos;
    }
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }
    public PubUsuario getPubUsuario() {
        return this.pubUsuario;
    }
    
    public void setPubUsuario(PubUsuario pubUsuario) {
        this.pubUsuario = pubUsuario;
    }
    public WflWorkflow getWflWorkflow() {
        return this.wflWorkflow;
    }
    
    public void setWflWorkflow(WflWorkflow wflWorkflow) {
        this.wflWorkflow = wflWorkflow;
    }
    public Date getDataHora() {
        return this.dataHora;
    }
    
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Set<ServItemOrdemServico> getServItemOrdemServicos() {
        return this.servItemOrdemServicos;
    }
    
    public void setServItemOrdemServicos(Set<ServItemOrdemServico> servItemOrdemServicos) {
        this.servItemOrdemServicos = servItemOrdemServicos;
    }




}


