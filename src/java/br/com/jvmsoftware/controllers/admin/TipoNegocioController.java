/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.admin;

import br.com.jvmsoftware.daos.PubTipoEmpresaDAO;
import br.com.jvmsoftware.daos.PubTipoNegocioDAO;
import br.com.jvmsoftware.entities.PubTipoNegocio;
import br.com.jvmsoftware.entities.PubSistema;
import br.com.jvmsoftware.entities.PubTipoEmpresa;
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
public class TipoNegocioController implements Serializable{
    
    private final PubTipoNegocioDAO tNegDAO = new PubTipoNegocioDAO();
    private final PubTipoEmpresaDAO tEmpDAO = new PubTipoEmpresaDAO();
    private PubUsuario usu = new PubUsuario();
    private PubTipoNegocio selectedTipoNegocio;
    private List<PubTipoEmpresa> listTipoEmpresas;
    private List<PubTipoNegocio> listTipoNegocios;
    private int tipoEmpresa = 0;
    private boolean disableNew = true;
    private String msg;
    

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedTipoNegocio = (PubTipoNegocio)request.getSession().getAttribute("selectedTipoNegocio");
        try {
            listTipoNegocios = tNegDAO.listAllTipoNegocios();
            listTipoEmpresas = tEmpDAO.listAllTipoEmpresas();
        } catch (SQLException ex) {
            Logger.getLogger(TipoNegocioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
   /**
    * navegação
    * @return 
    */
    
    public String tipoNegocio() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedTipoNegocio", null);  
        request.getSession().setAttribute("tipoEmpresa", 0);
        String navegar = "/pages/admin/tipoNegocio";
        return navegar;
    }
    
    public String tipoNegociosNew() throws SQLException {
        String navegar;
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedTipoNegocio = new PubTipoNegocio();
        selectedTipoNegocio.setPubTipoEmpresa(tEmpDAO.getById(tipoEmpresa));
        request.getSession().setAttribute("selectedTipoNegocio", selectedTipoNegocio);
        navegar = "/pages/admin/tipoNegocioNew";
        return navegar;
    }

    public String inserirTipoNegocio() {
        String navegar = "/pages/admin/tipoNegocio";
        try {
            tNegDAO.inserirTipoNegocio(selectedTipoNegocio);
            msg = "Tipo de Negocio incluido com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(TipoNegocioController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/admin/tipoNegocioNew";
        }
        return navegar;
    }
    
        // change modulo
    public void changeTipoEmpresa() throws SQLException {
        if (tipoEmpresa == 0) {
            disableNew = true;
            listTipoNegocios = tNegDAO.listAllTipoNegocios();
        } else {
            disableNew = false;
            listTipoNegocios = tNegDAO.listTipoNegociosByTipoEmpresa(tEmpDAO.getById(tipoEmpresa));
        }
    }
    

    
    /**
     * Getters & setters
     * @return 
     */
    
    public PubTipoNegocio getSelectedTipoNegocio() {
        return selectedTipoNegocio;
    }

    public void setSelectedTipoNegocio(PubTipoNegocio selectedTipoNegocio) {
        this.selectedTipoNegocio = selectedTipoNegocio;
    }

    public List<PubTipoNegocio> getListTipoNegocios() {
        return listTipoNegocios;
    }

    public void setListTipoNegocios(List<PubTipoNegocio> listTipoNegocios) {
        this.listTipoNegocios = listTipoNegocios;
    }    

    public List<PubTipoEmpresa> getListTipoEmpresas() {
        return listTipoEmpresas;
    }

    public void setListTipoEmpresas(List<PubTipoEmpresa> listTipoEmpresas) {
        this.listTipoEmpresas = listTipoEmpresas;
    }

    public int getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(int tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public boolean isDisableNew() {
        return disableNew;
    }

    public void setDisableNew(boolean disableNew) {
        this.disableNew = disableNew;
    }
    
}
