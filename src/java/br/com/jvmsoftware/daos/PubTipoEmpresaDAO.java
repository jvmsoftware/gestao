/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubTipoEmpresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubTipoEmpresaDAO extends DefaultDAO {
    
    public PubTipoEmpresa getById(int Id) throws SQLException {
        getSession();
        begin();
        PubTipoEmpresa tipoEmpresa;
        tipoEmpresa = (PubTipoEmpresa) session.get(PubTipoEmpresa.class, Id);
        return tipoEmpresa;
    }
    
    public List<PubTipoEmpresa> listAllTipoEmpresas() throws SQLException {
        getSession();
        begin();
        List<PubTipoEmpresa> tipoEmpresas;
        tipoEmpresas = session.createQuery("from PubTipoEmpresa").list();
        return tipoEmpresas;
    }
    
    public void inserirTipoEmpresa (PubTipoEmpresa tipoEmpresa) throws SQLException {
        getSession();
        begin();
        session.save(tipoEmpresa);
        commit();
        closeSession();
    }
    
    public void atualizarTipoEmpresa (PubTipoEmpresa tipoEmpresa) throws SQLException {
        getSession();
        begin();
        session.merge(tipoEmpresa);
        commit();
        closeSession();
    }
    
}
