package br.com.jvmsoftware.entities;
// Generated 17/Abr/2016 23:30:22 by Hibernate Tools 4.3.1



/**
 * ComRelCompraOrdemServico generated by hbm2java
 */
public class ComRelCompraOrdemServico  implements java.io.Serializable {


     private int idRel;
     private CadServicos cadServicos;
     private ComCompraOrdem comCompraOrdem;

    public ComRelCompraOrdemServico() {
    }

    public ComRelCompraOrdemServico(int idRel, CadServicos cadServicos, ComCompraOrdem comCompraOrdem) {
       this.idRel = idRel;
       this.cadServicos = cadServicos;
       this.comCompraOrdem = comCompraOrdem;
    }
   
    public int getIdRel() {
        return this.idRel;
    }
    
    public void setIdRel(int idRel) {
        this.idRel = idRel;
    }
    public CadServicos getCadServicos() {
        return this.cadServicos;
    }
    
    public void setCadServicos(CadServicos cadServicos) {
        this.cadServicos = cadServicos;
    }
    public ComCompraOrdem getComCompraOrdem() {
        return this.comCompraOrdem;
    }
    
    public void setComCompraOrdem(ComCompraOrdem comCompraOrdem) {
        this.comCompraOrdem = comCompraOrdem;
    }




}


