package hello.entities;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by paumedina on 26/04/15.
 */


@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Candidato implements Serializable {

    @Id
    @Column(name="username")
    private String username;

    @Lob
    private String experiencia;

    @Column
    private String formacion;

    @Column
    private Integer edad;

    @Column
    private String genero;

    @Column
    private String direccion;

    @Lob
    private String notas;

    @Column
    private Boolean activo;

    @MapsId
    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    private Puesto puesto;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Candidato candidato = (Candidato) o;

        if ( ! Objects.equals(username, candidato.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "username=" + username +
                ", experiencia='" + experiencia + "'" +
                ", formacion='" + formacion + "'" +
                ", edad='" + edad + "'" +
                ", genero='" + genero + "'" +
                ", direccion='" + direccion + "'" +
                ", notas='" + notas + "'" +
                ", activo='" + activo + "'" +
                '}';
    }
}