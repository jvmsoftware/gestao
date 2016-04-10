/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubMarcaVeiculo;
import br.com.jvmsoftware.entities.PubTipoVeiculo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubMarcaVeiculoDAO extends DefaultDAO {
    
    public PubMarcaVeiculo getById(int Id) throws SQLException {
        getSession();
        begin();
        PubMarcaVeiculo marcaVeiculo;
        marcaVeiculo = (PubMarcaVeiculo) session.get(PubMarcaVeiculo.class, Id);
        return marcaVeiculo;
    }
    
    public List<PubMarcaVeiculo> listMarcaVeiculo() throws SQLException {
        getSession();
        begin();
        List<PubMarcaVeiculo> marcaVeiculos;
        marcaVeiculos = session.createQuery("from PubMarcaVeiculo u").list();
        return marcaVeiculos;
    }
    
    public List<PubMarcaVeiculo> listMarcaVeiculoByTipo(PubTipoVeiculo tipo) throws SQLException {
        getSession();
        begin();
        List<PubMarcaVeiculo> marcaVeiculos;
        marcaVeiculos = session.createQuery("from PubMarcaVeiculo u where u.pubTipoVeiculo.idTipoVeiculo = :tipo").setParameter("tipo", tipo.getIdTipoVeiculo()).list();
        return marcaVeiculos;
    }
      
    public void inserirMarcaVeiculo (PubMarcaVeiculo marcaVeiculo) throws SQLException {
        getSession();
        begin();
        session.save(marcaVeiculo);
        commit();
        closeSession();
    }
    
    public void updateMarcaVeiculo (PubMarcaVeiculo marcaVeiculo) throws SQLException {
        getSession();
        begin();
        session.merge(marcaVeiculo);
        commit();
        closeSession();
    }
    
}
