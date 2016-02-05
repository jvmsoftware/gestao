/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.daos.PubConfigEmpresaDAO;
import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubEstadoDAO;
import br.com.jvmsoftware.daos.PubMunicipioDAO;
import br.com.jvmsoftware.daos.PubUsuarioDAO;
import br.com.jvmsoftware.entities.PubConfigEmpresa;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubEstado;
import br.com.jvmsoftware.entities.PubMunicipio;
import br.com.jvmsoftware.entities.PubUsuario;
import br.com.jvmsoftware.util.Criptografia;
import br.com.jvmsoftware.util.EnviarMail;
import br.com.jvmsoftware.util.GeraCodigoVerificacao;
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
import org.apache.commons.mail.EmailException;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class AcsUsuariosController implements Serializable{
    
    private final PubEmpresaDAO empDAO = new PubEmpresaDAO();
    private final PubUsuarioDAO usuDAO = new PubUsuarioDAO();
    private final PubEstadoDAO estDAO = new PubEstadoDAO();
    private final PubMunicipioDAO municDAO = new PubMunicipioDAO();
    private List<PubEstado> listEstado;
    private List<PubMunicipio> listMunicipio;
    private int municipio = 0;
    private int estado = 0;
    private PubUsuario usu = new PubUsuario();
    private PubUsuario selectedUsuario;
    private List<PubEmpresa> listEmpresas;
    private List<PubUsuario> listUsuarios;
    private boolean renderEmpresa = false;
    private int empresa = 0;
    private String msg;
    private String textAtivaUsuario;
    
    /**
     * Creates a new instance of UsuariosCadastroController
     */
    public AcsUsuariosController() {
    }
    
    @PostConstruct 
    public void Init() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        selectedUsuario = (PubUsuario)request.getSession().getAttribute("selectedUsuario");
        if (selectedUsuario != null) {
            if (selectedUsuario.getPubEstado() != null) {
                estado = selectedUsuario.getPubEstado().getIdEstado();
                if (selectedUsuario.getPubMunicipio() != null) {
                    municipio = selectedUsuario.getPubMunicipio().getIdMunicipio();
                }
            }
        }
        renderEmpresa = renderEmpresa();
        try {
            listEmpresas = empDAO.listAllEmpresas();
            listUsuarios = usuDAO.listUsuariosByEmpresa(usu.getPubEmpresa());
            listEstado = estDAO.listAllEstados();
            if (estado != 0) {
                listMunicipio = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcsUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** navergação
    * @return 
    */
    
    public String usuarios() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedUsuario", null);  
        String navegar = "/pages/cadastro/usuarios";
        return navegar;
    }
    
    public String usuariosView() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedUsuario", selectedUsuario);  
        String navegar = "/pages/cadastro/usuariosView";
        return navegar;
    }
    
    public String usuariosEdit() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        request.getSession().setAttribute("selectedUsuario", selectedUsuario);  
        String navegar = "/pages/cadastro/usuariosEdit";
        return navegar;
    }
    
    public String usuariosNew() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        selectedUsuario = new PubUsuario();
        selectedUsuario.setPubEmpresa(usu.getPubEmpresa());
        request.getSession().setAttribute("selectedUsuario", selectedUsuario);
        String navegar = "/pages/cadastro/usuariosNew";
        return navegar;
    }

    public String alterarUsuario() {
        String navegar = "/pages/cadastro/usuarios";
        try {
            usuDAO.updateUsuario(selectedUsuario);
            msg = "cadastro do usuario alterado com sucesso";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            Logger.getLogger(AcsUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            navegar = "/pages/cadastro/usuariosEdit";
        }
        return navegar;
    }

    
    public String inativaUsuario() {
        String navegar = "/pages/cadastro/usuarios";
        try {
            if (selectedUsuario.isAtivo() == true) {
                selectedUsuario.setAtivo(false);
                msg = "usuario inativado com sucesso.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            } else {
                selectedUsuario.setAtivo(true);
                msg = "usuario ativado com sucesso.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            }
            usuDAO.updateUsuario(selectedUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(AcsUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        return navegar;
    }

    public void setTextAtivaUsuario() {
        if (selectedUsuario.isAtivo() == true) {
            textAtivaUsuario = "Você tem certeza que deseja inativar o usario " + selectedUsuario + "?";
        } else {
            textAtivaUsuario = "Você tem certeza que deseja ativar o usario " + selectedUsuario + "?";
        }
    }

    
    public String inserirUsuario() throws EmailException, SQLException {
        String navegar = "/pages/cadastro/usuarios";
        PubUsuario usuConfere = usuDAO.getUsuariosByMail(selectedUsuario.getEmail());
        if (usuConfere != null) {
            msg = "Email já cadastrado, clique em recuperar senha.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
        } else {
            try {
                // codigo de verificação
                GeraCodigoVerificacao gera = new GeraCodigoVerificacao(); //você pode usar outras máscaras 
                // set usuario
                String codigo = gera.geraCodigo();
                selectedUsuario.setDataCadastro(new Date());
                selectedUsuario.setSenha(Criptografia.criptografar(codigo));
                selectedUsuario.setCodigoVerificacao(codigo);
                selectedUsuario.setDataRessetSenha(new Date());
                selectedUsuario.setDataValidacaoResset(new Date());
                selectedUsuario.setAtivo(true);
                selectedUsuario.setMaster(false);
                usuDAO.inserirUsuario(selectedUsuario);
                // enviar mail
                enviarMailCadastro();
                msg = "Cadastro do usuario realizado com sucesso.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
                msg = "Enviamos um email com o codigo de verificação e senha para o email do usuario.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            } catch (SQLException ex) {
                msg = "problemas ao acessar o banco de dados. Contate suporte técnico.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
                Logger.getLogger(AcsUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
                navegar = "/pages/cadastro/usuariosNew";
            }
        }
        return navegar;
    }
    
     // enviar email de boas vindas ao novo usuario
    private void enviarMailCadastro() throws EmailException, SQLException {
        // busca config da empresa padrão (jvmdsoftware)
        PubConfigEmpresaDAO confDAO = new PubConfigEmpresaDAO();
        PubConfigEmpresa conf = confDAO.getById(1);
        // envia email
        EnviarMail mail = new EnviarMail();
        String mensagem = "Bem vindo ao sistema " + selectedUsuario.getNomeUsuario() + "!\n \n" +
                "Seu codigo de verificação é: " + selectedUsuario.getCodigoVerificacao() + ". \n" +
                "Sua senha de acesso é: " + selectedUsuario.getCodigoVerificacao() + ". \n" +
                "Faça o login e informe seu codigo de verificação para ativar sua conta.";
        mail.emailSimples(conf, selectedUsuario, mensagem, "Bem vindo!");
    }
    
    // change empresa
    public void changeEmpresa() throws SQLException {
        if (empresa == 0) {
            listUsuarios = usuDAO.listUsuariosByEmpresa(usu.getPubEmpresa());
        } else {
            listUsuarios = usuDAO.listUsuariosByEmpresa(empDAO.getById(empresa));
        }
    }
    
    // change tipo de cadastro
    public void changeEstado() throws SQLException {
        listMunicipio = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
    }

    
    private boolean renderEmpresa() {
        boolean rend = false;
        if (usu.getPubEmpresa().getIdEmpresa() == 1) {
            rend = true;
        }
        return rend;
    }

    
    /**getters & setters
     * 
     * @return 
     */
    public List<PubEmpresa> getListEmpresas() {
        return listEmpresas;
    }

    public void setListEmpresas(List<PubEmpresa> listEmpresas) {
        this.listEmpresas = listEmpresas;
    }

    public PubUsuario getUsu() {
        return usu;
    }

    public void setUsu(PubUsuario usu) {
        this.usu = usu;
    }

    public boolean isRenderEmpresa() {
        return renderEmpresa;
    }

    public void setRenderEmpresa(boolean renderEmpresa) {
        this.renderEmpresa = renderEmpresa;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public List<PubUsuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<PubUsuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public PubUsuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(PubUsuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<PubEstado> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<PubEstado> listEstado) {
        this.listEstado = listEstado;
    }

    public List<PubMunicipio> getListMunicipio() {
        return listMunicipio;
    }

    public void setListMunicipio(List<PubMunicipio> listMunicipio) {
        this.listMunicipio = listMunicipio;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTextAtivaUsuario() {
        return textAtivaUsuario;
    }

    public void setTextAtivaUsuario(String textAtivaUsuario) {
        this.textAtivaUsuario = textAtivaUsuario;
    }
    
    
    
    
}
