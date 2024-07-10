package com.laboratorio.persistencia05.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* @Entity
@DiscriminatorValue("P")
@NoArgsConstructor @Getter @Setter
public class Profesor extends Persona {
    private String departamento;
    private String telefono;

    public Profesor(String nombre, String departamento, String telefono) {
        super(nombre);
        this.departamento = departamento;
        this.telefono = telefono;
    }
} */

/* @Entity
@PrimaryKeyJoinColumn(name = "profesor_id")
@NoArgsConstructor @Getter @Setter
public class Profesor extends Persona {
    private String departamento;
    private String telefono;

    public Profesor(String nombre, String departamento, String telefono) {
        super(nombre);
        this.departamento = departamento;
        this.telefono = telefono;
    }
} */

/* @Entity
@NoArgsConstructor @Getter @Setter
public class Profesor extends Persona {
    private String departamento;
    private String telefono;

    public Profesor(String nombre, String departamento, String telefono) {
        super(nombre);
        this.departamento = departamento;
        this.telefono = telefono;
    }
} */

@Entity
@Table(name = "profesores")
@NoArgsConstructor @Getter @Setter
public class Profesor extends Persona {
    private String departamento;
    private String telefono;

    public Profesor(String nombre, String departamento, String telefono) {
        super(nombre);
        this.departamento = departamento;
        this.telefono = telefono;
    }
}