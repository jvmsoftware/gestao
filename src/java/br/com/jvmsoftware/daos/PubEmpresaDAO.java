/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsEmpresaSistema;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubSistema;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubEmpresaDAO extends DefaultDAO {
    
    public PubEmpresa getById(int Id) throws SQLException {
        getSession();
        begin();
        PubEmpresa empresa;
        empresa = (PubEmpresa) session.get(PubEmpresa.class, Id);
        return empresa;
    }
    
    public List<PubEmpresa> listAllEmpresas() throws SQLException {
        getSession();
        begin();
        List<PubEmpresa> empresas;
        empresas = session.createQuery("from PubEmpresa").list();
        return empresas;
    }
    
    public void inserirEmpresa (PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        session.save(empresa);
        commit();
        triggerInsert(empresa);
        closeSession();
    }
    
    public void atualizarEmpresa (PubEmpresa empresa) throws SQLException {
        triggerInsert(empresa);
        getSession();
        begin();
        session.merge(empresa);
        commit();
        closeSession();
        
    }
    
    private void triggerInsert(PubEmpresa empresa) throws SQLException {
        AcsEmpresaSistemaDAO empSisDAO = new AcsEmpresaSistemaDAO();
        List<PubSistema> sistemas;
        getSession();
        begin();
        sistemas = session.createQuery("from PubSistema s "
                                     + "where not exists (from AcsEmpresaSistema r "
                                                       + "where r.pubEmpresa.idEmpresa = :emp)").setParameter("emp", empresa.getIdEmpresa()).list();
        for (PubSistema sistema : sistemas) {
            AcsEmpresaSistema empSis = new AcsEmpresaSistema();
            empSis.setPubEmpresa(empresa);
            empSis.setPubSistema(sistema);
            empSis.setAtivo(false);
            empSisDAO.inserirEmpresaSistema(empSis);
            //empresa.getAcsEmpresaSistemas().add(empSis);
        }
    }
}
