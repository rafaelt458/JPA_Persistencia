package com.laboratorio.persistencia08.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class EstadisticaDTO {
    private String nombreCurso;
    private long nroAlumnos;
    private BigDecimal promedio;
    private int notaMaxima;
    private int notaMinima;

    public EstadisticaDTO(String nombreCurso, long nroAlumnos, double promedio, int notaMaxima, int notaMinima) {
        this.nombreCurso = nombreCurso;
        this.nroAlumnos = nroAlumnos;
        this.promedio = new BigDecimal(promedio);
        this.notaMaxima = notaMaxima;
        this.notaMinima = notaMinima;
    }
    
    

    @Override
    public String toString() {
        return "EstadisticaDTO{" + "nombreCurso=" + nombreCurso + ", nroAlumnos=" + nroAlumnos + ", promedio=" + promedio + ", notaMaxima=" + notaMaxima + ", notaMinima=" + notaMinima + '}';
    }
}