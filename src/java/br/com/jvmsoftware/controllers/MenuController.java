/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.controllers;

import br.com.jvmsoftware.entities.PubUsuario;
import javax.annotation.PostConstruct;
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
public class MenuController {
    
    private PubUsuario usu = new PubUsuario();

    /**
     * Creates a new instance of MenuController
     */
    @PostConstruct 
    public void Init(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        usu = (PubUsuario)request.getSession().getAttribute("usuario");
    } 
    
    public String index() {
        return "/index";
    }
    
    public String empresa() {
        return "/empresa";
    }
    
    public String empresaObjetivo() {
        return "/empresaObjetivo";
    }
    
    public String cadastro() {
        return "/empresaCadastro";
    }
    
    public String cadastro1() {
        return "/empresaCadastro1";
    }
    
    public String login() {
        return "/login";
    }
    
    public String recuperaSenha() {
        return "/recuperaSenha";
    }
    
}
