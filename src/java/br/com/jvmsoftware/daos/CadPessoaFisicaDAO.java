/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.CadPessoaFisica;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadPessoaFisicaDAO extends DefaultDAO {
    
    public CadPessoaFisica getById(int Id) throws SQLException {
        getSession();
        begin();
        CadPessoaFisica pessoaFisica;
        pessoaFisica = (CadPessoaFisica) session.get(CadPessoaFisica.class, Id);
        return pessoaFisica;
    }
    
    public CadPessoaFisica getPessoasByMail(String mail) throws SQLException {
        getSession();
        begin();
        CadPessoaFisica pessoaFisica;
        pessoaFisica = (CadPessoaFisica) session.createQuery("from CadPessoaFisica u where u.email = :mail").setParameter("mail", mail).uniqueResult();
        return pessoaFisica;
    }
    
    public List<CadPessoaFisica> listAllPessoas() throws SQLException {
        getSession();
        begin();
        List<CadPessoaFisica> pessoasFisica;
        pessoasFisica = session.createQuery("from CadPessoaFisica").list();
        return pessoasFisica;
    }
    
    public List<CadPessoaFisica> listPessoasByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<CadPessoaFisica> pessoasFisica;
        pessoasFisica = session.createQuery("from CadPessoaFisica u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoasFisica;
    }
    
    public void inserirPessoa (CadPessoaFisica pessoaFisica) throws SQLException {
        getSession();
        begin();
        session.save(pessoaFisica);
        commit();
        closeSession();
    }
    
    public void updatePessoa (CadPessoaFisica pessoaFisica) throws SQLException {
        getSession();
        begin();
        session.merge(pessoaFisica);
        commit();
        closeSession();
    }
    
}
