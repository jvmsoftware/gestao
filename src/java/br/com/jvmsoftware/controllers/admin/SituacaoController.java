/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.admin;

import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubSistemaDAO;
import br.com.jvmsoftware.daos.WflSituacaoDAO;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.WflSituacao;
import br.com.jvmsoftware.entities.PubSistema;
import br.com.jvmsoftware.entities.PubUsuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class SituacaoController implements Serializable{
    
    private final WflSituacaoDAO sitDAO = new WflSituacaoDAO();
    private final PubEmpresaDAO empDAO = new PubEmpresaDAO();
    private final PubSistemaDAO sisDAO = new PubSistemaDAO();
    private PubUsuario usu = new PubUsuario();
    private WflSituacao selectedSituacao;
    private List<WflSituacao> listSituacaos;
    private List<PubEmpresa> listEmpresas;
    private List<PubSistema> listSistemas;
    private int empresa = 0;
    private int sistema = 0;
    private boolean disableNew = true;
    private String msg;
    

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedSituacao = (WflSituacao)request.getSession().getAttribute("selectedSituacao");
        try {
            listSituacaos = sitDAO.listAllSituacao();
            listEmpresas = empDAO.listAllEmpresas();
            listSistemas = sisDAO.listAllSistemas();
        } catch (SQLException ex) {
            Logger.getLogger(SituacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
   /**
    * navegação
    * @return 
    */
    
    public String situacao() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedSituacao", null);  
        request.getSession().setAttribute("sistema", 0);
        String navegar = "/pages/admin/situacao";
        return navegar;
    }
    
    public String situacaosNew() throws SQLException {
        String navegar;
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedSituacao = new WflSituacao();
        selectedSituacao.setPubEmpresa(empDAO.getById(empresa));
        selectedSituacao.setPubSistema(sisDAO.getById(sistema));
        request.getSession().setAttribute("selectedSituacao", selectedSituacao);
        navegar = "/pages/admin/situacaoNew";
        return navegar;
    }

    public String inserirSituacao() {
        String navegar = "/pages/admin/situacao";
        try {
            sitDAO.inserirSituacao(selectedSituacao);
            msg = "Tipo de Empresa incluida com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(SituacaoController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/situacaoNew";
        }
        return navegar;
    }
    
    
    // change empresa
    public void changeCombo() throws SQLException {
        if (empresa == 0 && sistema == 0) {
            listSituacaos = sitDAO.listAllSituacao();
            disableNew = true;
        } else {
            listSituacaos = sitDAO.listSituacaoByFiltro(empresa, sistema);
            disableNew = !(empresa > 0 && sistema > 0);
        }
    }

    
    /**
     * Getters & setters
     * @return 
     */
    
    public WflSituacao getSelectedSituacao() {
        return selectedSituacao;
    }

    public void setSelectedSituacao(WflSituacao selectedSituacao) {
        this.selectedSituacao = selectedSituacao;
    }

    public List<WflSituacao> getListSituacaos() {
        return listSituacaos;
    }

    public void setListSituacaos(List<WflSituacao> listSituacaos) {
        this.listSituacaos = listSituacaos;
    }    

    public List<PubEmpresa> getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(List<PubEmpresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public List<PubSistema> getListSistemas() {
        return listSistemas;
    }

    public void setListSistemas(List<PubSistema> listSistemas) {
        this.listSistemas = listSistemas;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public int getSistema() {
        return sistema;
    }

    public void setSistema(int sistema) {
        this.sistema = sistema;
    }

    public boolean isDisableNew() {
        return disableNew;
    }

    public void setDisableNew(boolean disableNew) {
        this.disableNew = disableNew;
    }
    
}
