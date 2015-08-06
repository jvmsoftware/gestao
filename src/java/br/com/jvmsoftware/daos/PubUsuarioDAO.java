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
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubUsuarioDAO extends DefaultDAO {
    
    public PubUsuario getById(int Id) throws SQLException {
        getSession();
        begin();
        PubUsuario usuario;
        usuario = (PubUsuario) session.get(PubUsuario.class, Id);
        return usuario;
    }
    
    public PubUsuario getUsuariosByMail(String mail) throws SQLException {
        getSession();
        begin();
        PubUsuario usuario;
        usuario = (PubUsuario) session.createQuery("from PubUsuario u where u.email = :mail").setParameter("mail", mail).uniqueResult();
        return usuario;
    }
    
    public List<PubUsuario> listAllUsuarios() throws SQLException {
        getSession();
        begin();
        List<PubUsuario> usuarios;
        usuarios = session.createQuery("from PubUsuario").list();
        return usuarios;
    }
    
    public List<PubUsuario> listUsuariosByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<PubUsuario> usuarios;
        usuarios = session.createQuery("from PubUsuario u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return usuarios;
    }
    
    public void inserirUsuario (PubUsuario usuario) throws SQLException {
        getSession();
        begin();
        session.save(usuario);
        commit();
        closeSession();
    }
    
    public void updateUsuario (PubUsuario usuario) throws SQLException {
        getSession();
        begin();
        session.merge(usuario);
        commit();
        if (usuario.getPubEmpresa() != null) {
            triggerUpdate(usuario);
        }
        closeSession();
    }
    
    public void triggerUpdate(PubUsuario usuario) throws SQLException {
        getSession();
        begin();
        AcsUsuarioSistemaDAO usrSisDAO = new AcsUsuarioSistemaDAO();
        List<PubSistema> sistemas;
        sistemas = session.createQuery("from PubSistema s "
                                     + "where not exists (from AcsUsuarioSistema r "
                                                       + "where r.pubUsuario.idUsuario = :usr)").setParameter("usr", usuario.getIdUsuario()).list();
        for (PubSistema sistema : sistemas) {
            AcsUsuarioSistema usrSis = new AcsUsuarioSistema();
            usrSis.setPubEmpresa(usuario.getPubEmpresa());
            usrSis.setPubUsuario(usuario);
            usrSis.setPubSistema(sistema);
            usrSis.setAtivo(false);
            usrSisDAO.inserirUsuarioSistema(usrSis);
        }
        closeSession();
    }
    
}
