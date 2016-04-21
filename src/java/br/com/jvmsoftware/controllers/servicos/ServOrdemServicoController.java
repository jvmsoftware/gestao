/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.servicos;

import br.com.jvmsoftware.daos.CadEquipamentosDAO;
import br.com.jvmsoftware.daos.CadImoveisDAO;
import br.com.jvmsoftware.daos.CadPessoaDAO;
import br.com.jvmsoftware.daos.CadVeiculosDAO;
import br.com.jvmsoftware.daos.ServItemOrdemServicoDAO;
import br.com.jvmsoftware.daos.ServOrdemServicoDAO;
import br.com.jvmsoftware.entities.CadEquipamentos;
import br.com.jvmsoftware.entities.CadImoveis;
import br.com.jvmsoftware.entities.CadPessoa;
import br.com.jvmsoftware.entities.CadVeiculos;
import br.com.jvmsoftware.entities.PubUsuario;
import br.com.jvmsoftware.entities.ServItemOrdemServico;
import br.com.jvmsoftware.entities.ServOrdemServico;
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
public class ServOrdemServicoController {

    private final ServOrdemServicoDAO osDAO = new ServOrdemServicoDAO();
    private final ServItemOrdemServicoDAO iosDAO = new ServItemOrdemServicoDAO();
    private final CadPessoaDAO pesDAO = new CadPessoaDAO();
    private final CadVeiculosDAO veiDAO = new CadVeiculosDAO();
    private final CadEquipamentosDAO equDAO = new CadEquipamentosDAO();
    private final CadImoveisDAO imoDAO = new CadImoveisDAO();
    private List<ServOrdemServico> listOrdensServico;
    private List<ServItemOrdemServico> listItensOrdemServico;
    private List<CadPessoa> listClientes;
    private List<CadVeiculos> listVeiculos;
    private List<CadEquipamentos> listEquipamentos;
    private List<CadImoveis> listImoveis;
    private ServOrdemServico selectedOrdemServico;
    private ServItemOrdemServico selectedItensOrdemServico;
    private CadPessoa selectedCliente;
    private int cliente;
    private int veiculo;
    private int equipamento;
    private int imovel;
    private PubUsuario usu;
    private String msg;
    private String filtro;
    
    /**
     * Creates a new instance of ServOrdemServico
     */
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario) request.getSession().getAttribute("usuario");
        selectedOrdemServico = (ServOrdemServico) request.getSession().getAttribute("selectedOrdemServico");
        try {
            listOrdensServico = osDAO.listOrdemServicosByEmpresa(usu.getPubEmpresa(), null);
            listClientes = pesDAO.listClientesByEmpresa(usu.getPubEmpresa(), "");
            if (selectedOrdemServico != null) {
                listItensOrdemServico  = iosDAO.listItemOrdemServicosByOrdemServico(selectedOrdemServico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServOrdemServicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/* Regras de navegação
    */
    
    public String ordemServicos() {
        String navegar = "/pages/servicos/servicos";
        return navegar;
    }

    public String ordemServicoNew() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/servicos/servicoNew";
        selectedOrdemServico = new ServOrdemServico();
        selectedOrdemServico.setPubUsuario(usu);
        request.getSession().setAttribute("selectedOrdemServico", selectedOrdemServico);  
        return navegar;
    }
    
    public String insereOrdemServico() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/servicos/servicoEdit";
        /*
        selectedOrdemServico.setPubEmbalagem(embDAO.getById(embalagem));
        selectedOrdemServico.setAtivo(true);
        prodDAO.inserirOrdemServico(selectedOrdemServico);
        msg = "OrdemServico incluido com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        request.getSession().setAttribute("selectedOrdemServico", selectedOrdemServico);  
                */
        return navegar;
    }
    
    public String ordemServicoView() throws SQLException {
        String navegar = "/pages/servicos/servicoView";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedOrdemServico", selectedOrdemServico);  
        return navegar;
    }
    
    public String ordemServicoEdit() throws SQLException {
        String navegar = "/pages/servicos/servicoEdit";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedOrdemServico", selectedOrdemServico);  
        return navegar;
    }
    
    public String alteraOrdemServico() throws SQLException {
        String navegar = "/pages/servicos/servicos";
        /*
        selectedOrdemServico.setPubEmbalagem(embDAO.getById(embalagem));
        try {
            prodDAO.atualizarOrdemServico(selectedOrdemServico);
            msg = "OrdemServico alterado com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            Logger.getLogger(CadOrdemServicoController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "Registro não alterado!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
                */
        return navegar;
    }
    
    
    public void itemNew() throws SQLException {
        /*
        item = 0;
        selectedRelacionamento = new CadRelProdFornec();
        selectedRelacionamento.setPubEmpresa(usu.getPubEmpresa());
        selectedRelacionamento.setCadOrdemServico(selectedOrdemServico);
        listItemes = pesDAO.listItemesByEmpresa(usu.getPubEmpresa(), filtro);
                */
    }
    
    public void incluiItem() throws SQLException {
        /*
        selectedRelacionamento.setCadPessoa(pesDAO.getById(item));
        // verifica inclusão ou alteração de item
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
        listRelProdFornec  = relDAO.listRelacionamentoByOrdemServico(selectedOrdemServico);
                */
    }
    
    public void setParametrosItem(ServItemOrdemServico rel) throws SQLException {
        /*
        selectedRelacionamento = rel;
        item = rel.getCadPessoa().getIdPessoa();
                */
    }

    // change filtro
    public void changeFiltro() throws SQLException {
        listOrdensServico = osDAO.listOrdemServicosByEmpresa(usu.getPubEmpresa(), filtro);
    }
    
    // change cliente
    public void changeCliente() throws SQLException {
        selectedCliente = pesDAO.getById(cliente);
        listVeiculos = veiDAO.listVeiculosByPessoa(selectedCliente);
        listEquipamentos = equDAO.listEquipamentosByPessoa(selectedCliente);
        listImoveis = imoDAO.listImoveisByPessoa(selectedCliente);
    }

    public List<ServOrdemServico> getListOrdensServico() {
        return listOrdensServico;
    }

    public void setListOrdensServico(List<ServOrdemServico> listOrdensServico) {
        this.listOrdensServico = listOrdensServico;
    }

    public List<ServItemOrdemServico> getListItensOrdemServico() {
        return listItensOrdemServico;
    }

    public void setListItensOrdemServico(List<ServItemOrdemServico> listItensOrdemServico) {
        this.listItensOrdemServico = listItensOrdemServico;
    }

    public ServOrdemServico getSelectedOrdemServico() {
        return selectedOrdemServico;
    }

    public void setSelectedOrdemServico(ServOrdemServico selectedOrdemServico) {
        this.selectedOrdemServico = selectedOrdemServico;
    }

    public ServItemOrdemServico getSelectedItensOrdemServico() {
        return selectedItensOrdemServico;
    }

    public void setSelectedItensOrdemServico(ServItemOrdemServico selectedItensOrdemServico) {
        this.selectedItensOrdemServico = selectedItensOrdemServico;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<CadPessoa> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<CadPessoa> listClientes) {
        this.listClientes = listClientes;
    }

    public List<CadVeiculos> getListVeiculos() {
        return listVeiculos;
    }

    public void setListVeiculos(List<CadVeiculos> listVeiculos) {
        this.listVeiculos = listVeiculos;
    }

    public List<CadEquipamentos> getListEquipamentos() {
        return listEquipamentos;
    }

    public void setListEquipamentos(List<CadEquipamentos> listEquipamentos) {
        this.listEquipamentos = listEquipamentos;
    }

    public List<CadImoveis> getListImoveis() {
        return listImoveis;
    }

    public void setListImoveis(List<CadImoveis> listImoveis) {
        this.listImoveis = listImoveis;
    }

    public CadPessoa getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(CadPessoa selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(int veiculo) {
        this.veiculo = veiculo;
    }

    public int getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(int equipamento) {
        this.equipamento = equipamento;
    }

    public int getImovel() {
        return imovel;
    }

    public void setImovel(int imovel) {
        this.imovel = imovel;
    }
    
    
}
