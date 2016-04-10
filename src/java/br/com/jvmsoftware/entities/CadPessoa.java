package br.com.jvmsoftware.entities;
// Generated 9/Abr/2016 18:34:16 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CadPessoa generated by hbm2java
 */
public class CadPessoa  implements java.io.Serializable {


     private Integer idPessoa;
     private CadEnderecos cadEnderecos;
     private PubEmpresa pubEmpresa;
     private PubTipoCadastro pubTipoCadastro;
     private Date dataCadastro;
     private String cpfCnpj;
     private String nomeRazaoSocial;
     private String apelidoFantasia;
     private String emailResponsavel;
     private String telefone;
     private String telefone1;
     private boolean cliente;
     private boolean fornecedor;
     private boolean funcionario;
     private boolean transportador;
     private Set<CadEquipamentos> cadEquipamentoses = new HashSet<CadEquipamentos>(0);
     private Set<CadImoveis> cadImoveises = new HashSet<CadImoveis>(0);
     private Set<CadVeiculos> cadVeiculoses = new HashSet<CadVeiculos>(0);
     private Set<CadEnderecos> cadEnderecoses = new HashSet<CadEnderecos>(0);
     private Set<CadRelServFornec> cadRelServFornecs = new HashSet<CadRelServFornec>(0);
     private Set<CadRelProdFornec> cadRelProdFornecs = new HashSet<CadRelProdFornec>(0);

    public CadPessoa() {
    }

	
    public CadPessoa(PubEmpresa pubEmpresa, PubTipoCadastro pubTipoCadastro, String nomeRazaoSocial, String apelidoFantasia, boolean cliente, boolean fornecedor, boolean funcionario, boolean transportador) {
        this.pubEmpresa = pubEmpresa;
        this.pubTipoCadastro = pubTipoCadastro;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.apelidoFantasia = apelidoFantasia;
        this.cliente = cliente;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
        this.transportador = transportador;
    }
    public CadPessoa(CadEnderecos cadEnderecos, PubEmpresa pubEmpresa, PubTipoCadastro pubTipoCadastro, Date dataCadastro, String cpfCnpj, String nomeRazaoSocial, String apelidoFantasia, String emailResponsavel, String telefone, String telefone1, boolean cliente, boolean fornecedor, boolean funcionario, boolean transportador, Set<CadEquipamentos> cadEquipamentoses, Set<CadImoveis> cadImoveises, Set<CadVeiculos> cadVeiculoses, Set<CadEnderecos> cadEnderecoses, Set<CadRelServFornec> cadRelServFornecs, Set<CadRelProdFornec> cadRelProdFornecs) {
       this.cadEnderecos = cadEnderecos;
       this.pubEmpresa = pubEmpresa;
       this.pubTipoCadastro = pubTipoCadastro;
       this.dataCadastro = dataCadastro;
       this.cpfCnpj = cpfCnpj;
       this.nomeRazaoSocial = nomeRazaoSocial;
       this.apelidoFantasia = apelidoFantasia;
       this.emailResponsavel = emailResponsavel;
       this.telefone = telefone;
       this.telefone1 = telefone1;
       this.cliente = cliente;
       this.fornecedor = fornecedor;
       this.funcionario = funcionario;
       this.transportador = transportador;
       this.cadEquipamentoses = cadEquipamentoses;
       this.cadImoveises = cadImoveises;
       this.cadVeiculoses = cadVeiculoses;
       this.cadEnderecoses = cadEnderecoses;
       this.cadRelServFornecs = cadRelServFornecs;
       this.cadRelProdFornecs = cadRelProdFornecs;
    }
   
    public Integer getIdPessoa() {
        return this.idPessoa;
    }
    
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }
    public CadEnderecos getCadEnderecos() {
        return this.cadEnderecos;
    }
    
    public void setCadEnderecos(CadEnderecos cadEnderecos) {
        this.cadEnderecos = cadEnderecos;
    }
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }
    public PubTipoCadastro getPubTipoCadastro() {
        return this.pubTipoCadastro;
    }
    
    public void setPubTipoCadastro(PubTipoCadastro pubTipoCadastro) {
        this.pubTipoCadastro = pubTipoCadastro;
    }
    public Date getDataCadastro() {
        return this.dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public String getCpfCnpj() {
        return this.cpfCnpj;
    }
    
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
    public String getNomeRazaoSocial() {
        return this.nomeRazaoSocial;
    }
    
    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }
    public String getApelidoFantasia() {
        return this.apelidoFantasia;
    }
    
    public void setApelidoFantasia(String apelidoFantasia) {
        this.apelidoFantasia = apelidoFantasia;
    }
    public String getEmailResponsavel() {
        return this.emailResponsavel;
    }
    
    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone1() {
        return this.telefone1;
    }
    
    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }
    public boolean getCliente() {
        return this.cliente;
    }
    
    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }
    public boolean isFornecedor() {
        return this.fornecedor;
    }
    
    public void setFornecedor(boolean fornecedor) {
        this.fornecedor = fornecedor;
    }
    public boolean isFuncionario() {
        return this.funcionario;
    }
    
    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }
    public boolean getTransportador() {
        return this.transportador;
    }
    
    public void setTransportador(boolean transportador) {
        this.transportador = transportador;
    }
    public Set<CadEquipamentos> getCadEquipamentoses() {
        return this.cadEquipamentoses;
    }
    
    public void setCadEquipamentoses(Set<CadEquipamentos> cadEquipamentoses) {
        this.cadEquipamentoses = cadEquipamentoses;
    }
    public Set<CadImoveis> getCadImoveises() {
        return this.cadImoveises;
    }
    
    public void setCadImoveises(Set<CadImoveis> cadImoveises) {
        this.cadImoveises = cadImoveises;
    }
    public Set<CadVeiculos> getCadVeiculoses() {
        return this.cadVeiculoses;
    }
    
    public void setCadVeiculoses(Set<CadVeiculos> cadVeiculoses) {
        this.cadVeiculoses = cadVeiculoses;
    }
    public Set<CadEnderecos> getCadEnderecoses() {
        return this.cadEnderecoses;
    }
    
    public void setCadEnderecoses(Set<CadEnderecos> cadEnderecoses) {
        this.cadEnderecoses = cadEnderecoses;
    }
    public Set<CadRelServFornec> getCadRelServFornecs() {
        return this.cadRelServFornecs;
    }
    
    public void setCadRelServFornecs(Set<CadRelServFornec> cadRelServFornecs) {
        this.cadRelServFornecs = cadRelServFornecs;
    }
    public Set<CadRelProdFornec> getCadRelProdFornecs() {
        return this.cadRelProdFornecs;
    }
    
    public void setCadRelProdFornecs(Set<CadRelProdFornec> cadRelProdFornecs) {
        this.cadRelProdFornecs = cadRelProdFornecs;
    }




}


