/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsUsuarioFuncionalidade;
import br.com.jvmsoftware.entities.AcsUsuarioSistema;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubFuncionalidade;
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
            triggerSistema(usuario);
            triggerFuncionalidade(usuario);
        }
        closeSession();
    }
    
    private void triggerSistema(PubUsuario u) throws SQLException {
        AcsUsuarioSistemaDAO relEmpDAO = new AcsUsuarioSistemaDAO();
        PubSistemaDAO sisDAO = new PubSistemaDAO();
        List<PubSistema> listSistema;
        listSistema = sisDAO.listAllSistemas();
        for (PubSistema listSistema1 : listSistema) {
            AcsUsuarioSistema rel = new AcsUsuarioSistema();
            rel.setPubEmpresa(u.getPubEmpresa());
            rel.setPubUsuario(u);
            rel.setPubSistema(listSistema1);
            rel.setAtivo(false);
            relEmpDAO.inserirUsuarioSistema(rel);
        }
    }
    
    private void triggerFuncionalidade(PubUsuario u) throws SQLException {
        AcsUsuarioFuncionalidadeDAO relUsuDAO = new AcsUsuarioFuncionalidadeDAO();
        PubFuncionalidadeDAO funDAO = new PubFuncionalidadeDAO();
        List<PubFuncionalidade> listFuncionalidades;
        listFuncionalidades = funDAO.listAllFuncionalidades();
        for (PubFuncionalidade listFuncionalidades1 : listFuncionalidades) {
            AcsUsuarioFuncionalidade rel = new AcsUsuarioFuncionalidade();
            rel.setPubUsuario(u);
            rel.setPubEmpresa(u.getPubEmpresa());
            rel.setPubFuncionalidade(listFuncionalidades1);
            rel.setDesabilitado(true);
            relUsuDAO.inserirUsuarioFuncionalidade(rel);
        }
    }
    
}
