/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubTipoEquipamento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubTipoEquipamentoDAO extends DefaultDAO {
    
    public PubTipoEquipamento getById(int Id) throws SQLException {
        getSession();
        begin();
        PubTipoEquipamento tipoEquipamento;
        tipoEquipamento = (PubTipoEquipamento) session.get(PubTipoEquipamento.class, Id);
        return tipoEquipamento;
    }
    
    public List<PubTipoEquipamento> listTipoEquipamento() throws SQLException {
        getSession();
        begin();
        List<PubTipoEquipamento> tipoEquipamentos;
        tipoEquipamentos = session.createQuery("from PubTipoEquipamento u").list();
        return tipoEquipamentos;
    }
    
    public void inserirTipoEquipamento (PubTipoEquipamento tipoEquipamento) throws SQLException {
        getSession();
        begin();
        session.save(tipoEquipamento);
        commit();
        closeSession();
    }
    
    public void updateTipoEquipamento (PubTipoEquipamento tipoEquipamento) throws SQLException {
        getSession();
        begin();
        session.merge(tipoEquipamento);
        commit();
        closeSession();
    }
    
}
