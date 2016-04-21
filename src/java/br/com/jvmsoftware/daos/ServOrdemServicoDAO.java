/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.ServOrdemServico;
import br.com.jvmsoftware.entities.ServOrdemServico;
import br.com.jvmsoftware.entities.PubEmpresa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class ServOrdemServicoDAO extends DefaultDAO {
    
    public ServOrdemServico getById(int Id) throws SQLException {
        getSession();
        begin();
        ServOrdemServico ordemServico;
        ordemServico = (ServOrdemServico) session.get(ServOrdemServico.class, Id);
        return ordemServico;
    }

    
    public List<ServOrdemServico> listOrdemServicosByEmpresa(PubEmpresa empresa, String filtro) throws SQLException {
        getSession();
        begin();
        List<ServOrdemServico> pessoas;
        if ("".equals(filtro)) {
            pessoas = session.createQuery("from ServOrdemServico u where u.pubEmpresa.idEmpresa = :emp and u.cliente = true").setParameter("emp", empresa.getIdEmpresa()).list();
        } else {
            pessoas = session.createQuery("from ServOrdemServico u where u.pubEmpresa.idEmpresa = :emp and upper(u.cadPessoa.nomeRazaoSocial) like upper(:filtro) and u.cadPessoa.cliente = true").setParameter("emp", empresa.getIdEmpresa()).setParameter("filtro", "%"+filtro+"%").list();
        }
        return pessoas;
    }
       
    public void inserirOrdemServico (ServOrdemServico ordemServico) throws SQLException {
        getSession();
        begin();
        session.save(ordemServico);
        commit();
        closeSession();
    }
    
    public void atualizarOrdemServico (ServOrdemServico ordemServico) throws SQLException {
        getSession();
        begin();
        session.merge(ordemServico);
        commit();
        closeSession();
    }
    
}
