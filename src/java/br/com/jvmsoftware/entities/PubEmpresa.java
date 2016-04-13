package br.com.jvmsoftware.entities;
// Generated 12/Abr/2016 22:07:07 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PubEmpresa generated by hbm2java
 */
@Entity
@Table(name="pub_empresa"
    ,catalog="jvmdsoftware"
)
public class PubEmpresa  implements java.io.Serializable {


     private Integer idEmpresa;
     private PubEstado pubEstado;
     private PubMunicipio pubMunicipio;
     private PubTipoCadastro pubTipoCadastro;
     private String cnpjEmpresa;
     private String razaoSocial;
     private String fantasia;
     private Double cep;
     private String endereco;
     private Double numero;
     private String bairro;
     private String complemento;
     private Date dataCadastro;
     private boolean ativo;
     private Double telefone;
     private String emailResponsavel;
     private Set pubConfigEmpresas = new HashSet(0);
     private Set comCompraOrdems = new HashSet(0);
     private Set cadImoveises = new HashSet(0);
     private Set cadServicoses = new HashSet(0);
     private Set acsEmpresaFuncionalidades = new HashSet(0);
     private Set acsEmpresaSistemas = new HashSet(0);
     private Set acsUsuarioFuncionalidades = new HashSet(0);
     private Set cadRelServFornecs = new HashSet(0);
     private Set supProjetos = new HashSet(0);
     private Set wflSituacaos = new HashSet(0);
     private Set supSolicitacaos = new HashSet(0);
     private Set acsUsuarioSistemas = new HashSet(0);
     private Set wflWorkflowsForEmpresaDestino = new HashSet(0);
     private Set cadPessoas = new HashSet(0);
     private Set cadVeiculoses = new HashSet(0);
     private Set wflWorkflowEventos = new HashSet(0);
     private Set cadProdutos = new HashSet(0);
     private Set cadRelProdFornecs = new HashSet(0);
     private Set pubUsuarios = new HashSet(0);
     private Set cadEquipamentoses = new HashSet(0);
     private Set wflWorkflowsForEmpresaCriacao = new HashSet(0);

    public PubEmpresa() {
    }

	
    public PubEmpresa(PubEstado pubEstado, PubMunicipio pubMunicipio, PubTipoCadastro pubTipoCadastro, String razaoSocial, String fantasia, boolean ativo) {
        this.pubEstado = pubEstado;
        this.pubMunicipio = pubMunicipio;
        this.pubTipoCadastro = pubTipoCadastro;
        this.razaoSocial = razaoSocial;
        this.fantasia = fantasia;
        this.ativo = ativo;
    }
    public PubEmpresa(PubEstado pubEstado, PubMunicipio pubMunicipio, PubTipoCadastro pubTipoCadastro, String cnpjEmpresa, String razaoSocial, String fantasia, Double cep, String endereco, Double numero, String bairro, String complemento, Date dataCadastro, boolean ativo, Double telefone, String emailResponsavel, Set pubConfigEmpresas, Set comCompraOrdems, Set cadImoveises, Set cadServicoses, Set acsEmpresaFuncionalidades, Set acsEmpresaSistemas, Set acsUsuarioFuncionalidades, Set cadRelServFornecs, Set supProjetos, Set wflSituacaos, Set supSolicitacaos, Set acsUsuarioSistemas, Set wflWorkflowsForEmpresaDestino, Set cadPessoas, Set cadVeiculoses, Set wflWorkflowEventos, Set cadProdutos, Set cadRelProdFornecs, Set pubUsuarios, Set cadEquipamentoses, Set wflWorkflowsForEmpresaCriacao) {
       this.pubEstado = pubEstado;
       this.pubMunicipio = pubMunicipio;
       this.pubTipoCadastro = pubTipoCadastro;
       this.cnpjEmpresa = cnpjEmpresa;
       this.razaoSocial = razaoSocial;
       this.fantasia = fantasia;
       this.cep = cep;
       this.endereco = endereco;
       this.numero = numero;
       this.bairro = bairro;
       this.complemento = complemento;
       this.dataCadastro = dataCadastro;
       this.ativo = ativo;
       this.telefone = telefone;
       this.emailResponsavel = emailResponsavel;
       this.pubConfigEmpresas = pubConfigEmpresas;
       this.comCompraOrdems = comCompraOrdems;
       this.cadImoveises = cadImoveises;
       this.cadServicoses = cadServicoses;
       this.acsEmpresaFuncionalidades = acsEmpresaFuncionalidades;
       this.acsEmpresaSistemas = acsEmpresaSistemas;
       this.acsUsuarioFuncionalidades = acsUsuarioFuncionalidades;
       this.cadRelServFornecs = cadRelServFornecs;
       this.supProjetos = supProjetos;
       this.wflSituacaos = wflSituacaos;
       this.supSolicitacaos = supSolicitacaos;
       this.acsUsuarioSistemas = acsUsuarioSistemas;
       this.wflWorkflowsForEmpresaDestino = wflWorkflowsForEmpresaDestino;
       this.cadPessoas = cadPessoas;
       this.cadVeiculoses = cadVeiculoses;
       this.wflWorkflowEventos = wflWorkflowEventos;
       this.cadProdutos = cadProdutos;
       this.cadRelProdFornecs = cadRelProdFornecs;
       this.pubUsuarios = pubUsuarios;
       this.cadEquipamentoses = cadEquipamentoses;
       this.wflWorkflowsForEmpresaCriacao = wflWorkflowsForEmpresaCriacao;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_empresa", unique=true, nullable=false)
    public Integer getIdEmpresa() {
        return this.idEmpresa;
    }
    
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="estado", nullable=false)
    public PubEstado getPubEstado() {
        return this.pubEstado;
    }
    
    public void setPubEstado(PubEstado pubEstado) {
        this.pubEstado = pubEstado;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="municipio", nullable=false)
    public PubMunicipio getPubMunicipio() {
        return this.pubMunicipio;
    }
    
    public void setPubMunicipio(PubMunicipio pubMunicipio) {
        this.pubMunicipio = pubMunicipio;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tipo_cadastro", nullable=false)
    public PubTipoCadastro getPubTipoCadastro() {
        return this.pubTipoCadastro;
    }
    
    public void setPubTipoCadastro(PubTipoCadastro pubTipoCadastro) {
        this.pubTipoCadastro = pubTipoCadastro;
    }

    
    @Column(name="cnpj_empresa", length=15)
    public String getCnpjEmpresa() {
        return this.cnpjEmpresa;
    }
    
    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    
    @Column(name="razao_social", nullable=false, length=200)
    public String getRazaoSocial() {
        return this.razaoSocial;
    }
    
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    
    @Column(name="fantasia", nullable=false, length=100)
    public String getFantasia() {
        return this.fantasia;
    }
    
    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    
    @Column(name="cep", precision=22, scale=0)
    public Double getCep() {
        return this.cep;
    }
    
    public void setCep(Double cep) {
        this.cep = cep;
    }

    
    @Column(name="endereco", length=150)
    public String getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    
    @Column(name="numero", precision=22, scale=0)
    public Double getNumero() {
        return this.numero;
    }
    
    public void setNumero(Double numero) {
        this.numero = numero;
    }

    
    @Column(name="bairro", length=50)
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    
    @Column(name="complemento", length=150)
    public String getComplemento() {
        return this.complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="data_cadastro", length=10)
    public Date getDataCadastro() {
        return this.dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    
    @Column(name="ativo", nullable=false)
    public boolean isAtivo() {
        return this.ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    
    @Column(name="telefone", precision=22, scale=0)
    public Double getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(Double telefone) {
        this.telefone = telefone;
    }

    
    @Column(name="email_responsavel", length=50)
    public String getEmailResponsavel() {
        return this.emailResponsavel;
    }
    
    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getPubConfigEmpresas() {
        return this.pubConfigEmpresas;
    }
    
    public void setPubConfigEmpresas(Set pubConfigEmpresas) {
        this.pubConfigEmpresas = pubConfigEmpresas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getComCompraOrdems() {
        return this.comCompraOrdems;
    }
    
    public void setComCompraOrdems(Set comCompraOrdems) {
        this.comCompraOrdems = comCompraOrdems;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadImoveises() {
        return this.cadImoveises;
    }
    
    public void setCadImoveises(Set cadImoveises) {
        this.cadImoveises = cadImoveises;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadServicoses() {
        return this.cadServicoses;
    }
    
    public void setCadServicoses(Set cadServicoses) {
        this.cadServicoses = cadServicoses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getAcsEmpresaFuncionalidades() {
        return this.acsEmpresaFuncionalidades;
    }
    
    public void setAcsEmpresaFuncionalidades(Set acsEmpresaFuncionalidades) {
        this.acsEmpresaFuncionalidades = acsEmpresaFuncionalidades;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getAcsEmpresaSistemas() {
        return this.acsEmpresaSistemas;
    }
    
    public void setAcsEmpresaSistemas(Set acsEmpresaSistemas) {
        this.acsEmpresaSistemas = acsEmpresaSistemas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getAcsUsuarioFuncionalidades() {
        return this.acsUsuarioFuncionalidades;
    }
    
    public void setAcsUsuarioFuncionalidades(Set acsUsuarioFuncionalidades) {
        this.acsUsuarioFuncionalidades = acsUsuarioFuncionalidades;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadRelServFornecs() {
        return this.cadRelServFornecs;
    }
    
    public void setCadRelServFornecs(Set cadRelServFornecs) {
        this.cadRelServFornecs = cadRelServFornecs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getSupProjetos() {
        return this.supProjetos;
    }
    
    public void setSupProjetos(Set supProjetos) {
        this.supProjetos = supProjetos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getWflSituacaos() {
        return this.wflSituacaos;
    }
    
    public void setWflSituacaos(Set wflSituacaos) {
        this.wflSituacaos = wflSituacaos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getSupSolicitacaos() {
        return this.supSolicitacaos;
    }
    
    public void setSupSolicitacaos(Set supSolicitacaos) {
        this.supSolicitacaos = supSolicitacaos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getAcsUsuarioSistemas() {
        return this.acsUsuarioSistemas;
    }
    
    public void setAcsUsuarioSistemas(Set acsUsuarioSistemas) {
        this.acsUsuarioSistemas = acsUsuarioSistemas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresaByEmpresaDestino")
    public Set getWflWorkflowsForEmpresaDestino() {
        return this.wflWorkflowsForEmpresaDestino;
    }
    
    public void setWflWorkflowsForEmpresaDestino(Set wflWorkflowsForEmpresaDestino) {
        this.wflWorkflowsForEmpresaDestino = wflWorkflowsForEmpresaDestino;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadPessoas() {
        return this.cadPessoas;
    }
    
    public void setCadPessoas(Set cadPessoas) {
        this.cadPessoas = cadPessoas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadVeiculoses() {
        return this.cadVeiculoses;
    }
    
    public void setCadVeiculoses(Set cadVeiculoses) {
        this.cadVeiculoses = cadVeiculoses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getWflWorkflowEventos() {
        return this.wflWorkflowEventos;
    }
    
    public void setWflWorkflowEventos(Set wflWorkflowEventos) {
        this.wflWorkflowEventos = wflWorkflowEventos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadProdutos() {
        return this.cadProdutos;
    }
    
    public void setCadProdutos(Set cadProdutos) {
        this.cadProdutos = cadProdutos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadRelProdFornecs() {
        return this.cadRelProdFornecs;
    }
    
    public void setCadRelProdFornecs(Set cadRelProdFornecs) {
        this.cadRelProdFornecs = cadRelProdFornecs;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getPubUsuarios() {
        return this.pubUsuarios;
    }
    
    public void setPubUsuarios(Set pubUsuarios) {
        this.pubUsuarios = pubUsuarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresa")
    public Set getCadEquipamentoses() {
        return this.cadEquipamentoses;
    }
    
    public void setCadEquipamentoses(Set cadEquipamentoses) {
        this.cadEquipamentoses = cadEquipamentoses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubEmpresaByEmpresaCriacao")
    public Set getWflWorkflowsForEmpresaCriacao() {
        return this.wflWorkflowsForEmpresaCriacao;
    }
    
    public void setWflWorkflowsForEmpresaCriacao(Set wflWorkflowsForEmpresaCriacao) {
        this.wflWorkflowsForEmpresaCriacao = wflWorkflowsForEmpresaCriacao;
    }




}


