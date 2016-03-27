package br.com.jvmsoftware.entities;
// Generated 27/Mar/2016 8:26:38 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AcsAcessoUsuarios generated by hbm2java
 */
public class AcsAcessoUsuarios  implements java.io.Serializable {


     private Integer idAcesso;
     private PubUsuario pubUsuario;
     private Date dataHoraLogin;
     private Date dataHoraLogoff;

    public AcsAcessoUsuarios() {
    }

	
    public AcsAcessoUsuarios(PubUsuario pubUsuario, Date dataHoraLogin) {
        this.pubUsuario = pubUsuario;
        this.dataHoraLogin = dataHoraLogin;
    }
    public AcsAcessoUsuarios(PubUsuario pubUsuario, Date dataHoraLogin, Date dataHoraLogoff) {
       this.pubUsuario = pubUsuario;
       this.dataHoraLogin = dataHoraLogin;
       this.dataHoraLogoff = dataHoraLogoff;
    }
   
    public Integer getIdAcesso() {
        return this.idAcesso;
    }
    
    public void setIdAcesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }
    public PubUsuario getPubUsuario() {
        return this.pubUsuario;
    }
    
    public void setPubUsuario(PubUsuario pubUsuario) {
        this.pubUsuario = pubUsuario;
    }
    public Date getDataHoraLogin() {
        return this.dataHoraLogin;
    }
    
    public void setDataHoraLogin(Date dataHoraLogin) {
        this.dataHoraLogin = dataHoraLogin;
    }
    public Date getDataHoraLogoff() {
        return this.dataHoraLogoff;
    }
    
    public void setDataHoraLogoff(Date dataHoraLogoff) {
        this.dataHoraLogoff = dataHoraLogoff;
    }




}


