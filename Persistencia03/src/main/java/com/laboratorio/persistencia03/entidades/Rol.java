package com.laboratorio.persistencia03.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nombre", length = 30, nullable = false, unique = true)
    private String nombre;
    
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}