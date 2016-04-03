/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.ComCompraOrdem;
import br.com.jvmsoftware.entities.ComRelCompraOrdemServico;
import br.com.jvmsoftware.entities.ComVendaPedido;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class ComRelCompraOrdemServicoDAO extends DefaultDAO {
    
    public ComRelCompraOrdemServico getById(int Id) throws SQLException {
        getSession();
        begin();
        ComRelCompraOrdemServico relCompraOrdemServico;
        relCompraOrdemServico = (ComRelCompraOrdemServico) session.get(ComRelCompraOrdemServico.class, Id);
        return relCompraOrdemServico;
    }
    
    public List<ComRelCompraOrdemServico> listAllSevicos() throws SQLException {
        getSession();
        begin();
        List<ComRelCompraOrdemServico> relCompraOrdemServicos;
        relCompraOrdemServicos = session.createQuery("from ComRelCompraOrdemServico e order by e.relCompraOrdemServico").list();
        return relCompraOrdemServicos;
    }
        
    public List<ComRelCompraOrdemServico> listRelCompraOrdemServicoByOrdem(ComCompraOrdem ordem) throws SQLException {
        getSession();
        begin();
        List<ComRelCompraOrdemServico> pessoas;
        pessoas = session.createQuery("from ComRelCompraOrdemServico u where u.comCompraOrdem.id = :ordem").setParameter("ordem", ordem.getId()).list();
        return pessoas;
    }
        
    public void inserirRelCompraOrdemServico (ComRelCompraOrdemServico relCompraOrdemServico) throws SQLException {
        getSession();
        begin();
        session.save(relCompraOrdemServico);
        commit();
        closeSession();
    }
    
    public void atualizarRelCompraOrdemServico (ComRelCompraOrdemServico relCompraOrdemServico) throws SQLException {
        getSession();
        begin();
        session.merge(relCompraOrdemServico);
        commit();
        closeSession();
    }
    
}
