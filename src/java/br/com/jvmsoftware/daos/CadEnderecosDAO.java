/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.CadEnderecos;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadEnderecosDAO extends DefaultDAO {
    
    public CadEnderecos getById(int Id) throws SQLException {
        getSession();
        begin();
        CadEnderecos pessoaFisica;
        pessoaFisica = (CadEnderecos) session.get(CadEnderecos.class, Id);
        return pessoaFisica;
    }
    
    public CadEnderecos getPessoasByMail(String mail) throws SQLException {
        getSession();
        begin();
        CadEnderecos pessoaFisica;
        pessoaFisica = (CadEnderecos) session.createQuery("from CadEnderecos u where u.email = :mail").setParameter("mail", mail).uniqueResult();
        return pessoaFisica;
    }
    
    public List<CadEnderecos> listAllPessoas() throws SQLException {
        getSession();
        begin();
        List<CadEnderecos> pessoasFisica;
        pessoasFisica = session.createQuery("from CadEnderecos").list();
        return pessoasFisica;
    }
    
    public List<CadEnderecos> listPessoasByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<CadEnderecos> pessoasFisica;
        pessoasFisica = session.createQuery("from CadEnderecos u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoasFisica;
    }
    
    public void inserirPessoa (CadEnderecos pessoaFisica) throws SQLException {
        getSession();
        begin();
        session.save(pessoaFisica);
        commit();
        closeSession();
    }
    
    public void updatePessoa (CadEnderecos pessoaFisica) throws SQLException {
        getSession();
        begin();
        session.merge(pessoaFisica);
        commit();
        closeSession();
    }
    
}
