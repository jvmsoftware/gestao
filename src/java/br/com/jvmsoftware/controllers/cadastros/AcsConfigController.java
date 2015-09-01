/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.PubConfigEmpresaDAO;
import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.entities.PubConfigEmpresa;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubUsuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class AcsConfigController implements Serializable{
    
    private final PubEmpresaDAO empDAO = new PubEmpresaDAO();
    private final PubConfigEmpresaDAO confDAO = new PubConfigEmpresaDAO();
    private List<PubEmpresa> listEmpresa;
    private PubConfigEmpresa ConfEmpresa;
    private PubUsuario usu;
    private int empresa = 0;
    private int tipoEmpresa = 0;
    private int tipoNegocio = 0;
    private String msg;
    private boolean renderEmpresa = false;

    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        empresa = usu.getPubEmpresa().getIdEmpresa();
        renderEmpresa = renderEmpresa();
        try {
            listEmpresa = empDAO.listAllEmpresas();
            ConfEmpresa = confDAO.getByIdEmpresa(empresa);
        } catch (SQLException ex) {
            Logger.getLogger(AcsConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String config() {
        String navegar = "/pages/cadastro/configuracao";
        return navegar;
    }

    
    public String alterarConfig() {
        String navegar = "/pages/cadastro/configuracaoEdit";
        return navegar;
    }
    
    public String finalizaAlterarConfig() {
        String navegar = "/pages/cadastro/configuracao";
        try {
            msg = "Dados da config alterados com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            confDAO.atualizarConfigEmpresa(ConfEmpresa);
        } catch (SQLException ex) {
            msg = "Dados da config não foram atualizados.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            Logger.getLogger(AcsConfigController.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return navegar;
    }

    // change empresa
    public void changeEmpresa() throws SQLException {
        ConfEmpresa = confDAO.getByIdEmpresa(empresa);
    }
    
    private boolean renderEmpresa() {
        boolean rend = false;
        if (usu.getPubEmpresa().getIdEmpresa() == 1) {
            rend = true;
        }
        return rend;
    }

    public List<PubEmpresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<PubEmpresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public PubConfigEmpresa getConfEmpresa() {
        return ConfEmpresa;
    }

    public void setConfEmpresa(PubConfigEmpresa ConfEmpresa) {
        this.ConfEmpresa = ConfEmpresa;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public int getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(int tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public int getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(int tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public boolean isRenderEmpresa() {
        return renderEmpresa;
    }

    public void setRenderEmpresa(boolean renderEmpresa) {
        this.renderEmpresa = renderEmpresa;
    }
        
    
}
