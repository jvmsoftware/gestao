/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubTipoCadastro;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubTipoCadastroDAO extends DefaultDAO {
    
    public PubTipoCadastro getById(int Id) throws SQLException {
        getSession();
        begin();
        PubTipoCadastro tipoNegocio;
        tipoNegocio = (PubTipoCadastro) session.get(PubTipoCadastro.class, Id);
        return tipoNegocio;
    }
    
    public PubTipoCadastro getByCodigo(String cod) throws SQLException {
        getSession();
        begin();
        PubTipoCadastro tipoNegocio;
        tipoNegocio = (PubTipoCadastro) session.createQuery("from PubTipoCadastro t where t.codigoTipoCadastro = :cod").setParameter("cod", cod).uniqueResult();
        return tipoNegocio;
    }
    
    public List<PubTipoCadastro> listAllTipoCadastros() throws SQLException {
        getSession();
        begin();
        List<PubTipoCadastro> tipoNegocios;
        tipoNegocios = session.createQuery("from PubTipoCadastro").list();
        return tipoNegocios;
    }
    
    public void inserirTipoCadastro (PubTipoCadastro tipoNegocio) throws SQLException {
        getSession();
        begin();
        session.save(tipoNegocio);
        commit();
        closeSession();
    }
    
    public void atualizarTipoCadastro (PubTipoCadastro tipoNegocio) throws SQLException {
        getSession();
        begin();
        session.merge(tipoNegocio);
        commit();
        closeSession();
    }
    
}
