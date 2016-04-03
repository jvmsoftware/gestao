package br.com.jvmsoftware.entities;
// Generated 3/Abr/2016 2:22:35 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ComVendaPedido generated by hbm2java
 */
public class ComVendaPedido  implements java.io.Serializable {


     private int idPedido;
     private int empresa;
     private int usuario;
     private Date dataHora;
     private int workflow;
     private Set<ComRelVendaProduto> comRelVendaProdutos = new HashSet<ComRelVendaProduto>(0);

    public ComVendaPedido() {
    }

	
    public ComVendaPedido(int idPedido, int empresa, int usuario, Date dataHora, int workflow) {
        this.idPedido = idPedido;
        this.empresa = empresa;
        this.usuario = usuario;
        this.dataHora = dataHora;
        this.workflow = workflow;
    }
    public ComVendaPedido(int idPedido, int empresa, int usuario, Date dataHora, int workflow, Set<ComRelVendaProduto> comRelVendaProdutos) {
       this.idPedido = idPedido;
       this.empresa = empresa;
       this.usuario = usuario;
       this.dataHora = dataHora;
       this.workflow = workflow;
       this.comRelVendaProdutos = comRelVendaProdutos;
    }
   
    public int getIdPedido() {
        return this.idPedido;
    }
    
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public int getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }
    public int getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    public Date getDataHora() {
        return this.dataHora;
    }
    
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    public int getWorkflow() {
        return this.workflow;
    }
    
    public void setWorkflow(int workflow) {
        this.workflow = workflow;
    }
    public Set<ComRelVendaProduto> getComRelVendaProdutos() {
        return this.comRelVendaProdutos;
    }
    
    public void setComRelVendaProdutos(Set<ComRelVendaProduto> comRelVendaProdutos) {
        this.comRelVendaProdutos = comRelVendaProdutos;
    }




}

