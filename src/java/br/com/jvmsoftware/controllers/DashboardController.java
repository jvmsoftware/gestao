/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubUsuario;
import br.com.jvmsoftware.jsfsecurity.SecurityUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class DashboardController implements Serializable{
     
    private PubUsuario usu = new PubUsuario();
    private DashboardModel model;
    private DashboardModel modelSis;
    private boolean cadEmpIncompleto = false;
    private boolean confgIncompleto = false;
     
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        PubEmpresa e = usu.getPubEmpresa();
        if (usu.getPubEmpresa() == null) {
            confgIncompleto = false;
            cadEmpIncompleto = true;
        }
        if (usu.getPubEmpresa() != null && usu.getPubEmpresa().getPubConfigEmpresas().isEmpty()) {
            confgIncompleto = true;
            cadEmpIncompleto = false;
        }
        montaModel();
    }
     
    /*** navegação
     * @return 
     */
    public String cadastrarEmpresa() {
        String viewId = SecurityUtil.logIn("/pages/usuarioEmpresa");
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("usuario", usu);  
        return viewId;
    }
    
    public String configurar() {
        String viewId = SecurityUtil.logIn("/pages/UsuarioNegocio");
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("usuario", usu);  
        return viewId;
    }
    
    /*** 
     * Redirect para sistemas
     * @return 
     */
    public String cadastros() {
        String viewId = SecurityUtil.logIn("/pages/cadastro/index");
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("usuario", usu);  
        request.getSession().setAttribute("step", "negocio");  
        return viewId;
    }
    
    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
         
        addMessage(message);
    }
     
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private void montaModel() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        
        column1.addWidget("avisos");
         
        column2.addWidget("sistemas");
        column2.addWidget("graficos");
        column3.addWidget("util");
 
        model.addColumn(column1);
        model.addColumn(column2);
        model.addColumn(column3);
    }
    
    private void montaModelSis() {
        modelSis = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();
        DashboardColumn column4 = new DefaultDashboardColumn();
        
        column1.addWidget("acesso");
         
        column2.addWidget("cadastro");
        
        column3.addWidget("compra");
 
        column4.addWidget("financeiro");
        
        modelSis.addColumn(column1);
        modelSis.addColumn(column2);
        modelSis.addColumn(column3);
        modelSis.addColumn(column4);
    }
        
    public DashboardModel getModel() {
        return model;
    }
        
    public DashboardModel getModelSis() {
        return modelSis;
    }

    public PubUsuario getUsu() {
        return usu;
    }

    public void setUsu(PubUsuario usu) {
        this.usu = usu;
    }

    public boolean isCadEmpIncompleto() {
        return cadEmpIncompleto;
    }

    public void setCadEmpIncompleto(boolean cadEmpIncompleto) {
        this.cadEmpIncompleto = cadEmpIncompleto;
    }

    public boolean isConfgIncompleto() {
        return confgIncompleto;
    }

    public void setConfgIncompleto(boolean confgIncompleto) {
        this.confgIncompleto = confgIncompleto;
    }
    
}