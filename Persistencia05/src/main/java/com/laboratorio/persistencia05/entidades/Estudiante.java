package com.laboratorio.persistencia05.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* @Entity
@DiscriminatorValue("E")
@NoArgsConstructor @Getter @Setter
public class Estudiante extends Persona {
     private String matricula;
     private LocalDate fechaNac;

    public Estudiante(String nombre, String matricula, LocalDate fechaNac) {
        super(nombre);
        this.matricula = matricula;
        this.fechaNac = fechaNac;
    }
} */

/* @Entity
@PrimaryKeyJoinColumn(name = "estudiante_id")
@NoArgsConstructor @Getter @Setter
public class Estudiante extends Persona {
     private String matricula;
     private LocalDate fechaNac;

    public Estudiante(String nombre, String matricula, LocalDate fechaNac) {
        super(nombre);
        this.matricula = matricula;
        this.fechaNac = fechaNac;
    }
} */

/* @Entity
@NoArgsConstructor @Getter @Setter
public class Estudiante extends Persona {
     private String matricula;
     private LocalDate fechaNac;

    public Estudiante(String nombre, String matricula, LocalDate fechaNac) {
        super(nombre);
        this.matricula = matricula;
        this.fechaNac = fechaNac;
    }
} */

@Entity
@Table(name = "estudiantes")
@NoArgsConstructor @Getter @Setter
public class Estudiante extends Persona {
     private String matricula;
     private LocalDate fechaNac;

    public Estudiante(String nombre, String matricula, LocalDate fechaNac) {
        super(nombre);
        this.matricula = matricula;
        this.fechaNac = fechaNac;
    }
}