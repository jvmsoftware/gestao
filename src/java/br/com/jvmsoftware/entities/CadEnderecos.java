package br.com.jvmsoftware.entities;
// Generated 31/Mar/2016 22:28:08 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * CadEnderecos generated by hbm2java
 */
public class CadEnderecos  implements java.io.Serializable {


     private Integer idEndereco;
     private CadPessoa cadPessoa;
     private PubEstado pubEstado;
     private PubMunicipio pubMunicipio;
     private PubTipoEndereco pubTipoEndereco;
     private String endereco;
     private Integer numero;
     private String bairro;
     private String cep;
     private String complemento;
     private Set<CadPessoa> cadPessoas = new HashSet<CadPessoa>(0);

    public CadEnderecos() {
    }

	
    public CadEnderecos(CadPessoa cadPessoa, PubTipoEndereco pubTipoEndereco) {
        this.cadPessoa = cadPessoa;
        this.pubTipoEndereco = pubTipoEndereco;
    }
    public CadEnderecos(CadPessoa cadPessoa, PubEstado pubEstado, PubMunicipio pubMunicipio, PubTipoEndereco pubTipoEndereco, String endereco, Integer numero, String bairro, String cep, String complemento, Set<CadPessoa> cadPessoas) {
       this.cadPessoa = cadPessoa;
       this.pubEstado = pubEstado;
       this.pubMunicipio = pubMunicipio;
       this.pubTipoEndereco = pubTipoEndereco;
       this.endereco = endereco;
       this.numero = numero;
       this.bairro = bairro;
       this.cep = cep;
       this.complemento = complemento;
       this.cadPessoas = cadPessoas;
    }
   
    public Integer getIdEndereco() {
        return this.idEndereco;
    }
    
    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }
    public CadPessoa getCadPessoa() {
        return this.cadPessoa;
    }
    
    public void setCadPessoa(CadPessoa cadPessoa) {
        this.cadPessoa = cadPessoa;
    }
    public PubEstado getPubEstado() {
        return this.pubEstado;
    }
    
    public void setPubEstado(PubEstado pubEstado) {
        this.pubEstado = pubEstado;
    }
    public PubMunicipio getPubMunicipio() {
        return this.pubMunicipio;
    }
    
    public void setPubMunicipio(PubMunicipio pubMunicipio) {
        this.pubMunicipio = pubMunicipio;
    }
    public PubTipoEndereco getPubTipoEndereco() {
        return this.pubTipoEndereco;
    }
    
    public void setPubTipoEndereco(PubTipoEndereco pubTipoEndereco) {
        this.pubTipoEndereco = pubTipoEndereco;
    }
    public String getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getComplemento() {
        return this.complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public Set<CadPessoa> getCadPessoas() {
        return this.cadPessoas;
    }
    
    public void setCadPessoas(Set<CadPessoa> cadPessoas) {
        this.cadPessoas = cadPessoas;
    }




}


