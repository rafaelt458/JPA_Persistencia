package com.laboratorio.persistencia09.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personas")
@NoArgsConstructor @Getter @Setter
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    
    @Column(name = "nombres", length = 25, nullable = false)
    private String nombres;
    
    @Column(name = "apellidos", length = 25, nullable = false)
    private String apellidos;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNac;
    
    @Column(name = "experiencia", nullable = false)
    private int experiencia;

    public Persona(String nombres, String apellidos, String fechaNac, String experiencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fechaNac = LocalDate.parse(fechaNac, dtf);
        this.experiencia = Integer.parseInt(experiencia);
    }
    
    public Persona(int codigo, String nombres, String apellidos, String fechaNac, String experiencia) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fechaNac = LocalDate.parse(fechaNac, dtf);
        this.experiencia = Integer.parseInt(experiencia);
    }
}