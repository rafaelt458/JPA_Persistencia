package com.laboratorio.persistencia08.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumnos")
@NamedQuery(
        name = "Alumno.findAll",
        query = "SELECT a FROM Alumno a ORDER BY a.nombre"
)
@NamedStoredProcedureQuery(
        name = "Alumno.promocionarAumnos",
        procedureName = "promover_alumnos",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "idOrigen", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "idDestino", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "nro_actualizaciones", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "nro_eliminaciones", type = Integer.class)
        }
)
@Getter @Setter @NoArgsConstructor
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer id;
    
    @Column(name = "nombre_alumno", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNac;
    
    @Column(name = "email_alumno", length = 255, nullable = false, unique = true)
    private String email;
    
    @Column(name = "nota_alumno", nullable = false)
    private int nota;
    
    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Alumno(String nombre, LocalDate fechaNac, String email, int nota, Curso curso) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.email = email;
        this.nota = nota;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", email=" + email + ", nota=" + nota + '}';
    }
}