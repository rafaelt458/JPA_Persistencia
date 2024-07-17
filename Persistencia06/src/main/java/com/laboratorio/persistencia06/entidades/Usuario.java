package com.laboratorio.persistencia06.entidades;

import com.laboratorio.persistencia06.entidades.embeddable.PreferenciaUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@SecondaryTable(
        name = "contacto_usuarios",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "usuario_id")
)
@SecondaryTable(
        name = "preferencias_usuarios",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "usuario_id")
)
@NoArgsConstructor @Getter @Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(length = 50, nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String email;
    
    @Column(length = 50, nullable = false)
    private String password;
    
    @Column(table = "contacto_usuarios", nullable = false)
    private String direccion;
    
    @Column(table = "contacto_usuarios", length = 20, nullable = false)
    private String telefono;
    
    /* @Column(table = "preferencias_usuarios", length = 20, nullable = false)
    private String color;
    
    @Column(table = "preferencias_usuarios", length = 30, nullable = false)
    private String tema; */
    
    private PreferenciaUsuario preferenciaUsuario;

    /* public Usuario(String nombre, String email, String password, String direccion, String telefono, String color, String tema) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.color = color;
        this.tema = tema;
    } */

    public Usuario(String nombre, String email, String password, String direccion, String telefono, PreferenciaUsuario preferenciaUsuario) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.preferenciaUsuario = preferenciaUsuario;
    }
}