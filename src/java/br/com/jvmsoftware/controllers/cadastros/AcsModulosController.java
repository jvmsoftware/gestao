/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.AcsEmpresaSistemaDAO;
import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubSistemaDAO;
import br.com.jvmsoftware.entities.AcsEmpresaSistema;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubUsuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
public class AcsModulosController implements Serializable{
    
    private final PubEmpresaDAO empDAO = new PubEmpresaDAO();
    private final PubSistemaDAO sisDAO = new PubSistemaDAO();
    private final AcsEmpresaSistemaDAO empSisDAO = new AcsEmpresaSistemaDAO();
    private List<PubEmpresa> listEmpresa;
    private List<AcsEmpresaSistema> listEmpresaSistema;
    private AcsEmpresaSistema selectEmpresaSistema;
    private PubUsuario usu;
    private int empresa = 0;
    private String msg;
    private boolean renderEmpresa = false;
    // implementar calculo do valorPlano
    private BigDecimal valorPlano = new BigDecimal("0");

    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        empresa = usu.getPubEmpresa().getIdEmpresa();
        renderEmpresa = renderEmpresa();
        try {
            listEmpresa = empDAO.listAllEmpresas();
            if (empresa > 0) {
                listEmpresaSistema = empSisDAO.listEmpresaSistemaByEmpresa(empDAO.getById(empresa));
            } else {
                listEmpresaSistema = empSisDAO.listEmpresaSistemaByEmpresa(usu.getPubEmpresa());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcsModulosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String modulos() {
        String navegar = "/pages/cadastro/modulos";
        return navegar;
    }

    
    // change empresa
    public void changeEmpresa() throws SQLException {
        if (empresa > 0) {
            listEmpresaSistema = empSisDAO.listEmpresaSistemaByEmpresa(empDAO.getById(empresa));
        } else {
            listEmpresaSistema = empSisDAO.listEmpresaSistemaByEmpresa(usu.getPubEmpresa());
        }
    }
    
    private boolean renderEmpresa() {
        boolean rend = false;
        if (usu.getPubEmpresa().getIdEmpresa() == 1) {
            rend = true;
        }
        return rend;
    }

    /* getters & setters
    */
    public List<PubEmpresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<PubEmpresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }

    public List<AcsEmpresaSistema> getListEmpresaSistema() {
        return listEmpresaSistema;
    }

    public void setListEmpresaSistema(List<AcsEmpresaSistema> listEmpresaSistema) {
        this.listEmpresaSistema = listEmpresaSistema;
    }

    public AcsEmpresaSistema getSelectEmpresaSistema() {
        return selectEmpresaSistema;
    }

    public void setSelectEmpresaSistema(AcsEmpresaSistema selectEmpresaSistema) {
        this.selectEmpresaSistema = selectEmpresaSistema;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public boolean isRenderEmpresa() {
        return renderEmpresa;
    }

    public void setRenderEmpresa(boolean renderEmpresa) {
        this.renderEmpresa = renderEmpresa;
    }

    public BigDecimal getValorPlano() {
        return valorPlano;
    }

    public void setValorPlano(BigDecimal valorPlano) {
        this.valorPlano = valorPlano;
    }

    
}
