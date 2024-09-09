package com.laboratorio.persistencia08.dao;

import com.laboratorio.persistencia08.entidades.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoDAO {
    List<Curso> findAll();
    Optional<Curso> findById(Integer id);
    void create(Curso curso);
    void update(Curso curso);
    void delete(Curso curso);
}