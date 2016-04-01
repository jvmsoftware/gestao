/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.CadPessoaDAO;
import br.com.jvmsoftware.daos.CadProdutoDAO;
import br.com.jvmsoftware.daos.CadRelProdFornecDAO;
import br.com.jvmsoftware.daos.PubEmbalagemDAO;
import br.com.jvmsoftware.entities.CadPessoa;
import br.com.jvmsoftware.entities.CadProduto;
import br.com.jvmsoftware.entities.CadRelProdFornec;
import br.com.jvmsoftware.entities.PubEmbalagem;
import br.com.jvmsoftware.entities.PubUsuario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class CadProdutoController {

    private final CadProdutoDAO prodDAO = new CadProdutoDAO();
    private final CadPessoaDAO pesDAO = new CadPessoaDAO();
    private final PubEmbalagemDAO embDAO = new PubEmbalagemDAO();
    private final CadRelProdFornecDAO relDAO = new CadRelProdFornecDAO();
    private List<CadProduto> listProdutos;
    private List<PubEmbalagem> listEmbalagem;
    private List<CadRelProdFornec> listRelProdFornec;
    private List<CadPessoa> listFornecedores;
    private PubUsuario usu;
    private CadProduto selectedProduto;
    private CadRelProdFornec selectedRelacionamento;
    private String filtro = "";
    private String msg;
    private int embalagem;
    private int fornecedor;
    
    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario) request.getSession().getAttribute("usuario");
        selectedProduto = (CadProduto) request.getSession().getAttribute("selectedProduto");
        try {
            listProdutos = prodDAO.listProdutosByEmpresa(usu.getPubEmpresa(), filtro);
            listFornecedores = pesDAO.listFornecedoresByEmpresa(usu.getPubEmpresa(), filtro);
            if (selectedProduto != null) {
                if (selectedRelacionamento == null) {
                    selectedRelacionamento = new CadRelProdFornec();
                }
                listRelProdFornec  = relDAO.listRelacionamentoByProduto(selectedProduto);
                listEmbalagem = embDAO.listAllEmbalagens();
                embalagem = selectedProduto.getIdProduto();
                if (selectedRelacionamento != null) {
                    listFornecedores = pesDAO.listFornecedoresByEmpresa(usu.getPubEmpresa(), filtro);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Regras de navegação
    */
    
    public String produtos() {
        String navegar = "/pages/cadastro/produtos";
        return navegar;
    }

    public String produtoNew() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/produtoNew";
        selectedProduto = new CadProduto();
        selectedProduto.setPubEmpresa(usu.getPubEmpresa());
        request.getSession().setAttribute("selectedProduto", selectedProduto);  
        return navegar;
    }
    
    public String insereProduto() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/produtoEdit";
        selectedProduto.setPubEmbalagem(embDAO.getById(embalagem));
        selectedProduto.setAtivo(true);
        prodDAO.inserirProduto(selectedProduto);
        msg = "Produto incluido com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        request.getSession().setAttribute("selectedProduto", selectedProduto);  
        return navegar;
    }
    
    public String produtoView() throws SQLException {
        String navegar = "/pages/cadastro/produtoView";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedProduto", selectedProduto);  
        return navegar;
    }
    
    public String produtoEdit() throws SQLException {
        String navegar = "/pages/cadastro/produtoEdit";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedProduto", selectedProduto);  
        return navegar;
    }
    
    public String alteraProduto() throws SQLException {
        String navegar = "/pages/cadastro/produtos";
        selectedProduto.setPubEmbalagem(embDAO.getById(embalagem));
        try {
            prodDAO.atualizarProduto(selectedProduto);
            msg = "Produto alterado com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            Logger.getLogger(CadProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "Registro não alterado!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return navegar;
    }
    
    
    public void fornecedorNew() throws SQLException {
        fornecedor = 0;
        selectedRelacionamento = new CadRelProdFornec();
        selectedRelacionamento.setPubEmpresa(usu.getPubEmpresa());
        selectedRelacionamento.setCadProduto(selectedProduto);
        listFornecedores = pesDAO.listFornecedoresByEmpresa(usu.getPubEmpresa(), filtro);
    }
    
    public void incluiFornecedor() throws SQLException {
        selectedRelacionamento.setCadPessoa(pesDAO.getById(fornecedor));
        // verifica inclusão ou alteração de fornecedor
        if (selectedRelacionamento.getIdRel() == null) {
            relDAO.inserirRelacionamento(selectedRelacionamento);
            msg = "Relacionamento incluido com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            relDAO.atualizarRelacionamento(selectedRelacionamento);
            msg = "Relacionamento alterado com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        // recaregar lista de endereços
        listRelProdFornec  = relDAO.listRelacionamentoByProduto(selectedProduto);
    }
    
    public void setParametrosFornecedor(CadRelProdFornec rel) throws SQLException {
        selectedRelacionamento = rel;
        fornecedor = rel.getCadPessoa().getIdPessoa();
    }
    

    
    // change filtro
    public void changeFiltro() throws SQLException {
        listProdutos = prodDAO.listProdutosByEmpresa(usu.getPubEmpresa(), filtro);
    }

    
    /* getters & setters
    */
    public List<CadProduto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<CadProduto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    public List<PubEmbalagem> getListEmbalagem() {
        return listEmbalagem;
    }

    public void setListEmbalagem(List<PubEmbalagem> listEmbalagem) {
        this.listEmbalagem = listEmbalagem;
    }

    public CadProduto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(CadProduto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public int getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(int embalagem) {
        this.embalagem = embalagem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<CadRelProdFornec> getListRelProdFornec() {
        return listRelProdFornec;
    }

    public void setListRelProdFornec(List<CadRelProdFornec> listRelProdFornec) {
        this.listRelProdFornec = listRelProdFornec;
    }

    public CadRelProdFornec getSelectedRelacionamento() {
        return selectedRelacionamento;
    }

    public void setSelectedRelacionamento(CadRelProdFornec selectedRelacionamento) {
        this.selectedRelacionamento = selectedRelacionamento;
    }

    public List<CadPessoa> getListFornecedores() {
        return listFornecedores;
    }

    public void setListFornecedores(List<CadPessoa> listFornecedores) {
        this.listFornecedores = listFornecedores;
    }

    public int getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(int fornecedor) {
        this.fornecedor = fornecedor;
    }

    
}
