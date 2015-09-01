/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.admin;

import br.com.jvmsoftware.daos.WflSeveridadeDAO;
import br.com.jvmsoftware.entities.WflSeveridade;
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
public class AdminSeveridadeController implements Serializable{
    
    private final WflSeveridadeDAO sevDAO = new WflSeveridadeDAO();
    private PubUsuario usu = new PubUsuario();
    private WflSeveridade selectedSeveridade;
    private List<WflSeveridade> listSeveridades;
    private String msg;
    

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedSeveridade = (WflSeveridade)request.getSession().getAttribute("selectedSeveridade");
        try {
            listSeveridades = sevDAO.listAllSeveridades();
        } catch (SQLException ex) {
            Logger.getLogger(AdminSeveridadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
   /**
    * navegação
    * @return 
    */
    
    public String severidade() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedSeveridade", null);  
        request.getSession().setAttribute("sistema", 0);
        String navegar = "/pages/admin/severidade";
        return navegar;
    }
    
    public String severidadeNew() throws SQLException {
        String navegar;
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedSeveridade = new WflSeveridade();
        request.getSession().setAttribute("selectedSeveridade", selectedSeveridade);
        navegar = "/pages/admin/severidadeNew";
        return navegar;
    }

    public String inserirSeveridade() {
        String navegar = "/pages/admin/severidade";
        try {
            sevDAO.inserirSeveridade(selectedSeveridade);
            msg = "Severidade incluida com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(AdminSeveridadeController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/severidadeNew";
        }
        return navegar;
    }
    

    
    /**
     * Getters & setters
     * @return 
     */
    
    public WflSeveridade getSelectedSeveridade() {
        return selectedSeveridade;
    }

    public void setSelectedSeveridade(WflSeveridade selectedSeveridade) {
        this.selectedSeveridade = selectedSeveridade;
    }

    public List<WflSeveridade> getListSeveridades() {
        return listSeveridades;
    }

    public void setListSeveridades(List<WflSeveridade> listSeveridades) {
        this.listSeveridades = listSeveridades;
    }    
    
}
