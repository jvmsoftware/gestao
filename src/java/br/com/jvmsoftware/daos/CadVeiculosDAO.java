/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadVeiculos;
import br.com.jvmsoftware.entities.CadPessoa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadVeiculosDAO extends DefaultDAO {
    
    public CadVeiculos getById(int Id) throws SQLException {
        getSession();
        begin();
        CadVeiculos veiculo;
        veiculo = (CadVeiculos) session.get(CadVeiculos.class, Id);
        return veiculo;
    }
    
    public List<CadVeiculos> listVeiculosByPessoa(CadPessoa pessoa) throws SQLException {
        getSession();
        begin();
        List<CadVeiculos> veiculos;
        veiculos = session.createQuery("from CadVeiculos u where u.cadPessoa.idPessoa = :pes").setParameter("pes", pessoa.getIdPessoa()).list();
        return veiculos;
    }
    
    public void inserirEndereco (CadVeiculos veiculo) throws SQLException {
        getSession();
        begin();
        session.save(veiculo);
        commit();
        closeSession();
    }
    
    public void updateEndereco (CadVeiculos veiculo) throws SQLException {
        getSession();
        begin();
        session.merge(veiculo);
        commit();
        closeSession();
    }
    
}
