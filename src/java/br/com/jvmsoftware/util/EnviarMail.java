/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.util;

import br.com.jvmsoftware.entities.PubConfigEmpresa;
import br.com.jvmsoftware.entities.PubUsuario;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author jose
 */
public class EnviarMail {
    
    public void emailSimples(PubConfigEmpresa conf, PubUsuario usu, String msg, String subj) throws EmailException {
        
        Email email = new SimpleEmail();
        email.setHostName(conf.getMailEnvioSmtp());
        email.setSmtpPort(conf.getMailEnvioPorta());
        email.setAuthenticator(new DefaultAuthenticator(conf.getMailEnvio(), conf.getMailEnvioSenha()));
        email.setSSLOnConnect(true);
        email.setFrom(conf.getMailEnvio());
        email.setSubject(subj);
        email.setMsg(msg);
        email.addTo(usu.getEmail());
        email.send();
    }
    
}
