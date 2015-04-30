package hello.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * Created by paumedina on 23/04/15.
 */

@Entity
public class Puesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String descripcion;

    @Column
    private String tipo_contrato;

    @Column
    private String responsabilidades;

    @Column(precision=10, scale=2)
    private BigDecimal sueldo;

    @Column
    private String nivel_academico;

    @Column
    private String experiencia_laboral;

    @Column
    private String conocimientos;

    @Column
    private String aptitudes;

    @ManyToMany
    @JoinTable(name="prueba_puesto", joinColumns = @JoinColumn(name = "puesto_id"), inverseJoinColumns = @JoinColumn(name = "prueba_id"))
    private Set<Prueba> pruebas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public String getResponsabilidades() {
        return responsabilidades;
    }

    public void setResponsabilidades(String responsabilidades) {
        this.responsabilidades = responsabilidades;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public String getNivel_academico() {
        return nivel_academico;
    }

    public void setNivel_academico(String nivel_academico) {
        this.nivel_academico = nivel_academico;
    }

    public String getExperiencia_laboral() {
        return experiencia_laboral;
    }

    public void setExperiencia_laboral(String experiencia_laboral) {
        this.experiencia_laboral = experiencia_laboral;
    }

    public String getConocimientos() {
        return conocimientos;
    }

    public void setConocimientos(String conocimientos) {
        this.conocimientos = conocimientos;
    }

    public String getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(String aptitudes) {
        this.aptitudes = aptitudes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Puesto puesto = (Puesto) o;

        if ( ! Objects.equals(id, puesto.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Puesto{" +
                "id=" + id +
                ", descripcion='" + descripcion + "'" +
                ", tipo_contrato='" + tipo_contrato + "'" +
                ", responsabilidades='" + responsabilidades + "'" +
                ", sueldo='" + sueldo + "'" +
                ", nivel_academico='" + nivel_academico + "'" +
                ", experiencia_laboral='" + experiencia_laboral + "'" +
                ", conocimientos='" + conocimientos + "'" +
                ", aptitudes='" + aptitudes + "'" +
                '}';
    }

    public Set<Prueba> getPruebas() {
        return pruebas;
    }

    public void setPruebas(Set<Prueba> pruebas) {
        this.pruebas = pruebas;
    }
}