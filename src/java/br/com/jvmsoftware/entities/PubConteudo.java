package br.com.jvmsoftware.entities;
// Generated 17/Abr/2016 23:30:22 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * PubConteudo generated by hbm2java
 */
public class PubConteudo  implements java.io.Serializable {


     private Integer idConteudo;
     private PubMenu pubMenu;
     private PubPosicao pubPosicao;
     private String conteudo;
     private Date inicioExibicao;
     private Date fimExibicao;

    public PubConteudo() {
    }

	
    public PubConteudo(PubMenu pubMenu, PubPosicao pubPosicao, String conteudo, Date inicioExibicao) {
        this.pubMenu = pubMenu;
        this.pubPosicao = pubPosicao;
        this.conteudo = conteudo;
        this.inicioExibicao = inicioExibicao;
    }
    public PubConteudo(PubMenu pubMenu, PubPosicao pubPosicao, String conteudo, Date inicioExibicao, Date fimExibicao) {
       this.pubMenu = pubMenu;
       this.pubPosicao = pubPosicao;
       this.conteudo = conteudo;
       this.inicioExibicao = inicioExibicao;
       this.fimExibicao = fimExibicao;
    }
   
    public Integer getIdConteudo() {
        return this.idConteudo;
    }
    
    public void setIdConteudo(Integer idConteudo) {
        this.idConteudo = idConteudo;
    }
    public PubMenu getPubMenu() {
        return this.pubMenu;
    }
    
    public void setPubMenu(PubMenu pubMenu) {
        this.pubMenu = pubMenu;
    }
    public PubPosicao getPubPosicao() {
        return this.pubPosicao;
    }
    
    public void setPubPosicao(PubPosicao pubPosicao) {
        this.pubPosicao = pubPosicao;
    }
    public String getConteudo() {
        return this.conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    public Date getInicioExibicao() {
        return this.inicioExibicao;
    }
    
    public void setInicioExibicao(Date inicioExibicao) {
        this.inicioExibicao = inicioExibicao;
    }
    public Date getFimExibicao() {
        return this.fimExibicao;
    }
    
    public void setFimExibicao(Date fimExibicao) {
        this.fimExibicao = fimExibicao;
    }




}


