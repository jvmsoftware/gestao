package br.com.jvmsoftware.entities;
// Generated 31/Mar/2016 22:28:08 by Hibernate Tools 4.3.1



/**
 * CadRelServFornec generated by hbm2java
 */
public class CadRelServFornec  implements java.io.Serializable {


     private Integer idRel;
     private CadPessoa cadPessoa;
     private CadServicos cadServicos;
     private PubEmpresa pubEmpresa;
     private Double precoServico;

    public CadRelServFornec() {
    }

	
    public CadRelServFornec(CadPessoa cadPessoa, CadServicos cadServicos, PubEmpresa pubEmpresa) {
        this.cadPessoa = cadPessoa;
        this.cadServicos = cadServicos;
        this.pubEmpresa = pubEmpresa;
    }
    public CadRelServFornec(CadPessoa cadPessoa, CadServicos cadServicos, PubEmpresa pubEmpresa, Double precoServico) {
       this.cadPessoa = cadPessoa;
       this.cadServicos = cadServicos;
       this.pubEmpresa = pubEmpresa;
       this.precoServico = precoServico;
    }
   
    public Integer getIdRel() {
        return this.idRel;
    }
    
    public void setIdRel(Integer idRel) {
        this.idRel = idRel;
    }
    public CadPessoa getCadPessoa() {
        return this.cadPessoa;
    }
    
    public void setCadPessoa(CadPessoa cadPessoa) {
        this.cadPessoa = cadPessoa;
    }
    public CadServicos getCadServicos() {
        return this.cadServicos;
    }
    
    public void setCadServicos(CadServicos cadServicos) {
        this.cadServicos = cadServicos;
    }
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }
    public Double getPrecoServico() {
        return this.precoServico;
    }
    
    public void setPrecoServico(Double precoServico) {
        this.precoServico = precoServico;
    }




}

