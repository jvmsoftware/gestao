/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.daos;

import br.com.jvmsoftware.entities.PubEstado;
import br.com.jvmsoftware.entities.PubMunicipio;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jose
 */
public class PubMunicipioDAO extends DefaultDAO {
    
    public PubMunicipio getById(int Id) throws SQLException {
        getSession();
        begin();
        PubMunicipio municipio;
        municipio = (PubMunicipio) session.get(PubMunicipio.class, Id);
        return municipio;
    }
    
    public List<PubMunicipio> listAllMunicipios() throws SQLException {
        getSession();
        begin();
        List<PubMunicipio> municipios;
        municipios = session.createQuery("from PubMunicipio").list();
        return municipios;
    }
    
    public List<PubMunicipio> listMunicipiosByEstado(PubEstado estado) throws SQLException {
        getSession();
        begin();
        List<PubMunicipio> municipios;
        municipios = session.createQuery("from PubMunicipio m where m.pubEstado = :est").setParameter("est", estado).list();
        return municipios;
    }
    
}
