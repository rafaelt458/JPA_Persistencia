package com.laboratorio.persistencia05.entidades;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* @Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@NoArgsConstructor @Getter @Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
} */

/* @Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor @Getter @Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
} */

/* @Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor @Getter @Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
} */

@MappedSuperclass
@NoArgsConstructor @Getter @Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
}