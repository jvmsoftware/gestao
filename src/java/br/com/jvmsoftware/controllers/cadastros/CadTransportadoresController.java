/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.CadEnderecosDAO;
import br.com.jvmsoftware.daos.CadPessoaDAO;
import br.com.jvmsoftware.daos.PubEstadoDAO;
import br.com.jvmsoftware.daos.PubMunicipioDAO;
import br.com.jvmsoftware.daos.PubTipoCadastroDAO;
import br.com.jvmsoftware.daos.PubTipoEnderecoDAO;
import br.com.jvmsoftware.entities.CadEnderecos;
import br.com.jvmsoftware.entities.CadPessoa;
import br.com.jvmsoftware.entities.PubEstado;
import br.com.jvmsoftware.entities.PubMunicipio;
import br.com.jvmsoftware.entities.PubTipoCadastro;
import br.com.jvmsoftware.entities.PubTipoEndereco;
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
public class CadTransportadoresController implements Serializable{
    
    private final CadPessoaDAO pesDAO = new CadPessoaDAO();
    private final PubTipoCadastroDAO tCadDAO = new PubTipoCadastroDAO();
    private final PubEstadoDAO estDAO = new PubEstadoDAO();
    private final PubMunicipioDAO municDAO = new PubMunicipioDAO();
    private final CadEnderecosDAO enderDAO = new CadEnderecosDAO();
    private final PubTipoEnderecoDAO eEnderDAO = new PubTipoEnderecoDAO();
    private List<CadPessoa> listTransportadores;
    private List<PubTipoCadastro> listTipoCadastro;
    private List<PubTipoEndereco> listTipoEndereco;
    private List<PubEstado> listEstados;
    private List<PubMunicipio> listMunicipios;
    private List<CadEnderecos> listEnderecos;
    private PubUsuario usu;
    private CadPessoa selectedTransportador;
    private CadEnderecos selectedEndereco;
    private int tipoCadastro = 0;
    private int tipoEndereco = 0;
    private int estado = 0;
    private int municipio = 0;
    private int pessoa = 0;
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
        selectedTransportador = (CadPessoa) request.getSession().getAttribute("selectedTransportador");
        if (selectedTransportador != null) {
            try {
                if (selectedEndereco == null) {
                    selectedEndereco = new CadEnderecos();
                }
                listEnderecos  = enderDAO.listEnderecosByPessoa(selectedTransportador);
            } catch (SQLException ex) {
                Logger.getLogger(CadTransportadoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (selectedTransportador.getPubTipoCadastro() != null) {
                tipoCadastro = selectedTransportador.getPubTipoCadastro().getIdTipoCadastro();
                tipoCadastroChange();
            }
        }
        try {
            listTipoCadastro = tCadDAO.listAllTipoCadastros();
            listTransportadores = pesDAO.listTransportadoresByEmpresa(usu.getPubEmpresa(), filtro);
            listEstados = estDAO.listAllEstados();
            listTipoEndereco = eEnderDAO.listTipoEndereco();
            if (estado != 0) {
                listMunicipios = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadTransportadoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String transportadores() {
        String navegar = "/pages/cadastro/transportadores";
        return navegar;
    }

    public String transportadorNew() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/transportadorNew";
        listTipoCadastro = tCadDAO.listAllTipoCadastros();
        selectedTransportador = new CadPessoa();
        selectedTransportador.setPubEmpresa(usu.getPubEmpresa());
        selectedTransportador.setCliente(false);
        selectedTransportador.setFornecedor(false);
        selectedTransportador.setFuncionario(false);
        selectedTransportador.setTransportador(true);
        request.getSession().setAttribute("selectedTransportador", selectedTransportador);  
        return navegar;
    }
    
    public String insereTransportador() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String navegar = "/pages/cadastro/transportadorEdit";
        selectedTransportador.setPubTipoCadastro(tCadDAO.getById(tipoCadastro));
        selectedTransportador.setDataCadastro(new Date());
        pesDAO.inserirPessoa(selectedTransportador);
        msg = "Transportador incluido com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        request.getSession().setAttribute("selectedTransportador", selectedTransportador);  
        return navegar;
    }
    
    public String transportadorView() throws SQLException {
        String navegar = "/pages/cadastro/transportadorView";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedTransportador", selectedTransportador);  
        return navegar;
    }
    
    public String transportadorEdit() throws SQLException {
        String navegar = "/pages/cadastro/transportadorEdit";
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedTransportador", selectedTransportador);  
        return navegar;
    }
    
    public String alteraTransportador() {
        String navegar = "/pages/cadastro/transportadores";
        try {
            pesDAO.updatePessoa(selectedTransportador);
            msg = "Transportador alterado com sucesso.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            Logger.getLogger(CadTransportadoresController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "Registro não alterado!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return navegar;
    }

    public void enderecoNew() {
        selectedEndereco = new CadEnderecos();
    }
    
    public void incluiEndereco() throws SQLException {
        selectedEndereco.setCadPessoa(selectedTransportador);
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
        if (selectedTransportador.getCadEnderecos() == null) {
            selectedTransportador.setCadEnderecos(selectedEndereco);
            pesDAO.updatePessoa(selectedTransportador);
            msg = "Endereço padrão definido.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        // recaregar lista de endereços
        listEnderecos  = enderDAO.listEnderecosByPessoa(selectedTransportador);
    }
    
    public void setParametrosEndereco(CadEnderecos end) throws SQLException {
        estado = end.getPubEstado().getIdEstado();
        listMunicipios = municDAO.listMunicipiosByEstado(end.getPubEstado());
        municipio = end.getPubMunicipio().getIdMunicipio();
        tipoEndereco = end.getPubTipoEndereco().getId();
        selectedEndereco = end;
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
        listTransportadores = pesDAO.listTransportadoresByEmpresa(usu.getPubEmpresa(), filtro);
    }
    
    /* getters & setters
    */
    public List<CadPessoa> getListTransportadores() {
        return listTransportadores;
    }

    public void setListTransportadores(List<CadPessoa> listTransportadores) {
        this.listTransportadores = listTransportadores;
    }

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    public CadPessoa getSelectedTransportador() {
        return selectedTransportador;
    }

    public void setSelectedTransportador(CadPessoa selectedTransportador) {
        this.selectedTransportador = selectedTransportador;
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

}