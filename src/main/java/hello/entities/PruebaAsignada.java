package hello.entities;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Created by octavioruiz on 26/04/15.
 */

@Entity
public class PruebaAsignada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date fechaAsignacion;

    @Lob
    private String respuestas;

    @Lob
    private String evaluacion;

    @Column
    private Date fechaEvaluacion;

    @Lob
    private String notas;

    @ManyToOne
    private Candidato candidato;

    @ManyToOne
    private Prueba prueba;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_asignacion() {
        return fechaAsignacion;
    }

    public void setFecha_asignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fecha_evaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PruebaAsignada prueba_asignada = (PruebaAsignada) o;

        if ( ! Objects.equals(id, prueba_asignada.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PruebaAsignada{" +
                "id=" + id +
                ", fechaAsignacion='" + fechaAsignacion + "'" +
                ", respuestas='" + respuestas + "'" +
                ", evaluacion='" + evaluacion + "'" +
                ", fechaEvaluacion='" + fechaEvaluacion + "'" +
                ", notas='" + notas + "'" +
                '}';
    }
}