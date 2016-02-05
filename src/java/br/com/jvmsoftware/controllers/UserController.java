/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers;

import br.com.jvmsoftware.daos.PubUsuarioDAO;
import br.com.jvmsoftware.entities.PubUsuario;
import br.com.jvmsoftware.jsfsecurity.SecurityUtil;
import br.com.jvmsoftware.util.Criptografia;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class UserController implements Serializable {
    
    private static final Logger logger = Logger.getLogger(PubUsuario.class.getName());

    private PubUsuarioDAO usuDAO = new PubUsuarioDAO();
    private PubUsuario usu;
    private String email;
    private String password;
    private String msg;

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        email = (String)request.getSession().getAttribute("mail");
    } 

    public String login() throws SQLException {
        // busca senha e confSenha do formulário
        String viewId = "/login";
        usu = usuDAO.getUsuariosByMail(email);
        // verifica usuario cadastrado
        if (usu == null) {
            msg = "email não encontrado na base de dados";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            // limpa usuario
            usu = null;
            return viewId;
        } 
        // verifica recuperação de senha
        else if (usu.getDataRessetSenha().after(usu.getDataValidacaoResset())) {
            msg = "sua senha foi reiniciada. Siga as instruções enviadas por e-mail.";
            FacesContext.getCurrentInstance().addMessage(msg, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            // limpa usuario
            usu = null;
            return viewId;
        }
        // verifica senha
        else if (!Criptografia.criptografar(password).equals(usu.getSenha())) {
            msg = "senha incorreta";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
            // limpa usuario
            usu = null;
            return viewId;
        } 
        // verifica usuario ativo
        else if (!usu.isAtivo()) {
            msg = "usuario inativo";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
            // limpa usuario
            usu = null;
            return viewId;
        } 
        // verifica empresa ativa
        else if (usu.getPubEmpresa() != null && !usu.getPubEmpresa().isAtivo()) {
            //if (!usu.getPubEmpresa().isAtivo()) {
                msg = "empresa inativa";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
                // limpa usuario
                usu = null;
                return viewId;
            //}
        }
        // usuario logado
        else {
            msg = "Bem vindo " + usu.getNomeUsuario() + ".";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            if (usu.getDataVerificacao() == null) {
                viewId = SecurityUtil.logIn("/pages/usuarioValidacao");
                // complemento a mensagem
                msg = "Informe o código de verificação enviado ao seu email para finalizar o cadastro.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
                // envio os parametros para serem recuperados pelo cadastro
                HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
                request.getSession().setAttribute("usuario", usu);  
                request.getSession().setAttribute("step", "validacao");  
                return viewId;
            } else {
                viewId = SecurityUtil.logIn("/pages/dashboard");
                HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
                request.getSession().setAttribute("usuario", usu);  
                return viewId;
            }
        }
    }

    /*
    public String authenticate() {
        String viewId = "login";
        // Aqui deve ser implementa a lógica que valida usuários e senhas...
        if (username != null && password != null && username.equals(password)) {
            // demarca uma sessão válida e retorna o próximo viewId para o JSF
            viewId = SecurityUtil.logIn("/pages/dashboard");
        }
        return viewId;
    }
    */

    public boolean isLoggedIn() {
        return SecurityUtil.isLoggedIn();
    }

    public String logout() {
        // Encerra uma sessão
        SecurityUtil.logOut();
        return "/index";
    }

    public PubUsuario getUsu() {
        return usu;
    }

    public void setUsu(PubUsuario usu) {
        this.usu = usu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
