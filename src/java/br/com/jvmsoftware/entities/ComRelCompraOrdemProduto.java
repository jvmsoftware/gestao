package br.com.jvmsoftware.entities;
// Generated 3/Abr/2016 2:22:35 by Hibernate Tools 4.3.1



/**
 * ComRelCompraOrdemProduto generated by hbm2java
 */
public class ComRelCompraOrdemProduto  implements java.io.Serializable {


     private int idRel;
     private CadProduto cadProduto;
     private ComCompraOrdem comCompraOrdem;

    public ComRelCompraOrdemProduto() {
    }

    public ComRelCompraOrdemProduto(int idRel, CadProduto cadProduto, ComCompraOrdem comCompraOrdem) {
       this.idRel = idRel;
       this.cadProduto = cadProduto;
       this.comCompraOrdem = comCompraOrdem;
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
    public ComCompraOrdem getComCompraOrdem() {
        return this.comCompraOrdem;
    }
    
    public void setComCompraOrdem(ComCompraOrdem comCompraOrdem) {
        this.comCompraOrdem = comCompraOrdem;
    }




}


