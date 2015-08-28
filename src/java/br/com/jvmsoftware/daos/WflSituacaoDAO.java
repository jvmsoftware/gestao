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
    
    public List<WflSituacao> listAllSituacao() throws SQLException {
        getSession();
        begin();
        List<WflSituacao> situacaos;
        situacaos = session.createQuery("from WflSituacao u order by u.prioridade").list();
        return situacaos;
    }
    
    public List<WflSituacao> listSituacaoByFiltro(int empresa, int sistema) throws SQLException {
        getSession();
        begin();
        List<WflSituacao> situacaos = null;
        if (empresa > 0 && sistema > 0) {
            situacaos = session.createQuery("from WflSituacao u where u.pubEmpresa.idEmpresa = :emp and u.pubSistema.idSistema = :sis order by u.prioridade").setParameter("emp", empresa).setParameter("sis", sistema).list();
        } else if (empresa == 0 && sistema > 0) {
            situacaos = session.createQuery("from WflSituacao u where u.pubSistema.idSistema = :sis order by u.prioridade").setParameter("sis", sistema).list();
        } else if (empresa > 0 && sistema == 0) {
            situacaos = session.createQuery("from WflSituacao u where u.pubEmpresa.idEmpresa = :emp order by u.prioridade").setParameter("emp", empresa).list();
        }
        return situacaos;
    }
    
    public void inserirSituacao (WflSituacao situacao) throws SQLException {
        getSession();
        begin();
        session.save(situacao);
        commit();
        closeSession();
    }
    
    public void updateSituacao (WflSituacao situacao) throws SQLException {
        getSession();
        begin();
        session.merge(situacao);
        commit();
        closeSession();
    }
    
}
