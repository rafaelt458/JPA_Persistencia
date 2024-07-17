package com.laboratorio.persistencia06.entidades.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FormaContacto {
    @Column(length = 30, nullable = false)
    private String metodo;
    
    @Column(nullable = false)
    private String valor;
}