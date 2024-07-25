package com.laboratorio.persistencia07.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumnos")
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