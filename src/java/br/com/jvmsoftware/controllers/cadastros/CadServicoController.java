/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.CadPessoaDAO;
import br.com.jvmsoftware.daos.CadServicoDAO;
import br.com.jvmsoftware.daos.CadRelServFornecDAO;
import br.com.jvmsoftware.daos.PubEmbalagemDAO;
import br.com.jvmsoftware.entities.CadPessoa;
import br.com.jvmsoftware.entities.CadServicos;
import br.com.jvmsoftware.entities.CadRelServFornec;
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
public class CadServicoController {

    private final CadServicoDAO servDAO = new CadServicoDAO();
    private final CadPessoaDAO pesDAO = new CadPessoaDAO();
    private final PubEmbalagemDAO embDAO = new PubEmbalagemDAO();
    private final CadRelServFornecDAO relDAO = new CadRelServFornecDAO();
    private List<CadServicos> listServicos;
    private List<PubEmbalagem> listEmbalagem;
    private List<CadRelServFornec> listRelProdFornec;
    private List<CadPessoa> listFornecedores;
    private PubUsuario usu;
    private CadServicos selectedServico;
    private CadRelServFornec selectedRelacionamento;
    private String filtro = "";
    private String msg;
    private int fornecedor;
    
    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario) request.getSession().getAttribute("usuario");
        selectedServico = (CadServicos) request.getSession().getAttribute("selectedServico");
        try {
            listServicos = servDAO.listServicosByEmpresa(usu.getPubEmpresa(), filtro);
            listFornecedores = pesDAO.listFornecedoresByEmpresa(usu.getPubEmpresa(), filtro);
            if (selectedServico != null) {
                if (selectedRelacionamento == null) {
                    selectedRelacionamento = new CadRelServFornec();
                }
                listRelProdFornec  = relDAO.listRelacionamentoByServicos(selectedServico);
                listEmbalagem = embDAO.listAllEmbalagens();
                if (selectedRelacionamento != null) {
                    listFornecedores = pesDAO.listFornecedoresByEmpresa(usu.getPubEmpresa(), filtro);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadServicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Regras de navegação
    */
    
    public String servicos() {
        String navegar = "/pages/cadastro/servicos";
        return navegar;
    }

    public String servicoNew() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/servicoNew";
        selectedServico = new CadServicos();
        selectedServico.setPubEmpresa(usu.getPubEmpresa());
        request.getSession().setAttribute("selectedServico", selectedServico);  
        return navegar;
    }
    
    public String insereServico() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/servicoEdit";
        selectedServico.setAtivo(true);
        servDAO.inserirServico(selectedServico);
        msg = "Servico incluido com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        request.getSession().setAttribute("selectedServico", selectedServico);  
        return navegar;
    }
    
    public String servicoView() throws SQLException {
        String navegar = "/pages/cadastro/servicoView";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedServico", selectedServico);  
        return navegar;
    }
    
    public String servicoEdit() throws SQLException {
        String navegar = "/pages/cadastro/servicoEdit";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedServico", selectedServico);  
        return navegar;
    }
    
    public String alteraServico() throws SQLException {
        String navegar = "/pages/cadastro/servicos";
        try {
            servDAO.atualizarServico(selectedServico);
            msg = "Servico alterado com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            Logger.getLogger(CadServicoController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "Registro não alterado!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return navegar;
    }
    
    
    public void fornecedorNew() throws SQLException {
        fornecedor = 0;
        selectedRelacionamento = new CadRelServFornec();
        selectedRelacionamento.setPubEmpresa(usu.getPubEmpresa());
        selectedRelacionamento.setCadServicos(selectedServico);
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
        listRelProdFornec  = relDAO.listRelacionamentoByServicos(selectedServico);
    }
    
    public void setParametrosFornecedor(CadRelServFornec rel) throws SQLException {
        selectedRelacionamento = rel;
        fornecedor = rel.getCadPessoa().getIdPessoa();
    }
    

    
    // change filtro
    public void changeFiltro() throws SQLException {
        listServicos = servDAO.listServicosByEmpresa(usu.getPubEmpresa(), filtro);
    }

    
    /* getters & setters
    */
    public List<CadServicos> getListServicos() {
        return listServicos;
    }

    public void setListServicos(List<CadServicos> listServicos) {
        this.listServicos = listServicos;
    }

    public List<PubEmbalagem> getListEmbalagem() {
        return listEmbalagem;
    }

    public void setListEmbalagem(List<PubEmbalagem> listEmbalagem) {
        this.listEmbalagem = listEmbalagem;
    }

    public CadServicos getSelectedServico() {
        return selectedServico;
    }

    public void setSelectedServico(CadServicos selectedServico) {
        this.selectedServico = selectedServico;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<CadRelServFornec> getListRelProdFornec() {
        return listRelProdFornec;
    }

    public void setListRelProdFornec(List<CadRelServFornec> listRelProdFornec) {
        this.listRelProdFornec = listRelProdFornec;
    }

    public CadRelServFornec getSelectedRelacionamento() {
        return selectedRelacionamento;
    }

    public void setSelectedRelacionamento(CadRelServFornec selectedRelacionamento) {
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
