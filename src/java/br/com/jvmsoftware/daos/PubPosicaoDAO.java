/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubPosicao;
import br.com.jvmsoftware.util.HibernateUtil;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author jose
 */
public class PubPosicaoDAO{
    
    /*
    public PubPosicao getById(int Id) throws SQLException {
        getSession();
        begin();
        PubPosicao posicao;
        posicao = (PubPosicao) session.get(PubPosicao.class, Id);
        return posicao;
    }
    
    public List<PubPosicao> listAllPosicoes() throws SQLException {
        getSession();
        begin();
        List<PubPosicao> posicoes;
        posicoes = session.createQuery("from PubPosicao").list();
        return posicoes;
    }
    */
        public void inserirPosicao (PubPosicao pos) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(pos);
        session.flush();
        session.beginTransaction().commit();
        session.close();
    }
    
}
