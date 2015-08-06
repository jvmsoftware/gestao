/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.AcsAcessoUsuarios;
import br.com.jvmsoftware.entities.PubUsuario;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class AcsAcessoUsuariosDAO extends DefaultDAO {
    
    public AcsAcessoUsuarios getById(int Id) throws SQLException {
        getSession();
        begin();
        AcsAcessoUsuarios acessoUsuarios;
        acessoUsuarios = (AcsAcessoUsuarios) session.get(AcsAcessoUsuarios.class, Id);
        return acessoUsuarios;
    }
    
    public List<AcsAcessoUsuarios> listAllTipoNegocios() throws SQLException {
        getSession();
        begin();
        List<AcsAcessoUsuarios> acessoUsuarioss;
        acessoUsuarioss = session.createQuery("from AcsAcessoUsuarios").list();
        return acessoUsuarioss;
    }
    
    public List<AcsAcessoUsuarios> listAcessoUsuariosByUsuario(PubUsuario usuario) throws SQLException {
        getSession();
        begin();
        List<AcsAcessoUsuarios> acessoUsuarioss;
        acessoUsuarioss = session.createQuery("from AcsAcessoUsuarios u where u.usuario = :usr").setParameter("usr", usuario.getIdUsuario()).list();
        return acessoUsuarioss;
    }
    
    public void inserirTipoNegocio (AcsAcessoUsuarios acessoUsuarios) throws SQLException {
        getSession();
        begin();
        session.save(acessoUsuarios);
        commit();
        closeSession();
    }
    
    public void atualizarTipoNegocio (AcsAcessoUsuarios acessoUsuarios) throws SQLException {
        getSession();
        begin();
        session.merge(acessoUsuarios);
        commit();
        closeSession();
    }
    
}
