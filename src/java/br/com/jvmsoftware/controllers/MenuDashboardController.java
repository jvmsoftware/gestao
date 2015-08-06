/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers;

import br.com.jvmsoftware.entities.PubUsuario;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jose
 */
@ManagedBean
@SessionScoped
public class MenuDashboardController {

    private PubUsuario usu = new PubUsuario();
    private String primeiroNome;
    private boolean renderedAdmin = false;

    /**
     * Creates a new instance of MenuController
     */
    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        setNome();
        if (usu.getPubEmpresa().getIdEmpresa() == 1) {
            renderedAdmin = true;
        }
    } 
    
    /**
     * item menu Home
     * @return 
     */
    public String dashboard() {
        return "/pages/dashboard";
    }

    /**
     * menu cadastros
     * @return 
     */
    public String index() {
        return "/pages/cadastro/index";
    }

    public String conta() {
        return "/pages/cadastro/user";
    }

    public String usuarios() {
        return "/pages/cadastro/usuarios";
    }
    
    public String empresa() {
        return "/pages/cadastro/empresa";
    }
    
    public String sistemas() {
        return "/pages/cadastro/sistemas";
    }
    
    public String configuracao() {
        return "/pages/cadastro/configuracao";
    }

    public String clientes() {
        return "/pages/cadastro/clientes";
    }

    public String forncedores() {
        return "/pages/cadastro/fornecedores";
    }

    public String funcionarios() {
        return "/pages/cadastro/funcionarios";
    }

    public String produtos() {
        return "/pages/cadastro/produtos";
    }

    public String servicos() {
        return "/pages/cadastro/servicos";
    }

    public String severidade() {
        return "/pages/admin/severidade";
    }

    public String situacao() {
        return "/pages/admin/situacao";
    }

    public String modulos() {
        return "/pages/admin/modulos";
    }

    public String funcionalidades() {
        return "/pages/admin/funcionalidades";
    }

    public String tipoCadastro() {
        return "/pages/admin/tipoCadastro";
    }

    public String tipoEmpresa() {
        return "/pages/admin/tipoEmpresa";
    }

    public String tipoNegocio() {
        return "/pages/admin/tipoNegocio";
    }

    
    /**
     * acesso aos modulos pelo dashboard
     * @return 
     */
    public String cadastros() {
        return "/pages/cadastro/index";
    }

    
    private void setNome() {
        StringTokenizer nome = new StringTokenizer(usu.getNomeUsuario()," ");
        primeiroNome = nome.nextToken();
    }
    
    
    /**
     * getters & setters
     * @return 
     */
    
    public PubUsuario getUsu() {
        return usu;
    }

    public void setUsu(PubUsuario usu) {
        this.usu = usu;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public boolean isRenderedAdmin() {
        return renderedAdmin;
    }

    public void setRenderedAdmin(boolean renderedAdmin) {
        this.renderedAdmin = renderedAdmin;
    }
    
    
    
}
