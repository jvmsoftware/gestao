/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubMarcaVeiculo;
import br.com.jvmsoftware.entities.PubModeloVeiculo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubModeloVeiculoDAO extends DefaultDAO {
    
    public PubModeloVeiculo getById(int Id) throws SQLException {
        getSession();
        begin();
        PubModeloVeiculo modeloVeiculo;
        modeloVeiculo = (PubModeloVeiculo) session.get(PubModeloVeiculo.class, Id);
        return modeloVeiculo;
    }
    
    public List<PubModeloVeiculo> listModeloVeiculo() throws SQLException {
        getSession();
        begin();
        List<PubModeloVeiculo> modeloVeiculos;
        modeloVeiculos = session.createQuery("from PubModeloVeiculo u").list();
        return modeloVeiculos;
    }
    
    public List<PubModeloVeiculo> listModeloVeiculoByMarca(PubMarcaVeiculo marca) throws SQLException {
        getSession();
        begin();
        List<PubModeloVeiculo> modeloVeiculos;
        modeloVeiculos = session.createQuery("from PubModeloVeiculo u where u.pubMarcaVeiculo.idMarcaVeiculo = :marca").setParameter("marca", marca.getIdMarcaVeiculo()).list();
        return modeloVeiculos;
    }
    
    public void inserirModeloVeiculo (PubModeloVeiculo modeloVeiculo) throws SQLException {
        getSession();
        begin();
        session.save(modeloVeiculo);
        commit();
        closeSession();
    }
    
    public void updateModeloVeiculo (PubModeloVeiculo modeloVeiculo) throws SQLException {
        getSession();
        begin();
        session.merge(modeloVeiculo);
        commit();
        closeSession();
    }
    
}
