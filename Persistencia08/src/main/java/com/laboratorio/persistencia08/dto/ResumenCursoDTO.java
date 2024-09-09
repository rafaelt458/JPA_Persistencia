package com.laboratorio.persistencia08.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ResumenCursoDTO {
    private Integer idCurso;
    private String nombreCurso;
    private int nivel;
    private String nombreAlumno;

    @Override
    public String toString() {
        return "ResumenCursoDTO{" + "idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", nivel=" + nivel + ", nombreAlumno=" + nombreAlumno + '}';
    }
}