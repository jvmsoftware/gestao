/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmbalagem;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubEmbalagemDAO extends DefaultDAO {
    
    public PubEmbalagem getById(int Id) throws SQLException {
        getSession();
        begin();
        PubEmbalagem embalagem;
        embalagem = (PubEmbalagem) session.get(PubEmbalagem.class, Id);
        return embalagem;
    }
    
    public PubEmbalagem getByEmbalagem(String emb) throws SQLException {
        getSession();
        begin();
        PubEmbalagem embalagem;
        embalagem = (PubEmbalagem) session.createQuery("from PubEmbalagem t where upper(t.embalagem) like upper(:emb) order by t.embalagem").setParameter("emb", "%"+emb+"%").uniqueResult();
        return embalagem;
    }
    
    public List<PubEmbalagem> listAllEmbalagens() throws SQLException {
        getSession();
        begin();
        List<PubEmbalagem> embalagems;
        embalagems = session.createQuery("from PubEmbalagem e order by e.embalagem").list();
        return embalagems;
    }
    
    public void inserirTipoCadastro (PubEmbalagem embalagem) throws SQLException {
        getSession();
        begin();
        session.save(embalagem);
        commit();
        closeSession();
    }
    
    public void atualizarTipoCadastro (PubEmbalagem embalagem) throws SQLException {
        getSession();
        begin();
        session.merge(embalagem);
        commit();
        closeSession();
    }
    
}
