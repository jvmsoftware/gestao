package br.com.jvmsoftware.entities;
// Generated 4/Ago/2015 22:25:45 by Hibernate Tools 4.3.1



/**
 * CadPessoaJuridica generated by hbm2java
 */
public class CadPessoaJuridica  implements java.io.Serializable {


     private Integer idPessoaJuridica;
     private CadPessoa cadPessoa;
     private PubEstado pubEstado;
     private PubMunicipio pubMunicipio;
     private Float cnpjEmpresa;
     private String razaoSocial;
     private String fantasia;
     private Float cep;
     private String endereco;
     private Float numero;
     private String bairro;
     private String complemento;
     private int tipoCadastro;

    public CadPessoaJuridica() {
    }

	
    public CadPessoaJuridica(CadPessoa cadPessoa, PubEstado pubEstado, PubMunicipio pubMunicipio, String razaoSocial, String fantasia, int tipoCadastro) {
        this.cadPessoa = cadPessoa;
        this.pubEstado = pubEstado;
        this.pubMunicipio = pubMunicipio;
        this.razaoSocial = razaoSocial;
        this.fantasia = fantasia;
        this.tipoCadastro = tipoCadastro;
    }
    public CadPessoaJuridica(CadPessoa cadPessoa, PubEstado pubEstado, PubMunicipio pubMunicipio, Float cnpjEmpresa, String razaoSocial, String fantasia, Float cep, String endereco, Float numero, String bairro, String complemento, int tipoCadastro) {
       this.cadPessoa = cadPessoa;
       this.pubEstado = pubEstado;
       this.pubMunicipio = pubMunicipio;
       this.cnpjEmpresa = cnpjEmpresa;
       this.razaoSocial = razaoSocial;
       this.fantasia = fantasia;
       this.cep = cep;
       this.endereco = endereco;
       this.numero = numero;
       this.bairro = bairro;
       this.complemento = complemento;
       this.tipoCadastro = tipoCadastro;
    }
   
    public Integer getIdPessoaJuridica() {
        return this.idPessoaJuridica;
    }
    
    public void setIdPessoaJuridica(Integer idPessoaJuridica) {
        this.idPessoaJuridica = idPessoaJuridica;
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
    public Float getCnpjEmpresa() {
        return this.cnpjEmpresa;
    }
    
    public void setCnpjEmpresa(Float cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }
    public String getRazaoSocial() {
        return this.razaoSocial;
    }
    
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getFantasia() {
        return this.fantasia;
    }
    
    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }
    public Float getCep() {
        return this.cep;
    }
    
    public void setCep(Float cep) {
        this.cep = cep;
    }
    public String getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public Float getNumero() {
        return this.numero;
    }
    
    public void setNumero(Float numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getComplemento() {
        return this.complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public int getTipoCadastro() {
        return this.tipoCadastro;
    }
    
    public void setTipoCadastro(int tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }




}


