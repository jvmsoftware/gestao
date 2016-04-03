/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubTipoImovel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubTipoImovelDAO extends DefaultDAO {
    
    public PubTipoImovel getById(int Id) throws SQLException {
        getSession();
        begin();
        PubTipoImovel tipoImovel;
        tipoImovel = (PubTipoImovel) session.get(PubTipoImovel.class, Id);
        return tipoImovel;
    }
    
    public List<PubTipoImovel> listTipoImovel() throws SQLException {
        getSession();
        begin();
        List<PubTipoImovel> tipoImoveis;
        tipoImoveis = session.createQuery("from PubTipoImovel u").list();
        return tipoImoveis;
    }
    
    public void inserirTipoImovel (PubTipoImovel tipoImovel) throws SQLException {
        getSession();
        begin();
        session.save(tipoImovel);
        commit();
        closeSession();
    }
    
    public void updateTipoImovel (PubTipoImovel tipoImovel) throws SQLException {
        getSession();
        begin();
        session.merge(tipoImovel);
        commit();
        closeSession();
    }
    
}
