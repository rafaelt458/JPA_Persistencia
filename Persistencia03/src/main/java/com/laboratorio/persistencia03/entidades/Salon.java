package com.laboratorio.persistencia03.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salones")
@Getter @Setter @NoArgsConstructor
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;
    
    @OneToMany
    @JoinColumn(name = "salon_id")
    List<Alumno> alumnos;

    public Salon(String nombre, List<Alumno> alumnos) {
        this.nombre = nombre;
        this.alumnos = alumnos;
    }
}
