/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubFuncionalidade;
import br.com.jvmsoftware.entities.PubSistema;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubFuncionalidadeDAO extends DefaultDAO {
    
    public PubFuncionalidade getById(int Id) throws SQLException {
        getSession();
        begin();
        PubFuncionalidade funcionalidade;
        funcionalidade = (PubFuncionalidade) session.get(PubFuncionalidade.class, Id);
        closeSession();
        return funcionalidade;
    }
    
    public List<PubFuncionalidade> listAllFuncionalidades() throws SQLException {
        getSession();
        begin();
        List<PubFuncionalidade> funcionalidades;
        funcionalidades = session.createQuery("from PubFuncionalidade f order by f.pubSistema.nomeSistema, f.descricaoFuncionalidade").list();
        return funcionalidades;
    }
    
    public List<PubFuncionalidade> listFuncionalidadesBySistema(PubSistema sis) throws SQLException {
        getSession();
        begin();
        List<PubFuncionalidade> funcionalidades;
        funcionalidades = session.createQuery("from PubFuncionalidade f where f.pubSistema.idSistema = :sis order by f.pubSistema.nomeSistema, f.descricaoFuncionalidade").setParameter("sis", sis.getIdSistema()).list();
        return funcionalidades;
    }
    
    public void inserirFuncionalidade (PubFuncionalidade funcionalidade) throws SQLException {
        getSession();
        begin();
        session.save(funcionalidade);
        commit();
        closeSession();
    }
    
    public void atualizarFuncionalidade (PubFuncionalidade funcionalidade) throws SQLException {
        getSession();
        begin();
        session.merge(funcionalidade);
        commit();
        closeSession();
    }
    
    
}
