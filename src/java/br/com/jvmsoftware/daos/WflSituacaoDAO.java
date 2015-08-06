/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.WflSituacao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class WflSituacaoDAO extends DefaultDAO {
    
    public WflSituacao getById(int Id) throws SQLException {
        getSession();
        begin();
        WflSituacao situacao;
        situacao = (WflSituacao) session.get(WflSituacao.class, Id);
        return situacao;
    }
    
    public List<WflSituacao> listProjetosByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<WflSituacao> situacaos;
        situacaos = session.createQuery("from WflSituacao u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return situacaos;
    }
    
    public void inserirProjeto (WflSituacao situacao) throws SQLException {
        getSession();
        begin();
        session.save(situacao);
        commit();
        closeSession();
    }
    
    public void updateProjeto (WflSituacao situacao) throws SQLException {
        getSession();
        begin();
        session.merge(situacao);
        commit();
        closeSession();
    }
    
}
