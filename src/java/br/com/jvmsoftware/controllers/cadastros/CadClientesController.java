/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.CadPessoaDAO;
import br.com.jvmsoftware.daos.PubConfigEmpresaDAO;
import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubTipoCadastroDAO;
import br.com.jvmsoftware.entities.CadPessoa;
import br.com.jvmsoftware.entities.PubConfigEmpresa;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubTipoCadastro;
import br.com.jvmsoftware.entities.PubUsuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
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
public class CadClientesController implements Serializable{
    
    private final CadPessoaDAO pesDAO = new CadPessoaDAO();
    private final PubTipoCadastroDAO tCadDAO = new PubTipoCadastroDAO();
    private CadPessoa selectedCliente;
    private List<CadPessoa> listClientes;
    private List<PubTipoCadastro> listTipoCadastro;
    private PubUsuario usu;
    private int pessoa = 0;
    private int tipoCadastro = 0;
    private String mask = "";
    private String msg;
    private Boolean ok = false;

    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario) request.getSession().getAttribute("usuario");
        selectedCliente = (CadPessoa) request.getSession().getAttribute("selectedCliente");
        if (selectedCliente == null) {
        } else {
            if (selectedCliente.getPubTipoCadastro() != null) {
                tipoCadastro = selectedCliente.getPubTipoCadastro().getIdTipoCadastro();
            }
        }
        try {
            listTipoCadastro = tCadDAO.listAllTipoCadastros();
            listClientes = pesDAO.listClientesByEmpresa(usu.getPubEmpresa());
        } catch (SQLException ex) {
            Logger.getLogger(CadClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String clientes() {
        String navegar = "/pages/cadastro/clientes";
        return navegar;
    }

    public String clienteNew() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/clienteNew";
        listTipoCadastro = tCadDAO.listAllTipoCadastros();
        selectedCliente = new CadPessoa();
        selectedCliente.setPubEmpresa(usu.getPubEmpresa());
        selectedCliente.setCliente(true);
        selectedCliente.setFornecedor(false);
        selectedCliente.setFuncionario(false);
        request.getSession().setAttribute("selectedCliente", selectedCliente);  
        return navegar;
    }
    
    public String insereCliente() throws SQLException {
        String navegar = "/pages/cadastro/clientes";
        selectedCliente.setPubTipoCadastro(tCadDAO.getById(tipoCadastro));
        selectedCliente.setDataCadastro(new Date());
        pesDAO.inserirPessoa(selectedCliente);
        return navegar;
    }
    
    public String clienteEdit() throws SQLException {
        String navegar = "/pages/cadastro/clienteEdit";
        selectedCliente = pesDAO.getById(pessoa);
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedCliente", selectedCliente);  
        return navegar;
    }
    
    public String alteraCliente() throws SQLException {
        String navegar = "/pages/cadastro/clientes";
        pesDAO.updatePessoa(selectedCliente);
        return navegar;
    }

    
    public void tipoCadastroChange() {
        if (tipoCadastro == 1) {
            ok = true;
            mask = "999.999.999-99";
        }
        else if (tipoCadastro == 2) {
            ok = true;
            mask = "99.999.999/9999-99";
        }
    }
    
    /* getters & setters
    */
    public List<CadPessoa> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<CadPessoa> listClientes) {
        this.listClientes = listClientes;
    }

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    public CadPessoa getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(CadPessoa selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<PubTipoCadastro> getListTipoCadastro() {
        return listTipoCadastro;
    }

    public void setListTipoCadastro(List<PubTipoCadastro> listTipoCadastro) {
        this.listTipoCadastro = listTipoCadastro;
    }

    public int getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(int tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}