/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubConfigEmpresa;
import java.sql.SQLException;

/**
 *
 * @author jose
 */
public class PubConfigEmpresaDAO extends DefaultDAO {
    
    public PubConfigEmpresa getById(int Id) throws SQLException {
        getSession();
        begin();
        PubConfigEmpresa configEmpresa;
        configEmpresa = (PubConfigEmpresa) session.get(PubConfigEmpresa.class, Id);
        return configEmpresa;
    }
    
    public void inserirConfigEmpresa (PubConfigEmpresa configEmpresa) throws SQLException {
        getSession();
        begin();
        session.save(configEmpresa);
        commit();
        closeSession();
    }
    
    public void atualizarConfigEmpresa (PubConfigEmpresa configEmpresa) throws SQLException {
        getSession();
        begin();
        session.merge(configEmpresa);
        commit();
        closeSession();
    }
    
}
