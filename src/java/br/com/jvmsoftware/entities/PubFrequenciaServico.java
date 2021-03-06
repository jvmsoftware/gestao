package br.com.jvmsoftware.entities;
// Generated 17/Abr/2016 23:30:22 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * PubFrequenciaServico generated by hbm2java
 */
public class PubFrequenciaServico  implements java.io.Serializable {


     private Integer idFrequencia;
     private String frequencia;
     private String descricaoFrequencia;
     private Set<CadServicos> cadServicoses = new HashSet<CadServicos>(0);

    public PubFrequenciaServico() {
    }

    public PubFrequenciaServico(String frequencia, String descricaoFrequencia, Set<CadServicos> cadServicoses) {
       this.frequencia = frequencia;
       this.descricaoFrequencia = descricaoFrequencia;
       this.cadServicoses = cadServicoses;
    }
   
    public Integer getIdFrequencia() {
        return this.idFrequencia;
    }
    
    public void setIdFrequencia(Integer idFrequencia) {
        this.idFrequencia = idFrequencia;
    }
    public String getFrequencia() {
        return this.frequencia;
    }
    
    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
    public String getDescricaoFrequencia() {
        return this.descricaoFrequencia;
    }
    
    public void setDescricaoFrequencia(String descricaoFrequencia) {
        this.descricaoFrequencia = descricaoFrequencia;
    }
    public Set<CadServicos> getCadServicoses() {
        return this.cadServicoses;
    }
    
    public void setCadServicoses(Set<CadServicos> cadServicoses) {
        this.cadServicoses = cadServicoses;
    }




}


