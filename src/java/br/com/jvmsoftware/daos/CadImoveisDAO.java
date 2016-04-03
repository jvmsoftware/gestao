/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadImoveis;
import br.com.jvmsoftware.entities.CadPessoa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadImoveisDAO extends DefaultDAO {
    
    public CadImoveis getById(int Id) throws SQLException {
        getSession();
        begin();
        CadImoveis imovel;
        imovel = (CadImoveis) session.get(CadImoveis.class, Id);
        return imovel;
    }
    
    public List<CadImoveis> listImoveisByPessoa(CadPessoa pessoa) throws SQLException {
        getSession();
        begin();
        List<CadImoveis> imoveis;
        imoveis = session.createQuery("from CadImoveis u where u.cadPessoa.idPessoa = :pes").setParameter("pes", pessoa.getIdPessoa()).list();
        return imoveis;
    }
    
    public void inserirEndereco (CadImoveis imovel) throws SQLException {
        getSession();
        begin();
        session.save(imovel);
        commit();
        closeSession();
    }
    
    public void updateEndereco (CadImoveis imovel) throws SQLException {
        getSession();
        begin();
        session.merge(imovel);
        commit();
        closeSession();
    }
    
}
