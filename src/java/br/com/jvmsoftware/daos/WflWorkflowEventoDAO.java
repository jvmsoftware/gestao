/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.WflWorkflowEvento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class WflWorkflowEventoDAO extends DefaultDAO {
    
    public WflWorkflowEvento getById(int Id) throws SQLException {
        getSession();
        begin();
        WflWorkflowEvento workflowEvento;
        workflowEvento = (WflWorkflowEvento) session.get(WflWorkflowEvento.class, Id);
        return workflowEvento;
    }
    
    public List<WflWorkflowEvento> listProjetosByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<WflWorkflowEvento> workflowEventos;
        workflowEventos = session.createQuery("from WflWorkflowEvento u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return workflowEventos;
    }
    
    public void inserirProjeto (WflWorkflowEvento workflowEvento) throws SQLException {
        getSession();
        begin();
        session.save(workflowEvento);
        commit();
        closeSession();
    }
    
    public void updateProjeto (WflWorkflowEvento workflowEvento) throws SQLException {
        getSession();
        begin();
        session.merge(workflowEvento);
        commit();
        closeSession();
    }
    
}
