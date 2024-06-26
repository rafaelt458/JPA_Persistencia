package com.laboratorio.persistencia03.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nombre_usuario", length = 30, nullable = false, unique = true)
    private String nombreUsuario;
    
    @Column(name = "clave", length = 30, nullable = false)
    private String clave;
    
    @Column(name = "nombre_persona", length = 50, nullable = false)
    private String nombrePersona;
    
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;
    
    @ManyToMany
    @JoinTable(
            name = "roles_usuarios",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    // private List<Rol> roles;
    private Set<Rol> roles;

    /* public Usuario(String nombreUsuario, String clave, String nombrePersona, String email, List<Rol> roles) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.nombrePersona = nombrePersona;
        this.email = email;
        this.roles = roles;
    } */
    
    public Usuario(String nombreUsuario, String clave, String nombrePersona, String email, Set<Rol> roles) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.nombrePersona = nombrePersona;
        this.email = email;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario=" + nombreUsuario + ", clave=" + clave + ", nombrePersona=" + nombrePersona + ", email=" + email + '}';
    }
}