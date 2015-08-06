/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CtrLog;
import br.com.jvmsoftware.entities.PubSistema;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CtrLogDAO extends DefaultDAO {
    
    public CtrLog getById(int Id) throws SQLException {
        getSession();
        begin();
        CtrLog tipoNegocio;
        tipoNegocio = (CtrLog) session.get(CtrLog.class, Id);
        return tipoNegocio;
    }
    
    public List<CtrLog> listAllTipoNegocios() throws SQLException {
        getSession();
        begin();
        List<CtrLog> tipoNegocios;
        tipoNegocios = session.createQuery("from CtrLog").list();
        return tipoNegocios;
    }
    
    public List<CtrLog> listTipoNegociosBySistema(PubSistema sistema) throws SQLException {
        getSession();
        begin();
        List<CtrLog> tipoNegocios;
        tipoNegocios = session.createQuery("from CtrLog u where u.sistema = :sis").setParameter("sis", sistema.getIdSistema()).list();
        return tipoNegocios;
    }
    
    public void inserirTipoNegocio (CtrLog tipoNegocio) throws SQLException {
        getSession();
        begin();
        session.save(tipoNegocio);
        commit();
        closeSession();
    }
    
    public void atualizarTipoNegocio (CtrLog tipoNegocio) throws SQLException {
        getSession();
        begin();
        session.merge(tipoNegocio);
        commit();
        closeSession();
    }
    
}
