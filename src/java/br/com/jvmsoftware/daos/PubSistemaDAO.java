/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsEmpresaSistema;
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
public class PubSistemaDAO extends DefaultDAO {
    
    public PubSistema getById(int Id) throws SQLException {
        getSession();
        begin();
        PubSistema sistema;
        sistema = (PubSistema) session.get(PubSistema.class, Id);
        closeSession();
        return sistema;
    }
    
    public List<PubSistema> listAllSistemas() throws SQLException {
        getSession();
        begin();
        List<PubSistema> sistemas;
        sistemas = session.createQuery("from PubSistema").list();
        return sistemas;
    }
    
    public void inserirSistema (PubSistema sistema) throws SQLException {
        getSession();
        begin();
        session.save(sistema);
        commit();
        triggerEmpresa(sistema);
        triggerUsuario(sistema);
        closeSession();
    }
    
    public void atualizarSistema (PubSistema sistema) throws SQLException {
        getSession();
        begin();
        session.merge(sistema);
        commit();
        closeSession();
    }

    private void triggerEmpresa(PubSistema s) throws SQLException {
        AcsEmpresaSistemaDAO relEmpDAO = new AcsEmpresaSistemaDAO();
        PubEmpresaDAO empDAO = new PubEmpresaDAO();
        List<PubEmpresa> listEmpresa;
        listEmpresa = empDAO.listAllEmpresas();
        for (PubEmpresa listEmpresa1 : listEmpresa) {
            AcsEmpresaSistema rel = new AcsEmpresaSistema();
            rel.setPubEmpresa(listEmpresa1);
            rel.setPubSistema(s);
            rel.setAtivo(false);
            relEmpDAO.inserirEmpresaSistema(rel);
        }
    }
    
    private void triggerUsuario(PubSistema s) throws SQLException {
        AcsUsuarioSistemaDAO relUsuDAO = new AcsUsuarioSistemaDAO();
        PubUsuarioDAO usuDAO = new PubUsuarioDAO();
        List<PubUsuario> listUsuario;
        listUsuario = usuDAO.listAllUsuarios();
        for (PubUsuario listUsuario1 : listUsuario) {
            AcsUsuarioSistema rel = new AcsUsuarioSistema();
            rel.setPubUsuario(listUsuario1);
            rel.setPubEmpresa(listUsuario1.getPubEmpresa());
            rel.setPubSistema(s);
            rel.setAtivo(true);
            relUsuDAO.inserirUsuarioSistema(rel);
        }
    }
        
}
