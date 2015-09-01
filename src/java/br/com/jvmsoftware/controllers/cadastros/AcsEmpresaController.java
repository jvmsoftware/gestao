/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubEstadoDAO;
import br.com.jvmsoftware.daos.PubMunicipioDAO;
import br.com.jvmsoftware.daos.PubTipoCadastroDAO;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubEstado;
import br.com.jvmsoftware.entities.PubMunicipio;
import br.com.jvmsoftware.entities.PubTipoCadastro;
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
public class AcsEmpresaController implements Serializable{
    
    private final PubEmpresaDAO empDAO = new PubEmpresaDAO();
    private final PubEstadoDAO estDAO = new PubEstadoDAO();
    private final PubMunicipioDAO municDAO = new PubMunicipioDAO();
    private final PubTipoCadastroDAO tCadDAO = new PubTipoCadastroDAO();
    private List<PubEstado> listEstado;
    private List<PubMunicipio> listMunicipio;
    private List<PubTipoCadastro> listTCadastro;
    private PubEmpresa empresa;
    private PubUsuario usu;
    private int estado = 0;
    private int municipio = 0;
    private String tipoCad = null;
    private String msg;

    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        empresa = usu.getPubEmpresa();
        tipoCad = empresa.getPubTipoCadastro().getCodigoTipoCadastro();
        if (empresa.getPubEstado() != null) {
            estado = empresa.getPubEstado().getIdEstado();
            if (empresa.getPubMunicipio() != null) {
                municipio = empresa.getPubMunicipio().getIdMunicipio();
            }
        }
        try {
            listTCadastro = tCadDAO.listAllTipoCadastros();
            listEstado = estDAO.listAllEstados();
            if (estado != 0) {
                listMunicipio = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
            }
        } catch (SQLException ex) {
                    Logger.getLogger(AcsEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String empresas() {
        String navegar = "/pages/cadastro/empresa";
        return navegar;
    }
    
    public String alterarEmpresa() {
        String navegar = "/pages/cadastro/empresaEdit";
        return navegar;
    }
    
    public String finalizaAlterarEmpresa() {
        String navegar = "/pages/cadastro/empresa";
        try {
            msg = "Dados da empresa alterados com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            empDAO.atualizarEmpresa(empresa);
        } catch (SQLException ex) {
            msg = "Dados da empresa não foram atualizados.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            Logger.getLogger(AcsEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return navegar;
    }

    // change estado
    public void changeEstado() throws SQLException {
        listMunicipio = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
    }
    
    public PubEmpresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PubEmpresa empresa) {
        this.empresa = empresa;
    }

    public List<PubEstado> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<PubEstado> listEstado) {
        this.listEstado = listEstado;
    }

    public List<PubMunicipio> getListMunicipio() {
        return listMunicipio;
    }

    public void setListMunicipio(List<PubMunicipio> listMunicipio) {
        this.listMunicipio = listMunicipio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public String getTipoCad() {
        return tipoCad;
    }

    public void setTipoCad(String tipoCad) {
        this.tipoCad = tipoCad;
    }

    public List<PubTipoCadastro> getListTCadastro() {
        return listTCadastro;
    }

    public void setListTCadastro(List<PubTipoCadastro> listTCadastro) {
        this.listTCadastro = listTCadastro;
    }
    
    
    
    
}
