/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.ComRelVendaProduto;
import br.com.jvmsoftware.entities.ComVendaPedido;
import br.com.jvmsoftware.entities.PubEmpresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class ComRelVendaProdutoDAO extends DefaultDAO {
    
    public ComRelVendaProduto getById(int Id) throws SQLException {
        getSession();
        begin();
        ComRelVendaProduto relVendaProduto;
        relVendaProduto = (ComRelVendaProduto) session.get(ComRelVendaProduto.class, Id);
        return relVendaProduto;
    }
    
    public List<ComRelVendaProduto> listAllSevicos() throws SQLException {
        getSession();
        begin();
        List<ComRelVendaProduto> relVendaProdutos;
        relVendaProdutos = session.createQuery("from ComRelVendaProduto e order by e.relVendaProduto").list();
        return relVendaProdutos;
    }
        
    public List<ComRelVendaProduto> listRelVendaProdutoByVenda(ComVendaPedido venda) throws SQLException {
        getSession();
        begin();
        List<ComRelVendaProduto> pessoas;
        pessoas = session.createQuery("from ComRelVendaProduto u where u.comVendaPedido.idPedido = :venda").setParameter("venda", venda.getIdPedido()).list();
        return pessoas;
    }
        
    public void inserirRelVendaProduto (ComRelVendaProduto relVendaProduto) throws SQLException {
        getSession();
        begin();
        session.save(relVendaProduto);
        commit();
        closeSession();
    }
    
    public void atualizarRelVendaProduto (ComRelVendaProduto relVendaProduto) throws SQLException {
        getSession();
        begin();
        session.merge(relVendaProduto);
        commit();
        closeSession();
    }
    
}
