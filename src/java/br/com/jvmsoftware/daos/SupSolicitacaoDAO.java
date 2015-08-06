/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.SupSolicitacao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class SupSolicitacaoDAO extends DefaultDAO {
    
    public SupSolicitacao getById(int Id) throws SQLException {
        getSession();
        begin();
        SupSolicitacao solicitacao;
        solicitacao = (SupSolicitacao) session.get(SupSolicitacao.class, Id);
        return solicitacao;
    }
    
    public List<SupSolicitacao> listProjetosByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<SupSolicitacao> solicitacaos;
        solicitacaos = session.createQuery("from SupSolicitacao u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return solicitacaos;
    }
    
    public void inserirProjeto (SupSolicitacao solicitacao) throws SQLException {
        getSession();
        begin();
        session.save(solicitacao);
        commit();
        closeSession();
    }
    
    public void updateProjeto (SupSolicitacao solicitacao) throws SQLException {
        getSession();
        begin();
        session.merge(solicitacao);
        commit();
        closeSession();
    }
    
}
