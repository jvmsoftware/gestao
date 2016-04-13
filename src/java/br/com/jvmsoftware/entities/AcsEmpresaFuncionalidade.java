package br.com.jvmsoftware.entities;
// Generated 12/Abr/2016 22:07:07 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AcsEmpresaFuncionalidade generated by hbm2java
 */
@Entity
@Table(name="acs_empresa_funcionalidade"
    ,catalog="jvmdsoftware"
)
public class AcsEmpresaFuncionalidade  implements java.io.Serializable {


     private Integer id;
     private PubEmpresa pubEmpresa;
     private PubFuncionalidade pubFuncionalidade;
     private boolean view;
     private boolean add;
     private boolean edit;
     private boolean delete;
     private boolean process;

    public AcsEmpresaFuncionalidade() {
    }

    public AcsEmpresaFuncionalidade(PubEmpresa pubEmpresa, PubFuncionalidade pubFuncionalidade, boolean view, boolean add, boolean edit, boolean delete, boolean process) {
       this.pubEmpresa = pubEmpresa;
       this.pubFuncionalidade = pubFuncionalidade;
       this.view = view;
       this.add = add;
       this.edit = edit;
       this.delete = delete;
       this.process = process;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="empresa", nullable=false)
    public PubEmpresa getPubEmpresa() {
        return this.pubEmpresa;
    }
    
    public void setPubEmpresa(PubEmpresa pubEmpresa) {
        this.pubEmpresa = pubEmpresa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="funcionalidade", nullable=false)
    public PubFuncionalidade getPubFuncionalidade() {
        return this.pubFuncionalidade;
    }
    
    public void setPubFuncionalidade(PubFuncionalidade pubFuncionalidade) {
        this.pubFuncionalidade = pubFuncionalidade;
    }

    
    @Column(name="view", nullable=false)
    public boolean isView() {
        return this.view;
    }
    
    public void setView(boolean view) {
        this.view = view;
    }

    
    @Column(name="add", nullable=false)
    public boolean isAdd() {
        return this.add;
    }
    
    public void setAdd(boolean add) {
        this.add = add;
    }

    
    @Column(name="edit", nullable=false)
    public boolean isEdit() {
        return this.edit;
    }
    
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    
    @Column(name="delete", nullable=false)
    public boolean isDelete() {
        return this.delete;
    }
    
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    
    @Column(name="process", nullable=false)
    public boolean isProcess() {
        return this.process;
    }
    
    public void setProcess(boolean process) {
        this.process = process;
    }




}


