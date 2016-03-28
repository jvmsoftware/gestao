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
     * menu Usuário Logado
     * @return 
     */
    // Minha conta
    public String conta() {
        return "/pages/cadastro/user";
    }

    
    /**
     * menu Admin
     * @return 
     */
    public String adminSeveridade() {
        return "/pages/admin/severidade";
    }

    public String adminSituacao() {
        return "/pages/admin/situacao";
    }

    public String adminModulos() {
        return "/pages/admin/modulos";
    }

    public String adminFuncionalidades() {
        return "/pages/admin/funcionalidades";
    }

    public String adminTipoCadastro() {
        return "/pages/admin/tipoCadastro";
    }

    public String adminTipoEmpresa() {
        return "/pages/admin/tipoEmpresa";
    }

    public String adminTipoNegocio() {
        return "/pages/admin/tipoNegocio";
    }
    
    
    /**
     * menu cadastros
     * @return 
     */
    
    // acesso aos modulos pelo dashboard
    public String cadastros() {
        return "/pages/cadastro/index";
    }
    
    // Controle de acesso
    public String acsUsuarios() {
        return "/pages/cadastro/usuarios";
    }
    
    public String acsEmpresa() {
        return "/pages/cadastro/empresa";
    }
    
    public String acsSistemas() {
        return "/pages/cadastro/sistemas";
    }
    
    public String acsConfiguracao() {
        return "/pages/cadastro/configuracao";
    }
    
    // Cadastros
    public String cadClientes() {
        return "/pages/cadastro/clientes";
    }

    public String cadForncedores() {
        return "/pages/cadastro/fornecedores";
    }

    public String cadFuncionarios() {
        return "/pages/cadastro/funcionarios";
    }

    public String cadTransportadores() {
        return "/pages/cadastro/transportadores";
    }

    public String cadProdutos() {
        return "/pages/cadastro/produtos";
    }

    public String cadServicos() {
        return "/pages/cadastro/servicos";
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
