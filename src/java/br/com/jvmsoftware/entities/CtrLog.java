package br.com.jvmsoftware.entities;
// Generated 4/Ago/2015 22:25:45 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * CtrLog generated by hbm2java
 */
public class CtrLog  implements java.io.Serializable {


     private Integer idLog;
     private PubSistema pubSistema;
     private PubUsuario pubUsuario;
     private Date dataHora;
     private String tread;
     private String log;

    public CtrLog() {
    }

	
    public CtrLog(PubSistema pubSistema, PubUsuario pubUsuario, Date dataHora) {
        this.pubSistema = pubSistema;
        this.pubUsuario = pubUsuario;
        this.dataHora = dataHora;
    }
    public CtrLog(PubSistema pubSistema, PubUsuario pubUsuario, Date dataHora, String tread, String log) {
       this.pubSistema = pubSistema;
       this.pubUsuario = pubUsuario;
       this.dataHora = dataHora;
       this.tread = tread;
       this.log = log;
    }
   
    public Integer getIdLog() {
        return this.idLog;
    }
    
    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }
    public PubSistema getPubSistema() {
        return this.pubSistema;
    }
    
    public void setPubSistema(PubSistema pubSistema) {
        this.pubSistema = pubSistema;
    }
    public PubUsuario getPubUsuario() {
        return this.pubUsuario;
    }
    
    public void setPubUsuario(PubUsuario pubUsuario) {
        this.pubUsuario = pubUsuario;
    }
    public Date getDataHora() {
        return this.dataHora;
    }
    
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    public String getTread() {
        return this.tread;
    }
    
    public void setTread(String tread) {
        this.tread = tread;
    }
    public String getLog() {
        return this.log;
    }
    
    public void setLog(String log) {
        this.log = log;
    }




}

