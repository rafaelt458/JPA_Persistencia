package com.laboratorio.persistencia03.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumnos")
@Getter @Setter @NoArgsConstructor
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNac;
    
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Alumno(String nombre, LocalDate fechaNac, String email, Curso curso) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.email = email;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", email=" + email + '}';
    }
}