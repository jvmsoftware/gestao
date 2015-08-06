/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.admin;

import br.com.jvmsoftware.daos.PubTipoCadastroDAO;
import br.com.jvmsoftware.entities.PubTipoCadastro;
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
public class TipoCadastroController implements Serializable{
    
    private final PubTipoCadastroDAO tCadDAO = new PubTipoCadastroDAO();
    private PubUsuario usu = new PubUsuario();
    private PubTipoCadastro selectedTipoCadastro;
    private List<PubTipoCadastro> listTipoCadastros;
    private String msg;
    

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedTipoCadastro = (PubTipoCadastro)request.getSession().getAttribute("selectedTipoCadastro");
        try {
            listTipoCadastros = tCadDAO.listAllTipoCadastros();
        } catch (SQLException ex) {
            Logger.getLogger(TipoCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
   /**
    * navegação
    * @return 
    */
    
    public String tipoCadastro() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedTipoCadastro", null);  
        request.getSession().setAttribute("sistema", 0);
        String navegar = "/pages/admin/tipoCadastro";
        return navegar;
    }
    
    public String tipoCadastrosNew() throws SQLException {
        String navegar;
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedTipoCadastro = new PubTipoCadastro();
        request.getSession().setAttribute("selectedTipoCadastro", selectedTipoCadastro);
        navegar = "/pages/admin/tipoCadastroNew";
        return navegar;
    }

    public String inserirTipoCadastro() {
        String navegar = "/pages/admin/tipoCadastro";
        try {
            tCadDAO.inserirTipoCadastro(selectedTipoCadastro);
            msg = "Tipo de Cadastro incluida com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(TipoCadastroController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/tipoCadastroNew";
        }
        return navegar;
    }
    

    
    /**
     * Getters & setters
     * @return 
     */
    
    public PubTipoCadastro getSelectedTipoCadastro() {
        return selectedTipoCadastro;
    }

    public void setSelectedTipoCadastro(PubTipoCadastro selectedTipoCadastro) {
        this.selectedTipoCadastro = selectedTipoCadastro;
    }

    public List<PubTipoCadastro> getListTipoCadastros() {
        return listTipoCadastros;
    }

    public void setListTipoCadastros(List<PubTipoCadastro> listTipoCadastros) {
        this.listTipoCadastros = listTipoCadastros;
    }    
    
}
