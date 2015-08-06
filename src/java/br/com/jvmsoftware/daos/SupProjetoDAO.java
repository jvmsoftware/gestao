/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.SupProjeto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class SupProjetoDAO extends DefaultDAO {
    
    public SupProjeto getById(int Id) throws SQLException {
        getSession();
        begin();
        SupProjeto projeto;
        projeto = (SupProjeto) session.get(SupProjeto.class, Id);
        return projeto;
    }
    
    public List<SupProjeto> listProjetosByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<SupProjeto> projetos;
        projetos = session.createQuery("from SupProjeto u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return projetos;
    }
    
    public void inserirProjeto (SupProjeto projeto) throws SQLException {
        getSession();
        begin();
        session.save(projeto);
        commit();
        closeSession();
    }
    
    public void updateProjeto (SupProjeto projeto) throws SQLException {
        getSession();
        begin();
        session.merge(projeto);
        commit();
        closeSession();
    }
    
}
