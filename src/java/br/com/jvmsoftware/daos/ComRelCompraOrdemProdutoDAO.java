/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.ComCompraOrdem;
import br.com.jvmsoftware.entities.ComRelCompraOrdemProduto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class ComRelCompraOrdemProdutoDAO extends DefaultDAO {
    
    public ComRelCompraOrdemProduto getById(int Id) throws SQLException {
        getSession();
        begin();
        ComRelCompraOrdemProduto relCompraOrdemProduto;
        relCompraOrdemProduto = (ComRelCompraOrdemProduto) session.get(ComRelCompraOrdemProduto.class, Id);
        return relCompraOrdemProduto;
    }
    
    public List<ComRelCompraOrdemProduto> listAllSevicos() throws SQLException {
        getSession();
        begin();
        List<ComRelCompraOrdemProduto> relCompraOrdemProdutos;
        relCompraOrdemProdutos = session.createQuery("from ComRelCompraOrdemProduto e order by e.relCompraOrdemProduto").list();
        return relCompraOrdemProdutos;
    }
        
    public List<ComRelCompraOrdemProduto> listRelCompraOrdemProdutoByOrdem(ComCompraOrdem ordem) throws SQLException {
        getSession();
        begin();
        List<ComRelCompraOrdemProduto> pessoas;
        pessoas = session.createQuery("from ComRelCompraOrdemProduto u where u.comCompraOrdem.id = :ordem").setParameter("ordem", ordem.getId()).list();
        return pessoas;
    }
        
    public void inserirRelCompraOrdemProduto (ComRelCompraOrdemProduto relCompraOrdemProduto) throws SQLException {
        getSession();
        begin();
        session.save(relCompraOrdemProduto);
        commit();
        closeSession();
    }
    
    public void atualizarRelCompraOrdemProduto (ComRelCompraOrdemProduto relCompraOrdemProduto) throws SQLException {
        getSession();
        begin();
        session.merge(relCompraOrdemProduto);
        commit();
        closeSession();
    }
    
}
