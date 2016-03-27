/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jose
 */
@FacesConverter(value="convertPhone")
public class ConverterPhone implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
        if (valor != null || valor != "") {
            valor = valor.toString().replaceAll("[- /.]", "");
            valor = valor.toString().replaceAll("[-()]", "");
        }
        // se telefone resicencial (10 digitos)
        // preenche com espaço a esquerda
        if (valor.length() == 10) {
            valor = valor + " ";
        }
        return valor;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
        return valor.toString();
    }
    
}
