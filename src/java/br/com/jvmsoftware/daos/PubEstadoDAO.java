/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEstado;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubEstadoDAO extends DefaultDAO {
    
    public PubEstado getById(int Id) throws SQLException {
        getSession();
        begin();
        PubEstado estado;
        estado = (PubEstado) session.get(PubEstado.class, Id);
        return estado;
    }
    
    public List<PubEstado> listAllEstados() throws SQLException {
        getSession();
        begin();
        List<PubEstado> estados;
        estados = session.createQuery("from PubEstado").list();
        return estados;
    }
    
}
