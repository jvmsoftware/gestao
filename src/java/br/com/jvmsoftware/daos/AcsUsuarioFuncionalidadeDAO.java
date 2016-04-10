/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsUsuarioFuncionalidade;
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
public class AcsUsuarioFuncionalidadeDAO extends DefaultDAO {
    
    public AcsUsuarioFuncionalidade getById(int Id) throws SQLException {
        getSession();
        begin();
        AcsUsuarioFuncionalidade usuarioFuncionalidade;
        usuarioFuncionalidade = (AcsUsuarioFuncionalidade) session.get(AcsUsuarioFuncionalidade.class, Id);
        return usuarioFuncionalidade;
    }
    
    public List<AcsUsuarioFuncionalidade> listAllTipoNegocios() throws SQLException {
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
