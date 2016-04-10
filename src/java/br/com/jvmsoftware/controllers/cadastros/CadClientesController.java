/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.CadEnderecosDAO;
import br.com.jvmsoftware.daos.CadEquipamentosDAO;
import br.com.jvmsoftware.daos.CadImoveisDAO;
import br.com.jvmsoftware.daos.CadPessoaDAO;
import br.com.jvmsoftware.daos.CadVeiculosDAO;
import br.com.jvmsoftware.daos.PubEstadoDAO;
import br.com.jvmsoftware.daos.PubMarcaVeiculoDAO;
import br.com.jvmsoftware.daos.PubModeloVeiculoDAO;
import br.com.jvmsoftware.daos.PubMunicipioDAO;
import br.com.jvmsoftware.daos.PubTipoCadastroDAO;
import br.com.jvmsoftware.daos.PubTipoEnderecoDAO;
import br.com.jvmsoftware.daos.PubTipoVeiculoDAO;
import br.com.jvmsoftware.daos.PubTipoEquipamentoDAO;
import br.com.jvmsoftware.daos.PubTipoImovelDAO;
import br.com.jvmsoftware.entities.CadEnderecos;
import br.com.jvmsoftware.entities.CadEquipamentos;
import br.com.jvmsoftware.entities.CadImoveis;
import br.com.jvmsoftware.entities.CadPessoa;
import br.com.jvmsoftware.entities.CadVeiculos;
import br.com.jvmsoftware.entities.PubEstado;
import br.com.jvmsoftware.entities.PubMarcaVeiculo;
import br.com.jvmsoftware.entities.PubModeloVeiculo;
import br.com.jvmsoftware.entities.PubMunicipio;
import br.com.jvmsoftware.entities.PubTipoCadastro;
import br.com.jvmsoftware.entities.PubTipoEndereco;
import br.com.jvmsoftware.entities.PubTipoEquipamento;
import br.com.jvmsoftware.entities.PubTipoImovel;
import br.com.jvmsoftware.entities.PubTipoVeiculo;
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
    private final PubEstadoDAO estDAO = new PubEstadoDAO();
    private final PubMunicipioDAO municDAO = new PubMunicipioDAO();
    private final CadEnderecosDAO enderDAO = new CadEnderecosDAO();
    private final PubTipoEnderecoDAO eEnderDAO = new PubTipoEnderecoDAO();
    private final CadVeiculosDAO veicDAO = new CadVeiculosDAO();
    private final CadEquipamentosDAO equipDAO = new CadEquipamentosDAO();
    private final CadImoveisDAO imovDAO = new CadImoveisDAO();
    private final PubTipoVeiculoDAO tVeiDAO = new PubTipoVeiculoDAO();
    private final PubMarcaVeiculoDAO marVeiDAO = new PubMarcaVeiculoDAO();
    private final PubModeloVeiculoDAO modVeiDAO = new PubModeloVeiculoDAO();
    private final PubTipoEquipamentoDAO tEquipDAO = new PubTipoEquipamentoDAO();
    private final PubTipoImovelDAO tImovelDAO = new PubTipoImovelDAO();
    private List<CadPessoa> listClientes;
    private List<PubTipoCadastro> listTipoCadastro;
    private List<PubTipoEndereco> listTipoEndereco;
    private List<PubEstado> listEstados;
    private List<PubMunicipio> listMunicipios;
    private List<CadEnderecos> listEnderecos;
    private List<CadVeiculos> listVeiculos;
    private List<CadEquipamentos>listEquipamentos;
    private List<CadImoveis> listImoveis;
    private List<PubTipoVeiculo> listTipoVeiculo;
    private List<PubMarcaVeiculo> listMarcaVeiculo;
    private List<PubModeloVeiculo> listModeloVeiculo;
    private List<PubTipoEquipamento> listTipoEquipamento;
    private List<PubTipoImovel> listTipoImovel;
    private PubUsuario usu;
    private CadPessoa selectedCliente = new CadPessoa();
    private CadEnderecos selectedEndereco = new CadEnderecos();
    private CadVeiculos selectedVeiculo = new CadVeiculos();
    private CadEquipamentos selectedEquipamento = new CadEquipamentos();
    private CadImoveis selectedImovel = new CadImoveis();
    private int tipoCadastro = 0;
    private int tipoEndereco = 0;
    private int estado = 0;
    private int municipio = 0;
    private int pessoa = 0;
    private int tipoVeiculo = 0;
    private int marcaVeiculo = 0;
    private int modeloVeiculo = 0;
    private int tipoEquipamento = 0;
    private int tipoImovel = 0;
    private String mask = "";
    private String msg;
    private String filtro = "";
    private Boolean ok = false;
    private Boolean pf = false;
    private Boolean pj = false;

    
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario) request.getSession().getAttribute("usuario");
        selectedCliente = (CadPessoa) request.getSession().getAttribute("selectedCliente");
        if (selectedCliente != null) {
            try {
                listEnderecos  = enderDAO.listEnderecosByPessoa(selectedCliente);
                listVeiculos = veicDAO.listVeiculosByPessoa(selectedCliente);
                listEquipamentos = equipDAO.listEquipamentosByPessoa(selectedCliente);
                listImoveis = imovDAO.listImoveisByPessoa(selectedCliente);
            } catch (SQLException ex) {
                Logger.getLogger(CadClientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (selectedCliente.getPubTipoCadastro() != null) {
                tipoCadastro = selectedCliente.getPubTipoCadastro().getIdTipoCadastro();
                tipoCadastroChange();
            }
        }
        try {
            listTipoCadastro = tCadDAO.listAllTipoCadastros();
            listClientes = pesDAO.listClientesByEmpresa(usu.getPubEmpresa(), filtro);
            listEstados = estDAO.listAllEstados();
            listTipoEndereco = eEnderDAO.listTipoEndereco();
            listTipoVeiculo = tVeiDAO.listTipoVeiculo();
            listTipoEquipamento = tEquipDAO.listTipoEquipamento();
            listTipoImovel = tImovelDAO.listTipoImovel();
            if (estado != 0) {
                listMunicipios = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
            }
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
        selectedCliente.setTransportador(false);
        request.getSession().setAttribute("selectedCliente", selectedCliente);  
        return navegar;
    }
    
    public String insereCliente() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/clienteEdit";
        selectedCliente.setPubTipoCadastro(tCadDAO.getById(tipoCadastro));
        selectedCliente.setDataCadastro(new Date());
        pesDAO.inserirPessoa(selectedCliente);
        msg = "Cliente incluido com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        request.getSession().setAttribute("selectedCliente", selectedCliente);  
        return navegar;
    }
    
    public String clienteView() throws SQLException {
        String navegar = "/pages/cadastro/clienteView";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedCliente", selectedCliente);  
        return navegar;
    }
    
    public String clienteEdit() throws SQLException {
        String navegar = "/pages/cadastro/clienteEdit";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedCliente", selectedCliente);  
        return navegar;
    }
    
    public String alteraCliente() {
        String navegar = "/pages/cadastro/clientes";
        try {
            pesDAO.updatePessoa(selectedCliente);
            msg = "Cliente alterado com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            Logger.getLogger(CadClientesController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "Registro não alterado!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return navegar;
    }

    public void enderecoNew() {
        selectedEndereco = new CadEnderecos();
    }
    
    public void incluiEndereco() throws SQLException {
        selectedEndereco.setCadPessoa(selectedCliente);
        selectedEndereco.setPubEstado(estDAO.getById(estado));
        selectedEndereco.setPubMunicipio(municDAO.getById(municipio));
        selectedEndereco.setPubTipoEndereco(eEnderDAO.getById(tipoEndereco));
        // verifica inclusão ou alteração de endereço
        if (selectedEndereco.getIdEndereco() == null) {
            enderDAO.inserirEndereco(selectedEndereco);
            msg = "Endereço incluido com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            enderDAO.updateEndereco(selectedEndereco);
            msg = "Endereço alterado com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        // verifica endereço padrão
        if (selectedCliente.getCadEnderecos() == null) {
            selectedCliente.setCadEnderecos(selectedEndereco);
            pesDAO.updatePessoa(selectedCliente);
            msg = "Endereço padrão definido.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        // recaregar lista de endereços
        listEnderecos  = enderDAO.listEnderecosByPessoa(selectedCliente);
    }
    
    public void setParametrosEndereco(CadEnderecos end) throws SQLException {
        estado = end.getPubEstado().getIdEstado();
        listMunicipios = municDAO.listMunicipiosByEstado(end.getPubEstado());
        municipio = end.getPubMunicipio().getIdMunicipio();
        tipoEndereco = end.getPubTipoEndereco().getId();
        selectedEndereco = end;
    }
    
    public void veiculoNew() {
        selectedVeiculo = new CadVeiculos();
    }
    
    public void incluiVeiculo() throws SQLException {
        selectedVeiculo.setPubEmpresa(usu.getPubEmpresa());
        selectedVeiculo.setCadPessoa(selectedCliente);
        selectedVeiculo.setPubTipoVeiculo(tVeiDAO.getById(tipoVeiculo));
        selectedVeiculo.setPubMarcaVeiculo(marVeiDAO.getById(marcaVeiculo));
        selectedVeiculo.setPubModeloVeiculo(modVeiDAO.getById(modeloVeiculo));
        // verifica inclusão ou alteração de endereço
        if (selectedVeiculo.getIdVeiculo() == null) {
            veicDAO.inserirVeiculo(selectedVeiculo);
            msg = "Veiculo incluido com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            veicDAO.updateVeiculo(selectedVeiculo);
            msg = "Veiculo alterado com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        listVeiculos = veicDAO.listVeiculosByPessoa(selectedCliente);
    }
    
    public void setParametrosVeiculo(CadVeiculos vei) throws SQLException {
        tipoVeiculo = vei.getPubTipoVeiculo().getIdTipoVeiculo();
        marcaVeiculo = vei.getPubMarcaVeiculo().getIdMarcaVeiculo();
        changeTipoVeiculo();
        changeMarca();
        modeloVeiculo = vei.getPubModeloVeiculo().getIdModeloVeiculo();
        selectedVeiculo = vei;
    }
    
    public void equipamentoNew() {
        selectedEquipamento = new CadEquipamentos();
    }
    
    public void incluiEquipamento() throws SQLException {
        selectedEquipamento.setPubEmpresa(usu.getPubEmpresa());
        selectedEquipamento.setCadPessoa(selectedCliente);
        selectedEquipamento.setPubTipoEquipamento(tEquipDAO.getById(tipoEquipamento));
        // verifica inclusão ou alteração de endereço
        if (selectedEquipamento.getIdEquipamento() == null) {
            equipDAO.inserirEquipamento(selectedEquipamento);
            msg = "Equipamento incluido com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            equipDAO.updateEquipamento(selectedEquipamento);
            msg = "Equipamento alterado com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        listEquipamentos = equipDAO.listEquipamentosByPessoa(selectedCliente);
    }
    
    public void setParametrosEquipamento(CadEquipamentos eqp) throws SQLException {
        tipoEquipamento = eqp.getPubTipoEquipamento().getIdTipoEquipamento();
        selectedEquipamento = eqp;
    }
    
    public void imovelNew() {
        selectedImovel = new CadImoveis();
    }
    
    public void incluiImovel() throws SQLException {
        selectedImovel.setPubEmpresa(usu.getPubEmpresa());
        selectedImovel.setCadPessoa(selectedCliente);
        selectedImovel.setPubTipoImovel(tImovelDAO.getById(tipoImovel));
        // verifica inclusão ou alteração de endereço
        if (selectedImovel.getIdImovel() == null) {
            imovDAO.inserirImovel(selectedImovel);
            msg = "Imovel incluido com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            imovDAO.updateImovel(selectedImovel);
            msg = "Imovel alterado com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        listImoveis = imovDAO.listImoveisByPessoa(selectedCliente);
    }
    
    public void setParametrosImovel(CadImoveis imo) throws SQLException {
        tipoImovel = imo.getPubTipoImovel().getIdTipoImovel();
        selectedImovel = imo;
    }
    
    public void tipoCadastroChange() {
        if (tipoCadastro == 1) {
            ok = true;
            mask = "999.999.999-9?9";
            pf = true;
            pj = false;
        }
        else if (tipoCadastro == 2) {
            ok = true;
            mask = "99.999.999/9999-99";
            pf = false;
            pj = true;
        }
    }

    // change estado
    public void changeEstado() throws SQLException {
        listMunicipios = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
    }
    
    // change filtro
    public void changeFiltro() throws SQLException {
        listClientes = pesDAO.listClientesByEmpresa(usu.getPubEmpresa(), filtro);
    }
    
    // change Marca Veiculo
    public void changeMarca() throws SQLException {
        listModeloVeiculo = modVeiDAO.listModeloVeiculoByMarca(marVeiDAO.getById(marcaVeiculo));
    }
    
    // change Tipo Veiculo
    public void changeTipoVeiculo() throws SQLException {
        listMarcaVeiculo = marVeiDAO.listMarcaVeiculoByTipo(tVeiDAO.getById(tipoVeiculo));
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

    public Boolean getPf() {
        return pf;
    }

    public void setPf(Boolean pf) {
        this.pf = pf;
    }

    public Boolean getPj() {
        return pj;
    }

    public void setPj(Boolean pj) {
        this.pj = pj;
    }

    public List<PubEstado> getListEstados() {
        return listEstados;
    }

    public void setListEstados(List<PubEstado> listEstados) {
        this.listEstados = listEstados;
    }

    public List<PubMunicipio> getListMunicipios() {
        return listMunicipios;
    }

    public void setListMunicipios(List<PubMunicipio> listMunicipios) {
        this.listMunicipios = listMunicipios;
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

    public List<CadEnderecos> getListEnderecos() {
        return listEnderecos;
    }

    public void setListEnderecos(List<CadEnderecos> listEnderecos) {
        this.listEnderecos = listEnderecos;
    }

    public CadEnderecos getSelectedEndereco() {
        return selectedEndereco;
    }

    public void setSelectedEndereco(CadEnderecos selectedEndereco) {
        this.selectedEndereco = selectedEndereco;
    }

    public List<PubTipoEndereco> getListTipoEndereco() {
        return listTipoEndereco;
    }

    public void setListTipoEndereco(List<PubTipoEndereco> listTipoEndereco) {
        this.listTipoEndereco = listTipoEndereco;
    }

    public int getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(int tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
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

    public CadVeiculos getSelectedVeiculo() {
        return selectedVeiculo;
    }

    public void setSelectedVeiculo(CadVeiculos selectedVeiculo) {
        this.selectedVeiculo = selectedVeiculo;
    }

    public CadImoveis getSelectedImoveis() {
        return selectedImovel;
    }

    public void setSelectedImoveis(CadImoveis selectedImoveis) {
        this.selectedImovel = selectedImoveis;
    }

    public List<PubTipoVeiculo> getListTipoVeiculo() {
        return listTipoVeiculo;
    }

    public void setListTipoVeiculo(List<PubTipoVeiculo> listTipoVeiculo) {
        this.listTipoVeiculo = listTipoVeiculo;
    }

    public List<PubMarcaVeiculo> getListMarcaVeiculo() {
        return listMarcaVeiculo;
    }

    public void setListMarcaVeiculo(List<PubMarcaVeiculo> listMarcaVeiculo) {
        this.listMarcaVeiculo = listMarcaVeiculo;
    }

    public List<PubModeloVeiculo> getListModeloVeiculo() {
        return listModeloVeiculo;
    }

    public void setListModeloVeiculo(List<PubModeloVeiculo> listModeloVeiculo) {
        this.listModeloVeiculo = listModeloVeiculo;
    }

    public CadEquipamentos getSelectedEquipamento() {
        return selectedEquipamento;
    }

    public void setSelectedEquipamento(CadEquipamentos selectedEquipamento) {
        this.selectedEquipamento = selectedEquipamento;
    }

    public CadImoveis getSelectedImovel() {
        return selectedImovel;
    }

    public void setSelectedImovel(CadImoveis selectedImovel) {
        this.selectedImovel = selectedImovel;
    }

    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(int tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public int getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(int marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

    public int getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(int modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public int getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(int tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public List<PubTipoEquipamento> getListTipoEquipamento() {
        return listTipoEquipamento;
    }

    public void setListTipoEquipamento(List<PubTipoEquipamento> listTipoEquipamento) {
        this.listTipoEquipamento = listTipoEquipamento;
    }

    public List<PubTipoImovel> getListTipoImovel() {
        return listTipoImovel;
    }

    public void setListTipoImovel(List<PubTipoImovel> listTipoImovel) {
        this.listTipoImovel = listTipoImovel;
    }

    public int getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(int tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

}