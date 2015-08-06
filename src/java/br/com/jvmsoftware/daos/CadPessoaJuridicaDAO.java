/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.CadPessoaJuridica;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadPessoaJuridicaDAO extends DefaultDAO {
    
    public CadPessoaJuridica getById(int Id) throws SQLException {
        getSession();
        begin();
        CadPessoaJuridica pessoaJuridica;
        pessoaJuridica = (CadPessoaJuridica) session.get(CadPessoaJuridica.class, Id);
        return pessoaJuridica;
    }
    
    public CadPessoaJuridica getPessoasByMail(String mail) throws SQLException {
        getSession();
        begin();
        CadPessoaJuridica pessoaJuridica;
        pessoaJuridica = (CadPessoaJuridica) session.createQuery("from CadPessoaJuridica u where u.email = :mail").setParameter("mail", mail).uniqueResult();
        return pessoaJuridica;
    }
    
    public List<CadPessoaJuridica> listAllPessoas() throws SQLException {
        getSession();
        begin();
        List<CadPessoaJuridica> pessoasJuridica;
        pessoasJuridica = session.createQuery("from CadPessoaJuridica").list();
        return pessoasJuridica;
    }
    
    public List<CadPessoaJuridica> listPessoasByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<CadPessoaJuridica> pessoasJuridica;
        pessoasJuridica = session.createQuery("from CadPessoaJuridica u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoasJuridica;
    }
    
    public void inserirPessoa (CadPessoaJuridica pessoaJuridica) throws SQLException {
        getSession();
        begin();
        session.save(pessoaJuridica);
        commit();
        closeSession();
    }
    
    public void updatePessoa (CadPessoaJuridica pessoaJuridica) throws SQLException {
        getSession();
        begin();
        session.merge(pessoaJuridica);
        commit();
        closeSession();
    }
    
}
