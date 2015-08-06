/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.sql.SQLException;
import org.hibernate.Session;

/**
 *
 * @author jose
 */
public class DefaultDAO implements Serializable {
    
    Session session;
    
    public void getSession() {
        //session = HibernateUtil.getSessionFactory().openSession();
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.getSessionFactory().openSession();
        } else {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
    }
    
    public void begin() {
        session.beginTransaction();
    }
    
    public void commit() {
        //session.flush();
        session.beginTransaction().commit();
    }
    
    public void rollback() {
        session.beginTransaction().rollback();
    }

    public void closeSession() throws SQLException {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        if (session.isOpen() || session == null) {
            session.close();
        }
    }
    
}
