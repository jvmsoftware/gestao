/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadProduto;
import br.com.jvmsoftware.entities.CadRelProdFornec;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadRelProdFornecDAO extends DefaultDAO {
    
    public CadRelProdFornec getById(int Id) throws SQLException {
        getSession();
        begin();
        CadRelProdFornec rel;
        rel = (CadRelProdFornec) session.get(CadRelProdFornec.class, Id);
        return rel;
    }

    public List<CadRelProdFornec> listRelacionamentoByProduto(CadProduto produto) throws SQLException {
        getSession();
        begin();
        List<CadRelProdFornec> rel;
        rel = session.createQuery("from CadRelProdFornec u where u.cadProduto.idProduto = :prod").setParameter("prod", produto.getIdProduto()).list();
        return rel;
    }
        
    public void inserirRelacionamento (CadRelProdFornec rel) throws SQLException {
        getSession();
        begin();
        session.save(rel);
        commit();
        closeSession();
    }
    
    public void atualizarRelacionamento (CadRelProdFornec rel) throws SQLException {
        getSession();
        begin();
        session.merge(rel);
        commit();
        closeSession();
    }
    
}
