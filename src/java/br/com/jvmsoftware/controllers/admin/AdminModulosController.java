/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.admin;

import br.com.jvmsoftware.daos.PubSistemaDAO;
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
public class AdminModulosController implements Serializable{
    
    private final PubSistemaDAO sisDAO = new PubSistemaDAO();
    private PubUsuario usu = new PubUsuario();
    private PubSistema selectedModulo;
    private List<PubSistema> listModulos;
    private String msg;
    

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedModulo = (PubSistema)request.getSession().getAttribute("selectedModulo");
        try {
            listModulos = sisDAO.listAllSistemas();
        } catch (SQLException ex) {
            Logger.getLogger(AdminModulosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
   /**
    * navegação
    * @return 
    */
    
    public String modulos() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedModulo", null);  
        String navegar = "/pages/admin/modulos";
        return navegar;
    }
    
    public String modulosEdit() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedModulo", selectedModulo);
        String navegar = "/pages/admin/modulosEdit";
        return navegar;
    }
    
    public String modulosNew() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedModulo = new PubSistema();
        selectedModulo.setAtivo(true);
        request.getSession().setAttribute("selectedModulo", selectedModulo);
        String navegar = "/pages/admin/modulosNew";
        return navegar;
    }

    public String alterarModulos() {
        String navegar = "/pages/admin/modulos";
        try {
            sisDAO.atualizarSistema(selectedModulo);
            msg = "cadastro do modulo alterado com sucesso";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(AdminModulosController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/modulosEdit";
        }
        return navegar;
    }

    public String inserirModulo() {
        String navegar = "/pages/admin/modulos";
        try {
            sisDAO.inserirSistema(selectedModulo);
            msg = "Modulo incluido com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(AdminModulosController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/modulosNew";
        }
        return navegar;
    }
    
    public String inativaModulo() {
        String navegar = "/pages/admin/modulos";
        try {
            if (selectedModulo.isAtivo() == true) {
                selectedModulo.setAtivo(false);
                msg = "modulo inativado com sucesso.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            } else {
                selectedModulo.setAtivo(true);
                msg = "modulo ativado com sucesso.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            }
            sisDAO.atualizarSistema(selectedModulo);
            listModulos = sisDAO.listAllSistemas();
        } catch (SQLException ex) {
            Logger.getLogger(AdminModulosController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return navegar;
    }


    
    
    /**
     * Getters & setters
     * @return 
     */
    
    public PubSistema getSelectedModulo() {
        return selectedModulo;
    }

    public void setSelectedModulo(PubSistema selectedModulo) {
        this.selectedModulo = selectedModulo;
    }

    public List<PubSistema> getListModulos() {
        return listModulos;
    }

    public void setListModulos(List<PubSistema> listModulos) {
        this.listModulos = listModulos;
    }
    
    
    
}
