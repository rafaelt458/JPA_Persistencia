package com.laboratorio.persistencia06.entidades.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PreferenciaUsuario {
    @Column(table = "preferencias_usuarios", length = 20, nullable = false)
    private String color;
    
    @Column(table = "preferencias_usuarios", length = 30, nullable = false)
    private String tema;
}