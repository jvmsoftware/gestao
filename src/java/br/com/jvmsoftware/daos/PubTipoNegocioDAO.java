/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubTipoEmpresa;
import br.com.jvmsoftware.entities.PubTipoNegocio;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubTipoNegocioDAO extends DefaultDAO {
    
    public PubTipoNegocio getById(int Id) throws SQLException {
        getSession();
        begin();
        PubTipoNegocio tipoNegocio;
        tipoNegocio = (PubTipoNegocio) session.get(PubTipoNegocio.class, Id);
        return tipoNegocio;
    }
    
    public List<PubTipoNegocio> listAllTipoNegocios() throws SQLException {
        getSession();
        begin();
        List<PubTipoNegocio> tipoNegocios;
        tipoNegocios = session.createQuery("from PubTipoNegocio").list();
        return tipoNegocios;
    }
    
    public List<PubTipoNegocio> listTipoNegociosByTipoEmpresa(PubTipoEmpresa tipoEmpresa) throws SQLException {
        getSession();
        begin();
        List<PubTipoNegocio> tipoNegocios;
        tipoNegocios = session.createQuery("from PubTipoNegocio u where u.pubTipoEmpresa.idTipoEmpresa = :tpEmp").setParameter("tpEmp", tipoEmpresa.getIdTipoEmpresa()).list();
        return tipoNegocios;
    }
    
    public void inserirTipoNegocio (PubTipoNegocio tipoNegocio) throws SQLException {
        getSession();
        begin();
        session.save(tipoNegocio);
        commit();
        closeSession();
    }
    
    public void atualizarTipoNegocio (PubTipoNegocio tipoNegocio) throws SQLException {
        getSession();
        begin();
        session.merge(tipoNegocio);
        commit();
        closeSession();
    }
    
}
