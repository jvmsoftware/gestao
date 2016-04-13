package br.com.jvmsoftware.entities;
// Generated 12/Abr/2016 22:07:07 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ServOrdemServico generated by hbm2java
 */
@Entity
@Table(name="serv_ordem_servico"
    ,catalog="jvmdsoftware"
)
public class ServOrdemServico  implements java.io.Serializable {


     private Integer idOrdem;
     private CadEquipamentos cadEquipamentos;
     private CadImoveis cadImoveis;
     private CadPessoa cadPessoa;
     private CadVeiculos cadVeiculos;
     private PubUsuario pubUsuario;
     private WflWorkflow wflWorkflow;
     private int empresa;
     private Date dataHora;
     private String observacao;

    public ServOrdemServico() {
    }

	
    public ServOrdemServico(PubUsuario pubUsuario, WflWorkflow wflWorkflow, int empresa, Date dataHora) {
        this.pubUsuario = pubUsuario;
        this.wflWorkflow = wflWorkflow;
        this.empresa = empresa;
        this.dataHora = dataHora;
    }
    public ServOrdemServico(CadEquipamentos cadEquipamentos, CadImoveis cadImoveis, CadPessoa cadPessoa, CadVeiculos cadVeiculos, PubUsuario pubUsuario, WflWorkflow wflWorkflow, int empresa, Date dataHora, String observacao) {
       this.cadEquipamentos = cadEquipamentos;
       this.cadImoveis = cadImoveis;
       this.cadPessoa = cadPessoa;
       this.cadVeiculos = cadVeiculos;
       this.pubUsuario = pubUsuario;
       this.wflWorkflow = wflWorkflow;
       this.empresa = empresa;
       this.dataHora = dataHora;
       this.observacao = observacao;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_ordem", unique=true, nullable=false)
    public Integer getIdOrdem() {
        return this.idOrdem;
    }
    
    public void setIdOrdem(Integer idOrdem) {
        this.idOrdem = idOrdem;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="equipamento_cliente")
    public CadEquipamentos getCadEquipamentos() {
        return this.cadEquipamentos;
    }
    
    public void setCadEquipamentos(CadEquipamentos cadEquipamentos) {
        this.cadEquipamentos = cadEquipamentos;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="imovel_cliente")
    public CadImoveis getCadImoveis() {
        return this.cadImoveis;
    }
    
    public void setCadImoveis(CadImoveis cadImoveis) {
        this.cadImoveis = cadImoveis;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente")
    public CadPessoa getCadPessoa() {
        return this.cadPessoa;
    }
    
    public void setCadPessoa(CadPessoa cadPessoa) {
        this.cadPessoa = cadPessoa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="veiculo_cliente")
    public CadVeiculos getCadVeiculos() {
        return this.cadVeiculos;
    }
    
    public void setCadVeiculos(CadVeiculos cadVeiculos) {
        this.cadVeiculos = cadVeiculos;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario", nullable=false)
    public PubUsuario getPubUsuario() {
        return this.pubUsuario;
    }
    
    public void setPubUsuario(PubUsuario pubUsuario) {
        this.pubUsuario = pubUsuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="workflow", nullable=false)
    public WflWorkflow getWflWorkflow() {
        return this.wflWorkflow;
    }
    
    public void setWflWorkflow(WflWorkflow wflWorkflow) {
        this.wflWorkflow = wflWorkflow;
    }

    
    @Column(name="empresa", nullable=false)
    public int getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_hora", nullable=false, length=19)
    public Date getDataHora() {
        return this.dataHora;
    }
    
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    
    @Column(name="observacao")
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }




}


