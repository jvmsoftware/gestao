/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.admin;

import br.com.jvmsoftware.daos.PubTipoEmpresaDAO;
import br.com.jvmsoftware.entities.PubTipoEmpresa;
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
public class AdminTipoEmpresaController implements Serializable{
    
    private final PubTipoEmpresaDAO tEmpDAO = new PubTipoEmpresaDAO();
    private PubUsuario usu = new PubUsuario();
    private PubTipoEmpresa selectedTipoEmpresa;
    private List<PubTipoEmpresa> listTipoEmpresas;
    private String msg;
    

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedTipoEmpresa = (PubTipoEmpresa)request.getSession().getAttribute("selectedTipoEmpresa");
        try {
            listTipoEmpresas = tEmpDAO.listAllTipoEmpresas();
        } catch (SQLException ex) {
            Logger.getLogger(AdminTipoEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
   /**
    * navegação
    * @return 
    */
    
    public String tipoEmpresa() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedTipoEmpresa", null);  
        request.getSession().setAttribute("sistema", 0);
        String navegar = "/pages/admin/tipoEmpresa";
        return navegar;
    }
    
    public String tipoEmpresasNew() throws SQLException {
        String navegar;
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedTipoEmpresa = new PubTipoEmpresa();
        request.getSession().setAttribute("selectedTipoEmpresa", selectedTipoEmpresa);
        navegar = "/pages/admin/tipoEmpresaNew";
        return navegar;
    }

    public String inserirTipoEmpresa() {
        String navegar = "/pages/admin/tipoEmpresa";
        try {
            tEmpDAO.inserirTipoEmpresa(selectedTipoEmpresa);
            msg = "Tipo de Empresa incluida com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(AdminTipoEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/tipoEmpresaNew";
        }
        return navegar;
    }
    

    
    /**
     * Getters & setters
     * @return 
     */
    
    public PubTipoEmpresa getSelectedTipoEmpresa() {
        return selectedTipoEmpresa;
    }

    public void setSelectedTipoEmpresa(PubTipoEmpresa selectedTipoEmpresa) {
        this.selectedTipoEmpresa = selectedTipoEmpresa;
    }

    public List<PubTipoEmpresa> getListTipoEmpresas() {
        return listTipoEmpresas;
    }

    public void setListTipoEmpresas(List<PubTipoEmpresa> listTipoEmpresas) {
        this.listTipoEmpresas = listTipoEmpresas;
    }    
    
}
