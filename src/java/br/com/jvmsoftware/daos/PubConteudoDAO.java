/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubConteudo;
import br.com.jvmsoftware.entities.PubMenu;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubConteudoDAO extends DefaultDAO {
    
    public PubConteudo getById(int Id) throws SQLException {
        getSession();
        begin();
        PubConteudo conteudo;
        conteudo = (PubConteudo) session.get(PubConteudo.class, Id);
        return conteudo;
    }
    
    public List<PubConteudo> listAllConteudos() throws SQLException {
        getSession();
        begin();
        List<PubConteudo> conteudos;
        conteudos = session.createQuery("from PubConteudo").list();
        return conteudos;
    }
    
    public List<PubConteudo> listByMenu(PubMenu menu) throws SQLException {
        getSession();
        begin();
        List<PubConteudo> conteudos;
        conteudos = session.createQuery("from PubConteudo c where c.pubMenu.idMenu = :id").setParameter("id", menu.getIdMenu()).list();
        return conteudos;
    }
    
    public void inserirConteudo (PubConteudo conteudo) throws SQLException {
        getSession();
        begin();
        session.save(conteudo);
        commit();
        closeSession();
    }
    
    public void atualizarConteudo (PubConteudo conteudo) throws SQLException {
        getSession();
        begin();
        session.merge(conteudo);
        commit();
        closeSession();
    }
    
}
