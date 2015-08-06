/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsUsuarioSistema;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubSistema;
import br.com.jvmsoftware.entities.PubUsuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class AcsUsuarioSistemaDAO extends DefaultDAO {
    
    public AcsUsuarioSistema getById(int Id) throws SQLException {
        getSession();
        begin();
        AcsUsuarioSistema usuarioSistema;
        usuarioSistema = (AcsUsuarioSistema) session.get(AcsUsuarioSistema.class, Id);
        return usuarioSistema;
    }
    
    public List<AcsUsuarioSistema> listAllTipoNegocios() throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioSistema> usuarioSistemas;
        usuarioSistemas = session.createQuery("from AcsUsuarioSistema").list();
        return usuarioSistemas;
    }
    
    public List<AcsUsuarioSistema> listUsuarioSistemaByUsuario(PubUsuario usuario) throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioSistema> usuarioSistemas;
        usuarioSistemas = session.createQuery("from AcsUsuarioSistema u where u.usuario = :usu").setParameter("usu", usuario.getIdUsuario()).list();
        return usuarioSistemas;
    }
    
    public List<AcsUsuarioSistema> listUsuarioSistemaByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioSistema> usuarioSistemas;
        usuarioSistemas = session.createQuery("from AcsUsuarioSistema u where u.empresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return usuarioSistemas;
    }
    
    public List<AcsUsuarioSistema> listUsuarioSistemaBySistema(PubSistema sistema) throws SQLException {
        getSession();
        begin();
        List<AcsUsuarioSistema> usuarioSistemas;
        usuarioSistemas = session.createQuery("from AcsUsuarioSistema u where u.sistema = :sis").setParameter("sis", sistema.getIdSistema()).list();
        return usuarioSistemas;
    }
    
    public void inserirUsuarioSistema (AcsUsuarioSistema usuarioSistema) throws SQLException {
        getSession();
        begin();
        session.save(usuarioSistema);
        commit();
        closeSession();
    }
    
    public void atualizarUsuarioSistema (AcsUsuarioSistema usuarioSistema) throws SQLException {
        getSession();
        begin();
        session.merge(usuarioSistema);
        commit();
        closeSession();
    }
    
}
