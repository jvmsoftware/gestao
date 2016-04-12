/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsEmpresaFuncionalidade;
import br.com.jvmsoftware.entities.AcsEmpresaSistema;
import br.com.jvmsoftware.entities.AcsUsuarioSistema;
import br.com.jvmsoftware.entities.PubEmpresa;
import br.com.jvmsoftware.entities.PubFuncionalidade;
import br.com.jvmsoftware.entities.PubSistema;
import br.com.jvmsoftware.entities.PubUsuario;
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
        triggerSistema(empresa);
        triggerFuncionalidade(empresa);
        closeSession();
    }
    
    public void atualizarEmpresa (PubEmpresa empresa) throws SQLException {
        getSession();
        begin();
        session.merge(empresa);
        commit();
        closeSession();
        
    }
    
    private void triggerSistema(PubEmpresa e) throws SQLException {
        AcsEmpresaSistemaDAO relEmpDAO = new AcsEmpresaSistemaDAO();
        PubSistemaDAO sisDAO = new PubSistemaDAO();
        List<PubSistema> listSistema;
        listSistema = sisDAO.listAllSistemas();
        for (PubSistema listSistema1 : listSistema) {
            AcsEmpresaSistema rel = new AcsEmpresaSistema();
            rel.setPubEmpresa(e);
            rel.setPubSistema(listSistema1);
            rel.setAtivo(false);
            relEmpDAO.inserirEmpresaSistema(rel);
        }
    }
    
    private void triggerFuncionalidade(PubEmpresa s) throws SQLException {
        AcsEmpresaFuncionalidadeDAO relUsuDAO = new AcsEmpresaFuncionalidadeDAO();
        PubFuncionalidadeDAO funDAO = new PubFuncionalidadeDAO();
        List<PubFuncionalidade> listFuncionalidades;
        listFuncionalidades = funDAO.listAllFuncionalidades();
        for (PubFuncionalidade listFuncionalidades1 : listFuncionalidades) {
            // permissoes
            Boolean view = null;
            Boolean add = null;
            Boolean edit = null;
            Boolean delete = null;
            if (!"0".equals(listFuncionalidades1.getCrudDefault())) {
                view = false;
                add = false;
                edit = false;
                delete = false;
            } else if (!"1".equals(listFuncionalidades1.getCrudDefault())) {
                view = true;
                add = false;
                edit = false;
                delete = false;
            } else if (!"2".equals(listFuncionalidades1.getCrudDefault())) {
                view = true;
                add = true;
                edit = true;
                delete = false;
            } else if (!"3".equals(listFuncionalidades1.getCrudDefault())) {
                view = true;
                add = true;
                edit = true;
                delete = true;
            }
            // relacionamento
            AcsEmpresaFuncionalidade rel = new AcsEmpresaFuncionalidade();
            rel.setPubEmpresa(s);
            rel.setPubFuncionalidade(listFuncionalidades1);
            rel.setView(view);
            rel.setAdd(add);
            rel.setEdit(edit);
            rel.setDelete(delete);
            rel.setProcess(listFuncionalidades1.getProcess());
            relUsuDAO.inserirEmpresaFuncionalidade(rel);
        }
    }
    
}
