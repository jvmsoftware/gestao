/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsEmpresaFuncionalidade;
import br.com.jvmsoftware.entities.AcsUsuarioFuncionalidade;
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
public class PubFuncionalidadeDAO extends DefaultDAO {
    
    public PubFuncionalidade getById(int Id) throws SQLException {
        getSession();
        begin();
        PubFuncionalidade funcionalidade;
        funcionalidade = (PubFuncionalidade) session.get(PubFuncionalidade.class, Id);
        closeSession();
        return funcionalidade;
    }
    
    public List<PubFuncionalidade> listAllFuncionalidades() throws SQLException {
        getSession();
        begin();
        List<PubFuncionalidade> funcionalidades;
        funcionalidades = session.createQuery("from PubFuncionalidade f order by f.pubSistema.nomeSistema, f.descricaoFuncionalidade").list();
        return funcionalidades;
    }
    
    public List<PubFuncionalidade> listFuncionalidadesBySistema(PubSistema sis) throws SQLException {
        getSession();
        begin();
        List<PubFuncionalidade> funcionalidades;
        funcionalidades = session.createQuery("from PubFuncionalidade f where f.pubSistema.idSistema = :sis order by f.pubSistema.nomeSistema, f.descricaoFuncionalidade").setParameter("sis", sis.getIdSistema()).list();
        return funcionalidades;
    }
    
    public void inserirFuncionalidade (PubFuncionalidade funcionalidade) throws SQLException {
        getSession();
        begin();
        session.save(funcionalidade);
        commit();
        closeSession();
        triggerEmpresa(funcionalidade);
        triggerUsuario(funcionalidade);
    }
    
    public void atualizarFuncionalidade (PubFuncionalidade funcionalidade) throws SQLException {
        getSession();
        begin();
        session.merge(funcionalidade);
        commit();
        closeSession();
    }

    private void triggerEmpresa(PubFuncionalidade f) throws SQLException {
        AcsEmpresaFuncionalidadeDAO relEmpDAO = new AcsEmpresaFuncionalidadeDAO();
        PubEmpresaDAO empDAO = new PubEmpresaDAO();
        List<PubEmpresa> listEmpresa;
        listEmpresa = empDAO.listAllEmpresas();
        for (PubEmpresa listEmpresa1 : listEmpresa) {
            AcsEmpresaFuncionalidade rel = new AcsEmpresaFuncionalidade();
            rel.setPubEmpresa(listEmpresa1);
            rel.setPubFuncionalidade(f);
            rel.setDesabilitado(false);
            relEmpDAO.inserirEmpresaFuncionalidade(rel);
        }
    }
    
    private void triggerUsuario(PubFuncionalidade f) throws SQLException {
        AcsUsuarioFuncionalidadeDAO relUsuDAO = new AcsUsuarioFuncionalidadeDAO();
        PubUsuarioDAO usuDAO = new PubUsuarioDAO();
        List<PubUsuario> listUsuario;
        listUsuario = usuDAO.listAllUsuarios();
        for (PubUsuario listUsuario1 : listUsuario) {
            AcsUsuarioFuncionalidade rel = new AcsUsuarioFuncionalidade();
            rel.setPubUsuario(listUsuario1);
            rel.setPubEmpresa(listUsuario1.getPubEmpresa());
            rel.setPubFuncionalidade(f);
            rel.setDesabilitado(false);
            relUsuDAO.inserirUsuarioFuncionalidade(rel);
        }
    }
    
}
