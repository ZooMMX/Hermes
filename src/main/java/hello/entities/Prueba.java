package hello.entities;

/**
 * Created by paumedina on 22/04/15.
 */

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Objects;

/**
 * A Prueba.
 */
@Entity
public class Prueba implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "notas")
    private String notas;

    @Lob
    private Blob documento;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "activa")
    private Boolean activa;

    @ManyToOne
    private User autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Blob getDocumento() {
        return documento;
    }

    public void setDocumento(Blob documento) {
        this.documento = documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User user) {
        this.autor = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Prueba prueba = (Prueba) o;

        if ( ! Objects.equals(id, prueba.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Prueba{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                ", notas='" + notas + "'" +
                ", documento='" + documento + "'" +
                ", fecha='" + fecha + "'" +
                ", activa='" + activa + "'" +
                '}';
    }
}
