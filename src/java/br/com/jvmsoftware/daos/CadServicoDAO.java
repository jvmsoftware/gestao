/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadServicos;
import br.com.jvmsoftware.entities.PubEmpresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadServicoDAO extends DefaultDAO {
    
    public CadServicos getById(int Id) throws SQLException {
        getSession();
        begin();
        CadServicos servico;
        servico = (CadServicos) session.get(CadServicos.class, Id);
        return servico;
    }
    
    public List<CadServicos> listAllSevicos() throws SQLException {
        getSession();
        begin();
        List<CadServicos> servicos;
        servicos = session.createQuery("from CadServicos e order by e.servico").list();
        return servicos;
    }
        
    public List<CadServicos> listServicosByEmpresa(PubEmpresa empresa, String filtro) throws SQLException {
        getSession();
        begin();
        List<CadServicos> pessoas;
        if ("".equals(filtro)) {
            pessoas = session.createQuery("from CadServicos u where u.pubEmpresa.idEmpresa = :emp order by u.servico").setParameter("emp", empresa.getIdEmpresa()).list();
        } else {
            pessoas = session.createQuery("from CadServicos u where u.pubEmpresa.idEmpresa = :emp and upper(u.servico) like upper(:filtro) order by u.produto").setParameter("emp", empresa.getIdEmpresa()).setParameter("filtro", "%"+filtro+"%").list();
        }
        return pessoas;
    }
        
    public void inserirServico (CadServicos servico) throws SQLException {
        getSession();
        begin();
        session.save(servico);
        commit();
        closeSession();
    }
    
    public void atualizarServico (CadServicos servico) throws SQLException {
        getSession();
        begin();
        session.merge(servico);
        commit();
        closeSession();
    }
    
}
