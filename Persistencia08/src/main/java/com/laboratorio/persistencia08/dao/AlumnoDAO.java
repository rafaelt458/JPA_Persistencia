package com.laboratorio.persistencia08.dao;

import com.laboratorio.persistencia08.entidades.Alumno;
import java.util.List;
import java.util.Optional;

public interface AlumnoDAO {
    List<Alumno> findAll();
    Optional<Alumno> findById(Integer id);
    void create(Alumno alumno);
    void update(Alumno alumno);
    void delete(Alumno alumno);
}
