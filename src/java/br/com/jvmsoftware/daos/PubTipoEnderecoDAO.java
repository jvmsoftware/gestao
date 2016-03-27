/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubTipoEndereco;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubTipoEnderecoDAO extends DefaultDAO {
    
    public PubTipoEndereco getById(int Id) throws SQLException {
        getSession();
        begin();
        PubTipoEndereco tipoEndereco;
        tipoEndereco = (PubTipoEndereco) session.get(PubTipoEndereco.class, Id);
        return tipoEndereco;
    }
    
    public List<PubTipoEndereco> listTipoEndereco() throws SQLException {
        getSession();
        begin();
        List<PubTipoEndereco> tipoEnderecos;
        tipoEnderecos = session.createQuery("from PubTipoEndereco u").list();
        return tipoEnderecos;
    }
    
    public void inserirTipoEndereco (PubTipoEndereco tipoEndereco) throws SQLException {
        getSession();
        begin();
        session.save(tipoEndereco);
        commit();
        closeSession();
    }
    
    public void updateTipoEndereco (PubTipoEndereco tipoEndereco) throws SQLException {
        getSession();
        begin();
        session.merge(tipoEndereco);
        commit();
        closeSession();
    }
    
}
