/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.ComCompraOrdem;
import br.com.jvmsoftware.entities.PubEmpresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class ComCompraOrdemDAO extends DefaultDAO {
    
    public ComCompraOrdem getById(int Id) throws SQLException {
        getSession();
        begin();
        ComCompraOrdem compraOrdem;
        compraOrdem = (ComCompraOrdem) session.get(ComCompraOrdem.class, Id);
        return compraOrdem;
    }
    
    public List<ComCompraOrdem> listAllSevicos() throws SQLException {
        getSession();
        begin();
        List<ComCompraOrdem> compraOrdems;
        compraOrdems = session.createQuery("from ComCompraOrdem e order by e.compraOrdem").list();
        return compraOrdems;
    }
        
    public List<ComCompraOrdem> listCompraOrdemByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<ComCompraOrdem> pessoas;
        pessoas = session.createQuery("from ComCompraOrdem u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoas;
    }
        
    public void inserirCompraOrdem (ComCompraOrdem compraOrdem) throws SQLException {
        getSession();
        begin();
        session.save(compraOrdem);
        commit();
        closeSession();
    }
    
    public void atualizarCompraOrdem (ComCompraOrdem compraOrdem) throws SQLException {
        getSession();
        begin();
        session.merge(compraOrdem);
        commit();
        closeSession();
    }
    
}
