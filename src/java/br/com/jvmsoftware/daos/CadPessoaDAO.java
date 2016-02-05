/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.CadPessoa;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class CadPessoaDAO extends DefaultDAO {
    
    public CadPessoa getById(int Id) throws SQLException {
        getSession();
        begin();
        CadPessoa pessoa;
        pessoa = (CadPessoa) session.get(CadPessoa.class, Id);
        return pessoa;
    }
    
    public List<CadPessoa> listAllPessoas() throws SQLException {
        getSession();
        begin();
        List<CadPessoa> pessoas;
        pessoas = session.createQuery("from CadPessoa").list();
        return pessoas;
    }
    
    public List<CadPessoa> listClientesByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<CadPessoa> pessoas;
        pessoas = session.createQuery("from CadPessoa u where u.pubEmpresa.idEmpresa = :emp and u.cliente = true").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoas;
    }
    
    public List<CadPessoa> listFornecedoresByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<CadPessoa> pessoas;
        pessoas = session.createQuery("from CadPessoa u where u.pubEmpresa.idEmpresa = :emp and u.fornecedor = true").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoas;
    }
    
    public List<CadPessoa> listFuncionariosByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<CadPessoa> pessoas;
        pessoas = session.createQuery("from CadPessoa u where u.pubEmpresa.idEmpresa = :emp and u.funcionario = true").setParameter("emp", empresa.getIdEmpresa()).list();
        return pessoas;
    }
    
    public void inserirPessoa (CadPessoa pessoa) throws SQLException {
        getSession();
        begin();
        session.save(pessoa);
        commit();
        closeSession();
    }
    
    public void updatePessoa (CadPessoa pessoa) throws SQLException {
        getSession();
        begin();
        session.merge(pessoa);
        commit();
        closeSession();
    }
    
}
