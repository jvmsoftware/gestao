/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsEmpresaSistema;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubSistema;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class AcsEmpresaSistemaDAO extends DefaultDAO {
    
    public AcsEmpresaSistema getById(int Id) throws SQLException {
        getSession();
        begin();
        AcsEmpresaSistema empresaSistema;
        empresaSistema = (AcsEmpresaSistema) session.get(AcsEmpresaSistema.class, Id);
        return empresaSistema;
    }
    
    public List<AcsEmpresaSistema> listAllTipoNegocios() throws SQLException {
        getSession();
        begin();
        List<AcsEmpresaSistema> empresaSistemas;
        empresaSistemas = session.createQuery("from AcsEmpresaSistema").list();
        return empresaSistemas;
    }
    
    public List<AcsEmpresaSistema> listEmpresaSistemaByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<AcsEmpresaSistema> empresaSistemas;
        empresaSistemas = session.createQuery("from AcsEmpresaSistema u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return empresaSistemas;
    }
    
    public List<AcsEmpresaSistema> listEmpresaSistemaBySistema(PubSistema sistema) throws SQLException {
        getSession();
        begin();
        List<AcsEmpresaSistema> empresaSistemas;
        empresaSistemas = session.createQuery("from AcsEmpresaSistema u where u.pubSistema.idSistema = :sis").setParameter("sis", sistema.getIdSistema()).list();
        return empresaSistemas;
    }
    
    public void inserirEmpresaSistema (AcsEmpresaSistema empresaSistema) throws SQLException {
        getSession();
        begin();
        session.save(empresaSistema);
        commit();
    }
    
    public void atualizarEmpresaSistema (AcsEmpresaSistema empresaSistema) throws SQLException {
        getSession();
        begin();
        session.merge(empresaSistema);
        commit();
        closeSession();
    }
    
    
    
}
