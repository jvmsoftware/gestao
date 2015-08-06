/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers;

import br.com.jvmsoftware.daos.PubConfigEmpresaDAO;
import br.com.jvmsoftware.daos.PubEmpresaDAO;
import br.com.jvmsoftware.daos.PubEstadoDAO;
import br.com.jvmsoftware.daos.PubMunicipioDAO;
import br.com.jvmsoftware.daos.PubTipoCadastroDAO;
import br.com.jvmsoftware.daos.PubUsuarioDAO;
import br.com.jvmsoftware.entities.PubConfigEmpresa;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubEstado;
import br.com.jvmsoftware.entities.PubMunicipio;
import br.com.jvmsoftware.entities.PubTipoCadastro;
import br.com.jvmsoftware.entities.PubUsuario;
import br.com.jvmsoftware.util.Criptografia;
import br.com.jvmsoftware.util.EnviarMail;
import br.com.jvmsoftware.util.GeraCodigoVerificacao;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
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
import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 * Controller do cadastro de usuarios pelo site
 * @author jose
 */
@ManagedBean
//@RequestScoped
//@ViewScoped
@ViewScoped
public final class CadastroController implements Serializable{
    private final PubUsuarioDAO usuDAO = new PubUsuarioDAO();
    private final PubEmpresaDAO empDAO = new PubEmpresaDAO();
    private final PubTipoCadastroDAO tCadDAO = new PubTipoCadastroDAO();
    private final PubEstadoDAO estDAO = new PubEstadoDAO();
    private final PubMunicipioDAO municDAO = new PubMunicipioDAO();
    private PubUsuario usu = new PubUsuario();
    private PubEmpresa emp = new PubEmpresa();
    private List<PubTipoCadastro> listTCadastro;
    private List<PubEstado> listEstado;
    private List<PubMunicipio> listMunicipio;
    FacesMessage message = new FacesMessage();
    private final int idEmpresa = 1; // constante
    private int municipio = 0;
    private int estado = 0;
    private String tipoCad = null;
    private String step = "registro";
    private String confMail;
    private String confSenha;
    private String msg;
    private String codVerif;
    private String email;
    private String senhaG;
    private String senhaN;
    private boolean segueTipo = false;
    private boolean segueEstado = false;
    private boolean segueCidade = false;
    private boolean segueTipoPF = false;
    private boolean segueTipoPJ = false;

    public CadastroController() throws SQLException {
        // setando combo tipo de cadastro
        this.listTCadastro = tCadDAO.listAllTipoCadastros();
        this.listEstado = estDAO.listAllEstados();
    }

    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
        step = (String)request.getSession().getAttribute("step");
        if (usu != null) {
            try {
                if (usu.getPubEstado() != null) {
                    estado = usu.getPubEstado().getIdEstado();
                } else {
                    estado = 0;
                }
                if (usu.getPubMunicipio() != null) {
                    listMunicipio = municDAO.listMunicipiosByEstado(usu.getPubEstado());
                    municipio = usu.getPubMunicipio().getIdMunicipio();
                } else {
                    municipio = 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (usu == null) {
            usu = new PubUsuario();
        }
        if ("validacao".equals(step) || "empresa".equals(step)) {
            RequestContext.getCurrentInstance().execute("PF('wiz').hideBackNav();");
        }
    } 
    
    
    /*** navegação
     * @return 
     * @throws java.sql.SQLException
     * @throws org.apache.commons.mail.EmailException
     * @throws java.text.ParseException
    */
    // cadastrar
    public String cadastrar() throws SQLException, EmailException, ParseException {
        String navegar;
        // cadastro ok
        if (isNovoCadastro() == true) {
            navegar = "/login";
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
            request.getSession().setAttribute("mail", usu.getEmail());  
        } 
        // email já cadastrado
        else{
            navegar = "/empresaCadastro1";
        }
        return navegar;
    }
    
    // validar cadastro
    public String validarCadastro() throws SQLException {
        String navegar;
        // verifica se o código de verificação foi digitado corretamente
        if (verificaCodigo()) {
            msg = "Validação do cadastro realizada com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            if (usu.getPubEmpresa() != null) {
                navegar = "/pages/dashboard";
            } else {
                msg = "Falta pouco. Configure a empresa para utilizar o sistema.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
                navegar = "/pages/usuarioEmpresa";
            }
        }
        else {
            msg = "Codigo de verificação inválido. Verifique seu e-mail.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            navegar = "/pages/usuarioValidacao";
        }
        return navegar;
    }
    
    // inserir empresa
    public String cadastrarEmpresa() {
        String navegar;
        if (insereEmpresa()) {
            navegar = "/pages/usuarioNegocio";
        } else {
            navegar = "/pages/usuarioEmpresa";
        }
        return navegar;
    }
    
    // validar cadastro
    public String pular() {
        String navegar = "/pages/dashboard";
        return navegar;
    }

    // redireciona para cadastro
    public void reenviarCodigo() throws EmailException, SQLException {
        enviarMailCadastro();
        msg = "Reenviamos o email com o codigo de verificação";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }
    
    // recuperação de senha
    public String recuperarSenha() throws SQLException, EmailException {
        String navegar = "/recuperaSenha";
        if (ressetSenha()) {
            navegar = "/login";
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
            request.getSession().setAttribute("mail", email);  
        }
        return navegar;
    }
    
    // validar dados de recuperação de senha
    public String validaSenhaRecuperada() throws SQLException {
        String navegar = "/recuperaSenha1";
        if (verificaSenhaRecuperada()) {
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
            request.getSession().setAttribute("usuario", usu);  
            navegar = "/alterarSenhaRecuperada";
        }
        return navegar;
    }
    
    // validar dados de recuperação de senha
    public String alteraSenhaRecuperada() throws SQLException {
        String navegar = "/alterarSenhaRecuperada";
        if (updateSenhaRecuperada()) {
            navegar = "/login";
        }
        return navegar;
    }
    
    
    
    /*** validadores
     * @param fc
     * @param uic
     * @param o
     */
    // valida senha
    public void validateSenha(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        // busca senha e confSenha do formulário
        String senha = (String) senhaG;
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

    // valida email
    public void validateMail(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        // busca senha e confSenha do formulário
        String mail = (String) usu.getEmail();
        String mailConf = (String) o;
        // verifica senhas
        if (mail == null || mailConf == null) {
            message.setDetail("Preencha o email e a confirmação de email");
            //invoked a server warning in JSP
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(message);
        } else if (!mail.equals(mailConf)) {
            message.setDetail("Confirmação de email não confere com o email informado");
            //invoked a server warning in JSP
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(message);
        }
    }

    
    
    /*** private methods ***/
    // cadastro inicial usuario
    private boolean isNovoCadastro() throws SQLException, EmailException, ParseException {
        PubUsuario usuConfere = usuDAO.getUsuariosByMail(usu.getEmail());
        if (usuConfere != null) {
            msg = "Email já cadastrado, clique em recuperar senha.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
            return false;
        } else {
            // codigo de verificação
            GeraCodigoVerificacao gera = new GeraCodigoVerificacao(); //você pode usar outras máscaras 
            // set usuario
            usu.setDataCadastro(new Date());
            usu.setSenha(Criptografia.criptografar(senhaG));
            usu.setCodigoVerificacao(gera.geraCodigo());
            usu.setDataRessetSenha(new Date());
            usu.setDataValidacaoResset(new Date());
            usu.setAtivo(true);
            usu.setMaster(true);
            usuDAO.inserirUsuario(usu);
            // enviar mail
            enviarMailCadastro();
            msg = "Cadastro realizado com sucesso.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            msg = "Enviamos um email com seu codigo de verificação.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            msg = "Faça o login e insira o código de verificação para finalizar seu cadastro.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            return true;
        }
    }
    
    // enviar email de boas vindas ao novo usuario
    private void enviarMailCadastro() throws EmailException, SQLException {
        // busca config da empresa padrão (jvmdsoftware)
        PubConfigEmpresaDAO confDAO = new PubConfigEmpresaDAO();
        PubConfigEmpresa conf = confDAO.getById(idEmpresa);
        // envia email
        EnviarMail mail = new EnviarMail();
        String mensagem = "Bem vindo ao sistema " + usu.getNomeUsuario() + "!\n \n" +
                "Seu codigo de verificação é: " + usu.getCodigoVerificacao() + ". \n" +
                "Faça o login e informe seu codigo de verificação para ativar sua conta.";
        mail.emailSimples(conf, usu, mensagem, "Bem vindo!");
    }
    
    // enviar email de recuperação de senha
    private void enviarMailRecuperaSenha(String senha) throws EmailException, SQLException {
        // busca config da empresa padrão (jvmdsoftware)
        PubConfigEmpresaDAO confDAO = new PubConfigEmpresaDAO();
        PubConfigEmpresa conf = confDAO.getById(idEmpresa);
        // envia email
        EnviarMail mail = new EnviarMail();
        String mensagem = usu.getNomeUsuario() + "!\n \n" +
                "Este é um e-mail de recuperação de senha. Sua senha foi reiniciada. \n" +
                "acesse o link http://localhost:8084/zProject/recuperaSenha1.xhtml \n \n" +
                "informe: \n" +
                "Seu email: " + usu.getEmail() + "\n" +
                "Senha: " + senha + "\n" +
                "Codigo de verificação: " + usu.getCodigoVerificacao() + "\n";
        mail.emailSimples(conf, usu, mensagem, "Recuperação de senha");
    }

    // ressetar senha do usuário
    private boolean ressetSenha() throws SQLException, EmailException {
        boolean ok = false;
        PubUsuario usuConfere = usuDAO.getUsuariosByMail(email);
        if (usuConfere == null) {
            ok = false;
            msg = "Email informado não está cadastrado. Faça seu cadastro.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
        } else {
            ok = true;
            // codigo de verificação
            GeraCodigoVerificacao gera = new GeraCodigoVerificacao(); //você pode usar outras máscaras 
            String senha = gera.geraCodigo();
            // set usuario
            usuConfere.setSenha(Criptografia.criptografar(senha));
            usuConfere.setCodigoVerificacao(gera.geraCodigo());
            usu = usuConfere;
            usu.setDataRessetSenha(new Date());
            usuDAO.updateUsuario(usu);
            // enviar mail
            enviarMailRecuperaSenha(senha);
            msg = "Sua senha foi reinicializada e enviada para seu email. Verifique seu email.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
        return ok;
    }
    
    // validar codigo de segurança
    private boolean verificaCodigo() throws SQLException {
        boolean val = false;
        if (codVerif.equals(usu.getCodigoVerificacao())) {
            usu.setDataVerificacao(new Date());
            usuDAO.updateUsuario(usu);
            msg = "Verificação concluída. Finalize sua configuração!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            val = true;
        } 
        else {
            msg = "Codigo de verificação inválido. Verifique se recebeu o email de boas vindas!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        return val;
    }
    
    // valida dados de recuperação de senha
    private boolean verificaSenhaRecuperada() throws SQLException {
        boolean val = false;
        PubUsuario usuConfere = usuDAO.getUsuariosByMail(email);
        
        if (usuConfere == null) {
            msg = "Email informado não cadastrado.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            return val;
        }
        else {
            if (!usuConfere.getSenha().equals(Criptografia.criptografar(senhaG))) {
                msg = "Senha incorreta";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
                return val;
            }
            else if (!usuConfere.getCodigoVerificacao().equals(codVerif)) {
                msg = "codigo de verificação inválido. Verifique se recebeu o email de recuperação de senha!";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
                return val;
            }
            else {
                msg = "Bem vindo a jvmdsoftware";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
                val = true;
                usu = usuConfere;
                return val;
            }
        }
    }
    
    // alterar senha recuperada
    public boolean updateSenhaRecuperada() throws SQLException {
        boolean val = false;
        if (!senhaG.equals(confSenha)) {
            msg = "Confirmação de senha não confere com a senha informada.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        else {
            usu.setSenha(Criptografia.criptografar(senhaG));
            usu.setDataValidacaoResset(new Date());
            usuDAO.updateUsuario(usu);
            msg = "Senha alterada com sucesso. Faça o login.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            val = true;
        }
        return val;
    }
    
    
    // insere empresa
    public boolean insereEmpresa() {
        boolean ok;
        try {
            // inserindo emprea
            if (tipoCad.equals("PF")) {
                emp.setRazaoSocial(usu.getNomeUsuario());
            }
            emp.setPubTipoCadastro(tCadDAO.getByCodigo(tipoCad));
            emp.setPubEstado(estDAO.getById(estado));
            emp.setPubMunicipio(municDAO.getById(municipio));
            emp.setAtivo(true);
            emp.setDataCadastro(new Date());
            empDAO.inserirEmpresa(emp);
            // atualizando usuário
            usu.setPubEmpresa(emp);
            usuDAO.updateUsuario(usu);
            ok = true;
            msg = "Empresa inserida com sucesso!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } catch (SQLException ex) {
            ok = false;
            msg = "Empresa não pode ser inserida. Contacte suporte tecnico.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    
    
    
    
    /*** tratamento de eventos
     * @param event
     * @return
     * @throws SQLException
     * @throws EmailException
     * @throws ParseException 
     */
        
    
    // change tipo de cadastro
    public void changeTipoCadastro() throws SQLException {
        if (tipoCad.equals("PF") || tipoCad.equals("PJ")) {
            listEstado = estDAO.listAllEstados();
            segueTipo = true;
            segueEstado = false;
            segueCidade = false;
            segueTipoPF = false;
            segueTipoPJ = false;
        }
    }
    
    // change tipo de cadastro
    public void changeEstado() throws SQLException {
        if (tipoCad == null & estado != 0) {
            listMunicipio = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
        }
        else if ( (tipoCad.equals("PF") || tipoCad.equals("PJ")) && estado > 0 ) {
            listMunicipio = municDAO.listMunicipiosByEstado(estDAO.getById(estado));
            segueTipo = true;
            segueEstado = true;
            segueCidade = false;
            segueTipoPF = false;
            segueTipoPJ = false;
        }
    }
    
    // change tipo de cadastro
    public void changeMunicipio() throws SQLException {
        if ( (tipoCad.equals("PF") || tipoCad.equals("PJ")) && municipio > 0 ) {
            segueTipo = true;
            segueEstado = true;
            segueCidade = true;
            if (tipoCad.equals("PF")) {
                segueTipoPF = true;
                segueTipoPJ = false;
            } else if (tipoCad.equals("PJ")) {
                segueTipoPF = false;
                segueTipoPJ = true;
            }
        }
    }

    
    
    
    /*** getters & setters
     * @return 
     */
    public PubUsuario getUsu() {
        return usu;
    }

    public void setUsu(PubUsuario usu) {
        this.usu = usu;
    }

    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
    }

    public String getConfMail() {
        return confMail;
    }

    public void setConfMail(String confMail) {
        this.confMail = confMail;
    }

    public String getCodVerif() {
        return codVerif;
    }

    public void setCodVerif(String codVerif) {
        this.codVerif = codVerif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaG() {
        return senhaG;
    }

    public void setSenhaG(String senhaG) {
        this.senhaG = senhaG;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public PubEmpresa getEmp() {
        return emp;
    }

    public void setEmp(PubEmpresa emp) {
        this.emp = emp;
    }

    public List<PubTipoCadastro> getListTCadastro() {
        return listTCadastro;
    }

    public void setListTCadastro(List<PubTipoCadastro> listTCadastro) {
        this.listTCadastro = listTCadastro;
    }

    public String getTipoCad() {
        return tipoCad;
    }

    public void setTipoCad(String tipoCad) {
        this.tipoCad = tipoCad;
    }

    public boolean isSegueTipo() {
        return segueTipo;
    }

    public void setSegueTipo(boolean segueTipo) {
        this.segueTipo = segueTipo;
    }

    public boolean isSegueEstado() {
        return segueEstado;
    }

    public void setSegueEstado(boolean segueEstado) {
        this.segueEstado = segueEstado;
    }

    public boolean isSegueCidade() {
        return segueCidade;
    }

    public void setSegueCidade(boolean segueCidade) {
        this.segueCidade = segueCidade;
    }

    public boolean isSegueTipoPF() {
        return segueTipoPF;
    }

    public void setSegueTipoPF(boolean segueTipoPF) {
        this.segueTipoPF = segueTipoPF;
    }

    public boolean isSegueTipoPJ() {
        return segueTipoPJ;
    }

    public void setSegueTipoPJ(boolean segueTipoPJ) {
        this.segueTipoPJ = segueTipoPJ;
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

    public String getSenhaN() {
        return senhaN;
    }

    public void setSenhaN(String senhaN) {
        this.senhaN = senhaN;
    }
    

}
