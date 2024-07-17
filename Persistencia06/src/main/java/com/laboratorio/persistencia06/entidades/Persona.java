package com.laboratorio.persistencia06.entidades;

import com.laboratorio.persistencia06.entidades.embeddable.FormaContacto;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personas")
@NoArgsConstructor @Getter @Setter
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(length = 50, nullable = false)
    private String nombre;
    
    @ElementCollection
    @CollectionTable(
            name = "formas_contacto",
            joinColumns = @JoinColumn(name = "persona_id")
    )
    private List<FormaContacto> formasContacto;

    public Persona(String nombre, List<FormaContacto> formasContacto) {
        this.nombre = nombre;
        this.formasContacto = formasContacto;
    }   
}