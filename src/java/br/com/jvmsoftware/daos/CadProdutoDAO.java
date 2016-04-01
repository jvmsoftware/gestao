/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadProduto;
import br.com.jvmsoftware.entities.PubEmpresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadProdutoDAO extends DefaultDAO {
    
    public CadProduto getById(int Id) throws SQLException {
        getSession();
        begin();
        CadProduto produto;
        produto = (CadProduto) session.get(CadProduto.class, Id);
        return produto;
    }

    public List<CadProduto> listAllProdutos() throws SQLException {
        getSession();
        begin();
        List<CadProduto> produtos;
        produtos = session.createQuery("from CadProduto e order by e.produto").list();
        return produtos;
    }
        
    public List<CadProduto> listProdutosByEmpresa(PubEmpresa empresa, String filtro) throws SQLException {
        getSession();
        begin();
        List<CadProduto> pessoas;
        if ("".equals(filtro)) {
            pessoas = session.createQuery("from CadProduto u where u.pubEmpresa.idEmpresa = :emp order by u.produto").setParameter("emp", empresa.getIdEmpresa()).list();
        } else {
            pessoas = session.createQuery("from CadProduto u where u.pubEmpresa.idEmpresa = :emp and upper(u.produto) like upper(:filtro) order by u.produto").setParameter("emp", empresa.getIdEmpresa()).setParameter("filtro", "%"+filtro+"%").list();
        }
        return pessoas;
    }
        
    public void inserirProduto (CadProduto produto) throws SQLException {
        getSession();
        begin();
        session.save(produto);
        commit();
        closeSession();
    }
    
    public void atualizarProduto (CadProduto produto) throws SQLException {
        getSession();
        begin();
        session.merge(produto);
        commit();
        closeSession();
    }
    
}
