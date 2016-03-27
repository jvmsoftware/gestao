/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.CadEnderecos;
import br.com.jvmsoftware.entities.CadPessoa;
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
        CadEnderecos endereco;
        endereco = (CadEnderecos) session.get(CadEnderecos.class, Id);
        return endereco;
    }
    
    public List<CadEnderecos> listEnderecosByPessoa(CadPessoa pessoa) throws SQLException {
        getSession();
        begin();
        List<CadEnderecos> enderecos;
        enderecos = session.createQuery("from CadEnderecos u where u.cadPessoa.idPessoa = :pes").setParameter("pes", pessoa.getIdPessoa()).list();
        return enderecos;
    }
    
    public void inserirEndereco (CadEnderecos endereco) throws SQLException {
        getSession();
        begin();
        session.save(endereco);
        commit();
        closeSession();
    }
    
    public void updateEndereco (CadEnderecos endereco) throws SQLException {
        getSession();
        begin();
        session.merge(endereco);
        commit();
        closeSession();
    }
    
}
