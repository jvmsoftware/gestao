/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.ComVendaPedido;
import br.com.jvmsoftware.entities.PubEmpresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class ComVendaPedidoDAO extends DefaultDAO {
    
    public ComVendaPedido getById(int Id) throws SQLException {
        getSession();
        begin();
        ComVendaPedido vendaPedido;
        vendaPedido = (ComVendaPedido) session.get(ComVendaPedido.class, Id);
        return vendaPedido;
    }
    
    public List<ComVendaPedido> listAllSevicos() throws SQLException {
        getSession();
        begin();
        List<ComVendaPedido> vendaPedidos;
        vendaPedidos = session.createQuery("from ComVendaPedido e order by e.vendaPedido").list();
        return vendaPedidos;
    }
        
    public List<ComVendaPedido> listVendaPedidoByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<ComVendaPedido> pessoas;
        pessoas = session.createQuery("from ComVendaPedido u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoas;
    }
        
    public void inserirVendaPedido (ComVendaPedido vendaPedido) throws SQLException {
        getSession();
        begin();
        session.save(vendaPedido);
        commit();
        closeSession();
    }
    
    public void atualizarVendaPedido (ComVendaPedido vendaPedido) throws SQLException {
        getSession();
        begin();
        session.merge(vendaPedido);
        commit();
        closeSession();
    }
    
}
