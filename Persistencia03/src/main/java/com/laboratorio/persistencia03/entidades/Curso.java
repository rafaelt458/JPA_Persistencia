package com.laboratorio.persistencia03.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter @Setter @NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "nivel", nullable = false)
    private int nivel;
    
    @Column(name = "profesor", length = 50, nullable = false)
    private String profesor;
    
    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;

    public Curso(String nombre, int nivel, String profesor) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nombre=" + nombre + ", nivel=" + nivel + ", profesor=" + profesor + ", alumnos=" + alumnos + '}';
    }
}
