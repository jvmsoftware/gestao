/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadEquipamentos;
import br.com.jvmsoftware.entities.CadPessoa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadEquipamentosDAO extends DefaultDAO {
    
    public CadEquipamentos getById(int Id) throws SQLException {
        getSession();
        begin();
        CadEquipamentos equipamento;
        equipamento = (CadEquipamentos) session.get(CadEquipamentos.class, Id);
        return equipamento;
    }
    
    public List<CadEquipamentos> listEquipamentosByPessoa(CadPessoa pessoa) throws SQLException {
        getSession();
        begin();
        List<CadEquipamentos> equipamentos;
        equipamentos = session.createQuery("from CadEquipamentos u where u.cadPessoa.idPessoa = :pes").setParameter("pes", pessoa.getIdPessoa()).list();
        return equipamentos;
    }
    
    public void inserirEndereco (CadEquipamentos equipamento) throws SQLException {
        getSession();
        begin();
        session.save(equipamento);
        commit();
        closeSession();
    }
    
    public void updateEndereco (CadEquipamentos equipamento) throws SQLException {
        getSession();
        begin();
        session.merge(equipamento);
        commit();
        closeSession();
    }
    
}
