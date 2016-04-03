/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubFrequenciaServico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubFrequenciaServicoDAO extends DefaultDAO {
    
    public PubFrequenciaServico getById(int Id) throws SQLException {
        getSession();
        begin();
        PubFrequenciaServico frequenciaServico;
        frequenciaServico = (PubFrequenciaServico) session.get(PubFrequenciaServico.class, Id);
        return frequenciaServico;
    }
    
    public List<PubFrequenciaServico> listFrequenciaServico() throws SQLException {
        getSession();
        begin();
        List<PubFrequenciaServico> frequenciaServicos;
        frequenciaServicos = session.createQuery("from PubFrequenciaServico u").list();
        return frequenciaServicos;
    }
    
    public void inserirFrequenciaServico (PubFrequenciaServico frequenciaServico) throws SQLException {
        getSession();
        begin();
        session.save(frequenciaServico);
        commit();
        closeSession();
    }
    
    public void updateFrequenciaServico (PubFrequenciaServico frequenciaServico) throws SQLException {
        getSession();
        begin();
        session.merge(frequenciaServico);
        commit();
        closeSession();
    }
    
}
