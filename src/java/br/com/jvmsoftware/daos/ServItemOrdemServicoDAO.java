/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.ServItemOrdemServico;
import br.com.jvmsoftware.entities.ServOrdemServico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class ServItemOrdemServicoDAO extends DefaultDAO {
    
    public ServItemOrdemServico getById(int Id) throws SQLException {
        getSession();
        begin();
        ServItemOrdemServico itemOrdemServico;
        itemOrdemServico = (ServItemOrdemServico) session.get(ServItemOrdemServico.class, Id);
        return itemOrdemServico;
    }
    
    public List<ServItemOrdemServico> listItemOrdemServicosByOrdemServico(ServOrdemServico ordemServico) throws SQLException {
        getSession();
        begin();
        List<ServItemOrdemServico> pessoas;
        pessoas = session.createQuery("from ServItemOrdemServico u where u.servOrdemServico.idOrdem = :ord order by u.idItem").setParameter("ord", ordemServico.getIdOrdem()).list();
        return pessoas;
    }
        
    public void inserirItemOrdemServico (ServItemOrdemServico itemOrdemServico) throws SQLException {
        getSession();
        begin();
        session.save(itemOrdemServico);
        commit();
        closeSession();
    }
    
    public void atualizarItemOrdemServico (ServItemOrdemServico itemOrdemServico) throws SQLException {
        getSession();
        begin();
        session.merge(itemOrdemServico);
        commit();
        closeSession();
    }
    
}
