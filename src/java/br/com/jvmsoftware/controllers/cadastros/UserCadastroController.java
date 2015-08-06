/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers.cadastros;

import br.com.jvmsoftware.controllers.CadastroController;
import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubEstadoDAO;
import br.com.jvmsoftware.daos.PubMunicipioDAO;
import br.com.jvmsoftware.daos.PubUsuarioDAO;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubEstado;
import br.com.jvmsoftware.entities.PubMunicipio;
import br.com.jvmsoftware.entities.PubTipoCadastro;
import br.com.jvmsoftware.entities.PubUsuario;
import br.com.jvmsoftware.util.Criptografia;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jose
 */
@ManagedBean
@ViewScoped
public class UserCadastroController implements Serializable{

    FacesMessage message = new FacesMessage();
    private final PubUsuarioDAO usuDAO = new PubUsuarioDAO();
    private final PubEstadoDAO estDAO = new PubEstadoDAO();
    private final PubMunicipioDAO municDAO = new PubMunicipioDAO();
    private PubUsuario usu = new PubUsuario();
    private List<PubEstado> listEstado;
    private List<PubMunicipio> listMunicipio;
    private int municipio = 0;
    private int estado = 0;
    private String confSenha;
    private String msg;
    private String email;
    private String senhaG;
    private String senhaN;
    
    /**
     * Creates a new instance of UserCadastroController
     */
    public UserCadastroController() {
    }
    
    
    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        if (usu != null) {
            try {
                listEstado = estDAO.listAllEstados();
                if (usu.getPubEstado() != null) {
                    estado = usu.getPubEstado().getIdEstado();
                    listMunicipio = municDAO.listMunicipiosByEstado(usu.getPubEstado());
                    if (usu.getPubMunicipio() != null) {
                        municipio = usu.getPubMunicipio().getIdMunicipio();
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(UserCadastroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (usu == null) {
            usu = new PubUsuario();
        }
    } 
    
    /** navergação
     * @return 
     */
        
    public String voltaUser() {
        String navegar = "/pages/cadastro/user";
        return navegar;
    }
    
    public String alterarSenha() {
        String navegar = "/pages/cadastro/userAlteraSenha";
        return navegar;
    }
    
        // alterar senha do usuario
    public String updateSenha() throws SQLException {
        String navegar = "/pages/cadastro/userAlteraSenha";
        if (!senhaN.equals(confSenha)) {
            msg = "Confirmação de senha não confere com a senha informada.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        else if (!usu.getSenha().equals(Criptografia.criptografar(senhaG))) {
            msg = "A senha atual não confere (o sistema diferencia letras maiusculas/minusculas)";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
        }
        else {
            usu.setSenha(Criptografia.criptografar(senhaN));
            usuDAO.updateUsuario(usu);
            msg = "Senha alterada com sucesso (será valida para seu próximo login).";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            navegar = "/pages/cadastro/user";
        }
        return navegar;
    }

    /***
     * metodos utilizados nas telas de cadastro de usuários /pages/cadastro
     */
    public void gravarUsuario() {
        try {
            if (estado != usu.getPubEstado().getIdEstado()) {
                usu.setPubEstado(estDAO.getById(estado));
            }
            if (municipio != usu.getPubMunicipio().getIdMunicipio()) {
                usu.setPubMunicipio(municDAO.getById(municipio));
            }
            usuDAO.updateUsuario(usu);
            msg = "Usuário gravado com sucesso";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            msg = "Problemas ao gravar alterações do usuário";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /** validadores
     * @param fc
     * @param uic
     * @param o
     */
    
    // valida senha alteração
    public void validateSenhaAltera(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        // busca senha e confSenha do formulário
        String senha = (String) senhaN;
        String conf = (String) o;
        // verifica senhas
        if (senha == null || conf == null) {
            message.setDetail("Preencha a senha e a confirmação da senha");
            //invoked a server warning in JSP
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(message);
        } else if (!senha.equals(conf)) {
            message.setDetail("Confirmação de senha não confere com a senha");
            //invoked a server warning in JSP
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(message);
        }
    }

    // change tipo de cadastro
    public void changeEstado() throws SQLException {
        listMunicipio = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
    }

    
    
    /**getters & setters
     * 
     * @return 
     */
    public PubUsuario getUsu() {
        return usu;
    }

    public void setUsu(PubUsuario usu) {
        this.usu = usu;
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

    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }

    public String getSenhaG() {
        return senhaG;
    }

    public void setSenhaG(String senhaG) {
        this.senhaG = senhaG;
    }

    public String getSenhaN() {
        return senhaN;
    }

    public void setSenhaN(String senhaN) {
        this.senhaN = senhaN;
    }
    
    
}
