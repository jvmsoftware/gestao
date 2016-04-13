package br.com.jvmsoftware.entities;
// Generated 12/Abr/2016 22:07:07 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PubTipoImovel generated by hbm2java
 */
@Entity
@Table(name="pub_tipo_imovel"
    ,catalog="jvmdsoftware"
)
public class PubTipoImovel  implements java.io.Serializable {


     private Integer idTipoImovel;
     private String tipoImovel;
     private Set cadImoveises = new HashSet(0);

    public PubTipoImovel() {
    }

	
    public PubTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }
    public PubTipoImovel(String tipoImovel, Set cadImoveises) {
       this.tipoImovel = tipoImovel;
       this.cadImoveises = cadImoveises;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_tipo_imovel", unique=true, nullable=false)
    public Integer getIdTipoImovel() {
        return this.idTipoImovel;
    }
    
    public void setIdTipoImovel(Integer idTipoImovel) {
        this.idTipoImovel = idTipoImovel;
    }

    
    @Column(name="tipo_imovel", nullable=false, length=50)
    public String getTipoImovel() {
        return this.tipoImovel;
    }
    
    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pubTipoImovel")
    public Set getCadImoveises() {
        return this.cadImoveises;
    }
    
    public void setCadImoveises(Set cadImoveises) {
        this.cadImoveises = cadImoveises;
    }




}


