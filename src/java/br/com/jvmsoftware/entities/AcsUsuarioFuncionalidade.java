package br.com.jvmsoftware.entities;
// Generated 9/Abr/2016 18:34:16 by Hibernate Tools 4.3.1



/**
 * AcsUsuarioFuncionalidade generated by hbm2java
 */
public class AcsUsuarioFuncionalidade  implements java.io.Serializable {


     private Integer id;
     private PubEmpresa pubEmpresa;
     private PubFuncionalidade pubFuncionalidade;
     private PubUsuario pubUsuario;
     private boolean desabilitado;

    public AcsUsuarioFuncionalidade() {
    }

    public AcsUsuarioFuncionalidade(PubEmpresa pubEmpresa, PubFuncionalidade pubFuncionalidade, PubUsuario pubUsuario, boolean desabilitado) {
       this.pubEmpresa = pubEmpresa;
       this.pubFuncionalidade = pubFuncionalidade;
       this.pubUsuario = pubUsuario;
       this.desabilitado = desabilitado;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }
    public PubFuncionalidade getPubFuncionalidade() {
        return this.pubFuncionalidade;
    }
    
    public void setPubFuncionalidade(PubFuncionalidade pubFuncionalidade) {
        this.pubFuncionalidade = pubFuncionalidade;
    }
    public PubUsuario getPubUsuario() {
        return this.pubUsuario;
    }
    
    public void setPubUsuario(PubUsuario pubUsuario) {
        this.pubUsuario = pubUsuario;
    }
    public boolean isDesabilitado() {
        return this.desabilitado;
    }
    
    public void setDesabilitado(boolean desabilitado) {
        this.desabilitado = desabilitado;
    }




}


