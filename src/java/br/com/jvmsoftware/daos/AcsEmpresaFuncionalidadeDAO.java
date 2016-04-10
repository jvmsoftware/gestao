/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsEmpresaFuncionalidade;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubSistema;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class AcsEmpresaFuncionalidadeDAO extends DefaultDAO {
    
    public AcsEmpresaFuncionalidade getById(int Id) throws SQLException {
        getSession();
        begin();
        AcsEmpresaFuncionalidade empresaFuncionalidade;
        empresaFuncionalidade = (AcsEmpresaFuncionalidade) session.get(AcsEmpresaFuncionalidade.class, Id);
        return empresaFuncionalidade;
    }
    
    public List<AcsEmpresaFuncionalidade> listAllTipoNegocios() throws SQLException {
        getSession();
        begin();
        List<AcsEmpresaFuncionalidade> empresaFuncionalidades;
        empresaFuncionalidades = session.createQuery("from AcsEmpresaFuncionalidade").list();
        return empresaFuncionalidades;
    }
    
    public List<AcsEmpresaFuncionalidade> listEmpresaFuncionalidadeByEmpresa(PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        List<AcsEmpresaFuncionalidade> empresaFuncionalidades;
        empresaFuncionalidades = session.createQuery("from AcsEmpresaFuncionalidade u where u.pubEmpresa.idEmpresa = :emp").setParameter("emp", empresa.getIdEmpresa()).list();
        return empresaFuncionalidades;
    }
    
    public List<AcsEmpresaFuncionalidade> listEmpresaFuncionalidadeBySistema(PubSistema sistema) throws SQLException {
        getSession();
        begin();
        List<AcsEmpresaFuncionalidade> empresaFuncionalidades;
        empresaFuncionalidades = session.createQuery("from AcsEmpresaFuncionalidade u where u.pubSistema.idSistema = :sis").setParameter("sis", sistema.getIdSistema()).list();
        return empresaFuncionalidades;
    }
    
    public void inserirEmpresaFuncionalidade (AcsEmpresaFuncionalidade empresaFuncionalidade) throws SQLException {
        getSession();
        begin();
        session.save(empresaFuncionalidade);
        commit();
    }
    
    public void atualizarEmpresaFuncionalidade (AcsEmpresaFuncionalidade empresaFuncionalidade) throws SQLException {
        getSession();
        begin();
        session.merge(empresaFuncionalidade);
        commit();
        closeSession();
    }
    
    
    
}
