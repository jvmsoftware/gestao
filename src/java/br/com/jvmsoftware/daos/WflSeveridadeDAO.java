/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.WflSeveridade;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class WflSeveridadeDAO extends DefaultDAO {
    
    public WflSeveridade getById(int Id) throws SQLException {
        getSession();
        begin();
        WflSeveridade severidade;
        severidade = (WflSeveridade) session.get(WflSeveridade.class, Id);
        return severidade;
    }
    
    public List<WflSeveridade> listAllSeveridades() throws SQLException {
        getSession();
        begin();
        List<WflSeveridade> severidades;
        severidades = session.createQuery("from WflSeveridade u order by u.prioridade").list();
        return severidades;
    }
    
    public void inserirSeveridade (WflSeveridade severidade) throws SQLException {
        getSession();
        begin();
        session.save(severidade);
        commit();
        closeSession();
    }
    
    public void updateSeveridade (WflSeveridade severidade) throws SQLException {
        getSession();
        begin();
        session.merge(severidade);
        commit();
        closeSession();
    }
    
}
