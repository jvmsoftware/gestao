/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers;

import br.com.jvmsoftware.daos.AcsUsuarioFuncionalidadeDAO;
import br.com.jvmsoftware.entities.PubUsuario;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private AcsUsuarioFuncionalidadeDAO usuFuncDAO = new AcsUsuarioFuncionalidadeDAO();
    private PubUsuario usu = new PubUsuario();
    private String primeiroNome;
    private boolean renderedAdmin = false;
    
    // itens de menu
    private boolean disabledUsu;
    private boolean disabledUsuAdd;
    private boolean disabledUsuEdit;
    private boolean disabledUsuDelete;
    private boolean disabledEmp;
    private boolean disabledEmpAdd;
    private boolean disabledEmpEdit;
    private boolean disabledEmpDelete;
    private boolean disabledSis;
    private boolean disabledSisAdd;
    private boolean disabledSisEdit;
    private boolean disabledSisDelete;
    private boolean disabledCfg;
    private boolean disabledCfgAdd;
    private boolean disabledCfgEdit;
    private boolean disabledCfgDelete;
    private boolean disabledCli;
    private boolean disabledCliAdd;
    private boolean disabledCliEdit;
    private boolean disabledCliDelete;
    private boolean disabledFor;
    private boolean disabledForAdd;
    private boolean disabledForEdit;
    private boolean disabledForDelete;
    private boolean disabledFun;
    private boolean disabledFunAdd;
    private boolean disabledFunEdit;
    private boolean disabledFunDelete;
    private boolean disabledTrs;
    private boolean disabledTrsAdd;
    private boolean disabledTrsEdit;
    private boolean disabledTrsDelete;
    private boolean disabledPro;
    private boolean disabledProAdd;
    private boolean disabledProEdit;
    private boolean disabledProDelete;
    

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
        try {
            disable();
        } catch (SQLException ex) {
            Logger.getLogger(MenuDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /**
     * item menu Home
     * @return 
     */
    public String dashboard() {
        return "/pages/dashboard";
    }

    


    public void disable() throws SQLException {
        disabledUsu = usuFuncDAO.isDisabled(usu, "USU", "view");
        disabledUsuAdd = usuFuncDAO.isDisabled(usu, "USU", "add");
        disabledUsuEdit = usuFuncDAO.isDisabled(usu, "USU", "edit");
        disabledUsuDelete = usuFuncDAO.isDisabled(usu, "USU", "delete");
        disabledEmp = usuFuncDAO.isDisabled(usu, "EMP", "view");
        disabledEmp = usuFuncDAO.isDisabled(usu, "EMP", "add");
        disabledEmp = usuFuncDAO.isDisabled(usu, "EMP", "edit");
        disabledEmp = usuFuncDAO.isDisabled(usu, "EMP", "delete");
        disabledSis = usuFuncDAO.isDisabled(usu, "SIS", "view");
        disabledSis = usuFuncDAO.isDisabled(usu, "SIS", "add");
        disabledSis = usuFuncDAO.isDisabled(usu, "SIS", "edit");
        disabledSis = usuFuncDAO.isDisabled(usu, "SIS", "delete");
        disabledCfg = usuFuncDAO.isDisabled(usu, "CFG", "view");
        disabledCfg = usuFuncDAO.isDisabled(usu, "CFG", "add");
        disabledCfg = usuFuncDAO.isDisabled(usu, "CFG", "edit");
        disabledCfg = usuFuncDAO.isDisabled(usu, "CFG", "delete");
        disabledCli = usuFuncDAO.isDisabled(usu, "CLI", "view");
        disabledCli = usuFuncDAO.isDisabled(usu, "CLI", "add");
        disabledCli = usuFuncDAO.isDisabled(usu, "CLI", "edit");
        disabledCli = usuFuncDAO.isDisabled(usu, "CLI", "delete");
        disabledFor = usuFuncDAO.isDisabled(usu, "FOR", "view");
        disabledFor = usuFuncDAO.isDisabled(usu, "FOR", "add");
        disabledFor = usuFuncDAO.isDisabled(usu, "FOR", "edit");
        disabledFor = usuFuncDAO.isDisabled(usu, "FOR", "delete");
        disabledFun = usuFuncDAO.isDisabled(usu, "FUN", "view");
        disabledFun = usuFuncDAO.isDisabled(usu, "FUN", "add");
        disabledFun = usuFuncDAO.isDisabled(usu, "FUN", "edit");
        disabledFun = usuFuncDAO.isDisabled(usu, "FUN", "delete");
        disabledTrs = usuFuncDAO.isDisabled(usu, "TRS", "view");
        disabledTrs = usuFuncDAO.isDisabled(usu, "TRS", "add");
        disabledTrs = usuFuncDAO.isDisabled(usu, "TRS", "edit");
        disabledTrs = usuFuncDAO.isDisabled(usu, "TRS", "delete");
        disabledPro = usuFuncDAO.isDisabled(usu, "TRS", "view"); // TRS > PRO
        disabledPro = usuFuncDAO.isDisabled(usu, "TRS", "view"); // TRS > PRO
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
    
    public String comOrdemCompra() {
        return "/pages/comercial/ordensCompra";
    }
    
    public String comProcessoCompra() {
        return "/pages/comercial/processosCompra";
    }

    public String comPedidoVenda() {
        return "/pages/comercial/pedidosVenda";
    }
    
    public String comVenda() {
        return "/pages/comercial/Venda";
    }
    
    public String servOrdemServico() {
        return "/pages/servicos/servicos";
    }
    
    public String servGarantia() {
        return "/pages/servicos/garantias";
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

    public boolean isDisabledUsu() {
        return disabledUsu;
    }

    public void setDisabledUsu(boolean disabledUsu) {
        this.disabledUsu = disabledUsu;
    }

    public boolean isDisabledEmp() {
        return disabledEmp;
    }

    public void setDisabledEmp(boolean disabledEmp) {
        this.disabledEmp = disabledEmp;
    }

    public boolean isDisabledSis() {
        return disabledSis;
    }

    public void setDisabledSis(boolean disabledSis) {
        this.disabledSis = disabledSis;
    }

    public boolean isDisabledCfg() {
        return disabledCfg;
    }

    public void setDisabledCfg(boolean disabledCfg) {
        this.disabledCfg = disabledCfg;
    }

    public boolean isDisabledCli() {
        return disabledCli;
    }

    public void setDisabledCli(boolean disabledCli) {
        this.disabledCli = disabledCli;
    }

    public boolean isDisabledFor() {
        return disabledFor;
    }

    public void setDisabledFor(boolean disabledFor) {
        this.disabledFor = disabledFor;
    }

    public boolean isDisabledFun() {
        return disabledFun;
    }

    public void setDisabledFun(boolean disabledFun) {
        this.disabledFun = disabledFun;
    }

    public boolean isDisabledTrs() {
        return disabledTrs;
    }

    public void setDisabledTrs(boolean disabledTrs) {
        this.disabledTrs = disabledTrs;
    }

    public boolean isDisabledPro() {
        return disabledPro;
    }

    public void setDisabledPro(boolean disabledPro) {
        this.disabledPro = disabledPro;
    }

    public boolean isDisabledUsuAdd() {
        return disabledUsuAdd;
    }

    public void setDisabledUsuAdd(boolean disabledUsuAdd) {
        this.disabledUsuAdd = disabledUsuAdd;
    }

    public boolean isDisabledUsuEdit() {
        return disabledUsuEdit;
    }

    public void setDisabledUsuEdit(boolean disabledUsuEdit) {
        this.disabledUsuEdit = disabledUsuEdit;
    }

    public boolean isDisabledUsuDelete() {
        return disabledUsuDelete;
    }

    public void setDisabledUsuDelete(boolean disabledUsuDelete) {
        this.disabledUsuDelete = disabledUsuDelete;
    }

    public boolean isDisabledEmpAdd() {
        return disabledEmpAdd;
    }

    public void setDisabledEmpAdd(boolean disabledEmpAdd) {
        this.disabledEmpAdd = disabledEmpAdd;
    }

    public boolean isDisabledEmpEdit() {
        return disabledEmpEdit;
    }

    public void setDisabledEmpEdit(boolean disabledEmpEdit) {
        this.disabledEmpEdit = disabledEmpEdit;
    }

    public boolean isDisabledEmpDelete() {
        return disabledEmpDelete;
    }

    public void setDisabledEmpDelete(boolean disabledEmpDelete) {
        this.disabledEmpDelete = disabledEmpDelete;
    }

    public boolean isDisabledSisAdd() {
        return disabledSisAdd;
    }

    public void setDisabledSisAdd(boolean disabledSisAdd) {
        this.disabledSisAdd = disabledSisAdd;
    }

    public boolean isDisabledSisEdit() {
        return disabledSisEdit;
    }

    public void setDisabledSisEdit(boolean disabledSisEdit) {
        this.disabledSisEdit = disabledSisEdit;
    }

    public boolean isDisabledSisDelete() {
        return disabledSisDelete;
    }

    public void setDisabledSisDelete(boolean disabledSisDelete) {
        this.disabledSisDelete = disabledSisDelete;
    }

    public boolean isDisabledCfgAdd() {
        return disabledCfgAdd;
    }

    public void setDisabledCfgAdd(boolean disabledCfgAdd) {
        this.disabledCfgAdd = disabledCfgAdd;
    }

    public boolean isDisabledCfgEdit() {
        return disabledCfgEdit;
    }

    public void setDisabledCfgEdit(boolean disabledCfgEdit) {
        this.disabledCfgEdit = disabledCfgEdit;
    }

    public boolean isDisabledCfgDelete() {
        return disabledCfgDelete;
    }

    public void setDisabledCfgDelete(boolean disabledCfgDelete) {
        this.disabledCfgDelete = disabledCfgDelete;
    }

    public boolean isDisabledCliAdd() {
        return disabledCliAdd;
    }

    public void setDisabledCliAdd(boolean disabledCliAdd) {
        this.disabledCliAdd = disabledCliAdd;
    }

    public boolean isDisabledCliEdit() {
        return disabledCliEdit;
    }

    public void setDisabledCliEdit(boolean disabledCliEdit) {
        this.disabledCliEdit = disabledCliEdit;
    }

    public boolean isDisabledCliDelete() {
        return disabledCliDelete;
    }

    public void setDisabledCliDelete(boolean disabledCliDelete) {
        this.disabledCliDelete = disabledCliDelete;
    }

    public boolean isDisabledForAdd() {
        return disabledForAdd;
    }

    public void setDisabledForAdd(boolean disabledForAdd) {
        this.disabledForAdd = disabledForAdd;
    }

    public boolean isDisabledForEdit() {
        return disabledForEdit;
    }

    public void setDisabledForEdit(boolean disabledForEdit) {
        this.disabledForEdit = disabledForEdit;
    }

    public boolean isDisabledForDelete() {
        return disabledForDelete;
    }

    public void setDisabledForDelete(boolean disabledForDelete) {
        this.disabledForDelete = disabledForDelete;
    }

    public boolean isDisabledFunAdd() {
        return disabledFunAdd;
    }

    public void setDisabledFunAdd(boolean disabledFunAdd) {
        this.disabledFunAdd = disabledFunAdd;
    }

    public boolean isDisabledFunEdit() {
        return disabledFunEdit;
    }

    public void setDisabledFunEdit(boolean disabledFunEdit) {
        this.disabledFunEdit = disabledFunEdit;
    }

    public boolean isDisabledFunDelete() {
        return disabledFunDelete;
    }

    public void setDisabledFunDelete(boolean disabledFunDelete) {
        this.disabledFunDelete = disabledFunDelete;
    }

    public boolean isDisabledTrsAdd() {
        return disabledTrsAdd;
    }

    public void setDisabledTrsAdd(boolean disabledTrsAdd) {
        this.disabledTrsAdd = disabledTrsAdd;
    }

    public boolean isDisabledTrsEdit() {
        return disabledTrsEdit;
    }

    public void setDisabledTrsEdit(boolean disabledTrsEdit) {
        this.disabledTrsEdit = disabledTrsEdit;
    }

    public boolean isDisabledTrsDelete() {
        return disabledTrsDelete;
    }

    public void setDisabledTrsDelete(boolean disabledTrsDelete) {
        this.disabledTrsDelete = disabledTrsDelete;
    }

    public boolean isDisabledProAdd() {
        return disabledProAdd;
    }

    public void setDisabledProAdd(boolean disabledProAdd) {
        this.disabledProAdd = disabledProAdd;
    }

    public boolean isDisabledProEdit() {
        return disabledProEdit;
    }

    public void setDisabledProEdit(boolean disabledProEdit) {
        this.disabledProEdit = disabledProEdit;
    }

    public boolean isDisabledProDelete() {
        return disabledProDelete;
    }

    public void setDisabledProDelete(boolean disabledProDelete) {
        this.disabledProDelete = disabledProDelete;
    }
    
    
    
}
