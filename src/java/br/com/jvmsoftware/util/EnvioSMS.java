/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jvmsoftware.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author jose
 */
public class EnvioSMS { 
      
      
        public static void main(String[] args) throws Exception{  
              
            String urlParameters = "user=jrsiesler" +   
                    "&password=Matheus1108" +   
                    "&destinatario=19993189872" +  
                    "&msg=" + URLEncoder.encode("sua mensagem vai aqui", "UTF-8");;  
              
            URL url = new URL("http://www.facilitamovel.com/api/simpleSend.ft?");   
              
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();             
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setInstanceFollowRedirects(false);   
            connection.setRequestMethod("POST");   
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");   
            connection.setRequestProperty("charset", "utf-8");  
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));  
            connection.setUseCaches (false);  
      
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());  
            wr.write(urlParameters);  
            wr.flush();  
              
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
            StringBuffer strRet = new StringBuffer();  
            String line;  
            while ((line = rd.readLine()) != null) {  
                strRet.append(line);  
            }  
            wr.close();  
            rd.close();  
              
            System.out.println("Retorno da Chamada HTTP:"+ strRet);  
              
              
      
        }  
}