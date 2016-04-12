/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsEmpresaFuncionalidade;
import br.com.jvmsoftware.entities.AcsEmpresaSistema;
import br.com.jvmsoftware.entities.AcsUsuarioFuncionalidade;
import br.com.jvmsoftware.entities.AcsUsuarioSistema;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubFuncionalidade;
import br.com.jvmsoftware.entities.PubSistema;
import br.com.jvmsoftware.entities.PubUsuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class AcsUsuarioFuncionalidadeDAO extends DefaultDAO {
    
    public AcsUsuarioFuncionalidade getById(int Id) throws SQLException {
        getSession();
        begin();
        AcsUsuarioFuncionalidade usuarioFuncionalidade;
        usuarioFuncionalidade = (AcsUsuarioFuncionalidade) session.get(AcsUsuarioFuncionalidade.class, Id);
        return usuarioFuncionalidade;
    }
    
    // usado no metodo isDisabled
    private AcsUsuarioFuncionalidade getByUsuarioCodigoFuncionalidade(PubUsuario usuario, String codigo) throws SQLException {
        getSession();
        begin();
        PubFuncionalidadeDAO funDAO = new PubFuncionalidadeDAO();
        PubFuncionalidade func = funDAO.getByCodigo(codigo);
        AcsUsuarioFuncionalidade usuarioSistemas;
        usuarioSistemas = (AcsUsuarioFuncionalidade) session.createQuery("from AcsUsuarioFuncionalidade u where u.pubUsuario.idUsuario = :usu and u.pubFuncionalidade.idFuncionalidade = :fun").setParameter("usu", usuario.getIdUsuario()).setParameter("fun", func.getIdFuncionalidade()).uniqueResult();
        return usuarioSistemas;
    }
    
    // usado para verificar se a funcionalidade deve estar habilitada
    public boolean isDisabled(PubUsuario u, String codFuncionalidade, String crud) throws SQLException {
        // declaração de variaveis
        AcsEmpresaSistemaDAO empSisDAO = new AcsEmpresaSistemaDAO();
        AcsUsuarioSistemaDAO usuSisDAO = new AcsUsuarioSistemaDAO();
        AcsEmpresaFuncionalidadeDAO empFunDAO = new AcsEmpresaFuncionalidadeDAO();
        AcsEmpresaSistema empSis;
        AcsUsuarioSistema usuSis;
        AcsEmpresaFuncionalidade empFun;
        AcsUsuarioFuncionalidade usuFun;

        // default true
        Boolean disabled = true;
        
        // get usuarioFuncionalidade
        usuFun = getByUsuarioCodigoFuncionalidade(u, codFuncionalidade);
        // verifica sistemaEmpresa
        empSis = empSisDAO.getByEmpresaSistema(u.getPubEmpresa(), usuFun.getPubFuncionalidade().getPubSistema());
        if (empSis.isAtivo() == true) {
            // verifica usuarioSistema {
            usuSis = usuSisDAO.getUsuarioSistemaByUsuarioSistema(u, usuFun.getPubFuncionalidade().getPubSistema());
            if (usuSis.isAtivo() == true) {            
                // verifica empresaFuncionalidade
                empFun = empFunDAO.getByEmpresaFuncionalidade(u.getPubEmpresa(), usuFun.getPubFuncionalidade());
                // verifica usuarioFuncionalidade
                if ("view".equals(crud)) {
                    if (empFun.isView() == true) {
                        disabled = usuFun.getView();
                    }
                } else if ("add".equals(crud)) {
                    if (empFun.isAdd() == true) {
                        disabled = usuFun.isAdd();
                    }
                } else if ("edit".equals(crud)) {
                    if (empFun.isEdit() == true) {
                        disabled = usuFun.isEdit();
                    }
                } else if ("delete".equals(crud)) {
                    if (empFun.isDelete() == true) {
                        disabled = usuFun.isDelete();
                    }
                } else if ("process".equals(crud)) {
                    if (empFun.isProcess() == true) {
                        disabled = usuFun.isProcess();
                    }
                } 
            }
        }    
        return disabled;
        
    }
    
    public List<AcsUsuarioFuncionalidade> listUsuarioSistemaByUsuario(PubUsuario usuario) throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioFuncionalidade> usuarioSistemas;
        usuarioSistemas = session.createQuery("from AcsUsuarioSistema u where u.pubUsuario.idUsuario = :usu").setParameter("usu", usuario.getIdUsuario()).list();
        return usuarioSistemas;
    }
    
    public List<AcsUsuarioFuncionalidade> listAllUsuarioFuncionalides() throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioFuncionalidade> usuarioFuncionalidades;
        usuarioFuncionalidades = session.createQuery("from AcsUsuarioFuncionalidade").list();
        return usuarioFuncionalidades;
    }
    
    public List<AcsUsuarioFuncionalidade> listUsuarioFuncionalidadeByUsuario(PubUsuario usuario) throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioFuncionalidade> usuarioFuncionalidades;
        usuarioFuncionalidades = session.createQuery("from AcsUsuarioFuncionalidade u where u.usuario = :usu").setParameter("usu", usuario.getIdUsuario()).list();
        return usuarioFuncionalidades;
    }
    
    public List<AcsUsuarioFuncionalidade> listUsuarioFuncionalidadeByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioFuncionalidade> usuarioFuncionalidades;
        usuarioFuncionalidades = session.createQuery("from AcsUsuarioFuncionalidade u where u.empresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return usuarioFuncionalidades;
    }
    
    public List<AcsUsuarioFuncionalidade> listUsuarioFuncionalidadeBySistema(PubSistema sistema) throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioFuncionalidade> usuarioFuncionalidades;
        usuarioFuncionalidades = session.createQuery("from AcsUsuarioFuncionalidade u where u.sistema = :sis").setParameter("sis", sistema.getIdSistema()).list();
        return usuarioFuncionalidades;
    }
    
    public void inserirUsuarioFuncionalidade (AcsUsuarioFuncionalidade usuarioFuncionalidade) throws SQLException {
        getSession();
        begin();
        session.save(usuarioFuncionalidade);
        commit();
        closeSession();
    }
    
    public void atualizarUsuarioFuncionalidade (AcsUsuarioFuncionalidade usuarioFuncionalidade) throws SQLException {
        getSession();
        begin();
        session.merge(usuarioFuncionalidade);
        commit();
        closeSession();
    }
    
}
