/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadServicos;
import br.com.jvmsoftware.entities.CadRelServFornec;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadRelServFornecDAO extends DefaultDAO {
    
    public CadRelServFornec getById(int Id) throws SQLException {
        getSession();
        begin();
        CadRelServFornec rel;
        rel = (CadRelServFornec) session.get(CadRelServFornec.class, Id);
        return rel;
    }

    public List<CadRelServFornec> listRelacionamentoByServicos(CadServicos servicos) throws SQLException {
        getSession();
        begin();
        List<CadRelServFornec> rel;
        rel = session.createQuery("from CadRelServFornec u where u.cadServicos.idServico = :prod").setParameter("prod", servicos.getIdServico()).list();
        return rel;
    }
        
    public void inserirRelacionamento (CadRelServFornec rel) throws SQLException {
        getSession();
        begin();
        session.save(rel);
        commit();
        closeSession();
    }
    
    public void atualizarRelacionamento (CadRelServFornec rel) throws SQLException {
        getSession();
        begin();
        session.merge(rel);
        commit();
        closeSession();
    }
    
}
