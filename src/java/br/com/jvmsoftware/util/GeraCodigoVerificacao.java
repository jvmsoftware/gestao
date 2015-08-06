/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.util;

import java.util.Random;
import java.util.UUID;

/**
 *
 * @author jose
 */
public class GeraCodigoVerificacao {
    
    Random gerador = new Random();
    
    public String geraCodigo() {
        UUID uuid = UUID.randomUUID();  
        String myRandom = uuid.toString();  
        System.out.println(myRandom.substring(0,8)); 
        return myRandom.substring(0,8);
    }
    
}
