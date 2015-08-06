/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.WflWorkflow;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class WflWorkflowDAO extends DefaultDAO {
    
    public WflWorkflow getById(int Id) throws SQLException {
        getSession();
        begin();
        WflWorkflow workflow;
        workflow = (WflWorkflow) session.get(WflWorkflow.class, Id);
        return workflow;
    }
    
    public List<WflWorkflow> listProjetosByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<WflWorkflow> workflows;
        workflows = session.createQuery("from WflWorkflow u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return workflows;
    }
    
    public void inserirProjeto (WflWorkflow workflow) throws SQLException {
        getSession();
        begin();
        session.save(workflow);
        commit();
        closeSession();
    }
    
    public void updateProjeto (WflWorkflow workflow) throws SQLException {
        getSession();
        begin();
        session.merge(workflow);
        commit();
        closeSession();
    }
    
}
