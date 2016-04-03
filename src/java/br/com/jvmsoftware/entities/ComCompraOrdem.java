package br.com.jvmsoftware.entities;
// Generated 3/Abr/2016 2:22:35 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ComCompraOrdem generated by hbm2java
 */
public class ComCompraOrdem  implements java.io.Serializable {


     private int id;
     private PubEmpresa pubEmpresa;
     private PubUsuario pubUsuario;
     private WflWorkflow wflWorkflow;
     private Date dataHoraInclusao;
     private Set<ComRelCompraOrdemProduto> comRelCompraOrdemProdutos = new HashSet<ComRelCompraOrdemProduto>(0);
     private Set<ComRelCompraOrdemServico> comRelCompraOrdemServicos = new HashSet<ComRelCompraOrdemServico>(0);

    public ComCompraOrdem() {
    }

	
    public ComCompraOrdem(int id, PubEmpresa pubEmpresa, PubUsuario pubUsuario, WflWorkflow wflWorkflow, Date dataHoraInclusao) {
        this.id = id;
        this.pubEmpresa = pubEmpresa;
        this.pubUsuario = pubUsuario;
        this.wflWorkflow = wflWorkflow;
        this.dataHoraInclusao = dataHoraInclusao;
    }
    public ComCompraOrdem(int id, PubEmpresa pubEmpresa, PubUsuario pubUsuario, WflWorkflow wflWorkflow, Date dataHoraInclusao, Set<ComRelCompraOrdemProduto> comRelCompraOrdemProdutos, Set<ComRelCompraOrdemServico> comRelCompraOrdemServicos) {
       this.id = id;
       this.pubEmpresa = pubEmpresa;
       this.pubUsuario = pubUsuario;
       this.wflWorkflow = wflWorkflow;
       this.dataHoraInclusao = dataHoraInclusao;
       this.comRelCompraOrdemProdutos = comRelCompraOrdemProdutos;
       this.comRelCompraOrdemServicos = comRelCompraOrdemServicos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    public Date getDataHoraInclusao() {
        return this.dataHoraInclusao;
    }
    
    public void setDataHoraInclusao(Date dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }
    public Set<ComRelCompraOrdemProduto> getComRelCompraOrdemProdutos() {
        return this.comRelCompraOrdemProdutos;
    }
    
    public void setComRelCompraOrdemProdutos(Set<ComRelCompraOrdemProduto> comRelCompraOrdemProdutos) {
        this.comRelCompraOrdemProdutos = comRelCompraOrdemProdutos;
    }
    public Set<ComRelCompraOrdemServico> getComRelCompraOrdemServicos() {
        return this.comRelCompraOrdemServicos;
    }
    
    public void setComRelCompraOrdemServicos(Set<ComRelCompraOrdemServico> comRelCompraOrdemServicos) {
        this.comRelCompraOrdemServicos = comRelCompraOrdemServicos;
    }




}

