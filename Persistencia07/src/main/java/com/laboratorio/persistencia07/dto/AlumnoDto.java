package com.laboratorio.persistencia07.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AlumnoDto {
    private Integer id;
    private String nombre;
    private int nota;

    @Override
    public String toString() {
        return "AlumnoDto{" + "id=" + id + ", nombre=" + nombre + ", nota=" + nota + '}';
    }
}