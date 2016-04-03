/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubTipoVeiculo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubTipoVeiculoDAO extends DefaultDAO {
    
    public PubTipoVeiculo getById(int Id) throws SQLException {
        getSession();
        begin();
        PubTipoVeiculo tipoVeiculo;
        tipoVeiculo = (PubTipoVeiculo) session.get(PubTipoVeiculo.class, Id);
        return tipoVeiculo;
    }
    
    public List<PubTipoVeiculo> listTipoVeiculo() throws SQLException {
        getSession();
        begin();
        List<PubTipoVeiculo> tipoVeiculos;
        tipoVeiculos = session.createQuery("from PubTipoVeiculo u").list();
        return tipoVeiculos;
    }
    
    public void inserirTipoVeiculo (PubTipoVeiculo tipoVeiculo) throws SQLException {
        getSession();
        begin();
        session.save(tipoVeiculo);
        commit();
        closeSession();
    }
    
    public void updateTipoVeiculo (PubTipoVeiculo tipoVeiculo) throws SQLException {
        getSession();
        begin();
        session.merge(tipoVeiculo);
        commit();
        closeSession();
    }
    
}
