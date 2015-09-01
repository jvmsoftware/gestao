/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.admin;

import br.com.jvmsoftware.daos.PubFuncionalidadeDAO;
import br.com.jvmsoftware.daos.PubSistemaDAO;
import br.com.jvmsoftware.entities.PubFuncionalidade;
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
public class AdminFuncionalidadesController implements Serializable{
    
    private final PubSistemaDAO sisDAO = new PubSistemaDAO();
    private final PubFuncionalidadeDAO funDAO = new PubFuncionalidadeDAO();
    private PubUsuario usu = new PubUsuario();
    private PubFuncionalidade selectedFuncionalidade;
    private List<PubFuncionalidade> listFuncionalidades;
    private List<PubSistema> listSistemas;
    private String msg;
    private int sistema = 0;
    

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedFuncionalidade = (PubFuncionalidade)request.getSession().getAttribute("selectedFuncionalidade");
        try {
            listSistemas = sisDAO.listAllSistemas();
            changeModulo();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFuncionalidadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
   /**
    * navegação
    * @return 
    */
    
    public String funcionalidades() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedFuncionalidade", null);  
        request.getSession().setAttribute("sistema", 0);
        String navegar = "/pages/admin/funcionalidades";
        return navegar;
    }
    
    public String funcionalidadesNew() throws SQLException {
        String navegar;
        if (sistema != 0) {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedFuncionalidade = new PubFuncionalidade();
        selectedFuncionalidade.setPubSistema(sisDAO.getById(sistema));
        request.getSession().setAttribute("selectedFuncionalidade", selectedFuncionalidade);
        navegar = "/pages/admin/funcionalidadesNew";
        } else {
            msg = "selecione um modulo para incluir uma nova funcionalidade.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
            navegar = "/pages/admin/funcionalidades";
        }
        return navegar;
    }

    public String inserirFuncionalidade() {
        String navegar = "/pages/admin/funcionalidades";
        try {
            funDAO.inserirFuncionalidade(selectedFuncionalidade);
            msg = "Funcionalidade incluida com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(AdminFuncionalidadesController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/funcionalidadesNew";
        }
        return navegar;
    }
    

    // change modulo
    public void changeModulo() throws SQLException {
        if (sistema == 0) {
            listFuncionalidades = funDAO.listAllFuncionalidades();
        } else {
            listFuncionalidades = funDAO.listFuncionalidadesBySistema(sisDAO.getById(sistema));
        }
    }
    
    
    
    /**
     * Getters & setters
     * @return 
     */
    
    public PubFuncionalidade getSelectedFuncionalidade() {
        return selectedFuncionalidade;
    }

    public void setSelectedFuncionalidade(PubFuncionalidade selectedFuncionalidade) {
        this.selectedFuncionalidade = selectedFuncionalidade;
    }

    public List<PubFuncionalidade> getListFuncionalidades() {
        return listFuncionalidades;
    }

    public void setListFuncionalidades(List<PubFuncionalidade> listFuncionalidades) {
        this.listFuncionalidades = listFuncionalidades;
    }

    public List<PubSistema> getListSistemas() {
        return listSistemas;
    }

    public void setListSistemas(List<PubSistema> listSistemas) {
        this.listSistemas = listSistemas;
    }

    public int getSistema() {
        return sistema;
    }

    public void setSistema(int sistema) {
        this.sistema = sistema;
    }
    
    
    
}
