package br.com.jvmsoftware.entities;
// Generated 17/Abr/2016 23:30:22 by Hibernate Tools 4.3.1



/**
 * ComRelVendaProduto generated by hbm2java
 */
public class ComRelVendaProduto  implements java.io.Serializable {


     private int idRel;
     private CadProduto cadProduto;
     private ComVendaPedido comVendaPedido;

    public ComRelVendaProduto() {
    }

    public ComRelVendaProduto(int idRel, CadProduto cadProduto, ComVendaPedido comVendaPedido) {
       this.idRel = idRel;
       this.cadProduto = cadProduto;
       this.comVendaPedido = comVendaPedido;
    }
   
    public int getIdRel() {
        return this.idRel;
    }
    
    public void setIdRel(int idRel) {
        this.idRel = idRel;
    }
    public CadProduto getCadProduto() {
        return this.cadProduto;
    }
    
    public void setCadProduto(CadProduto cadProduto) {
        this.cadProduto = cadProduto;
    }
    public ComVendaPedido getComVendaPedido() {
        return this.comVendaPedido;
    }
    
    public void setComVendaPedido(ComVendaPedido comVendaPedido) {
        this.comVendaPedido = comVendaPedido;
    }




}


