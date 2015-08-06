/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubMenu;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubMenuDAO extends DefaultDAO {
    
    public PubMenu getById(int Id) throws SQLException {
        getSession();
        begin();
        PubMenu menu;
        menu = (PubMenu) session.get(PubMenu.class, Id);
        return menu;
    }
    
    public List<PubMenu> listBarraMenu() throws SQLException {
        getSession();
        begin();
        List<PubMenu> menus;
        menus = session.createQuery("from PubMenu m where m.pubMenu.idMenu is null").list();
        return menus;
    }
    
    public List<PubMenu> listItensMenu(PubMenu menu) throws SQLException {
        getSession();
        begin();
        List<PubMenu> menus;
        menus = session.createQuery("from PubMenu m where m.pubMenu.idMenu = :id").setParameter("id", menu.getIdMenu()).list();
        return menus;
    }
    
    // ** MenuModel **
    public List<PubMenu> listaTodos() {
        getSession();
        begin();
        List<PubMenu> listaMenu;
        listaMenu = session.createQuery("from PubMenu").list();
        return listaMenu;
    }
}
