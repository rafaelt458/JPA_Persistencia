package com.laboratorio.persistencia08.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ResumenAlumnoDTO {
    private Integer idAlumno;
    private String nombreAlumno;
    private String nombreCurso;

    @Override
    public String toString() {
        return "ResumenAlumnoDTO{" + "idAlumno=" + idAlumno + ", nombreAlumno=" + nombreAlumno + ", nombreCurso=" + nombreCurso + '}';
    }
}