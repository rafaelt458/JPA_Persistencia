package com.laboratorio.persistencia08.dao.impl;

import com.laboratorio.persistencia08.dao.CursoDAO;
import com.laboratorio.persistencia08.entidades.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CursoDAOImpl implements CursoDAO {
    private final EntityManager manager;

    public CursoDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Curso> findAll() {
        TypedQuery<Curso> query = manager.createQuery("SELECT c FROM Curso c", Curso.class);
        return query.getResultList();
    }

    @Override
    public Optional<Curso> findById(Integer id) {
        return Optional.of(manager.find(Curso.class, id));
    }

    @Override
    public void create(Curso curso) {
        manager.persist(curso);
    }

    @Override
    public void update(Curso curso) {
        manager.merge(curso);
    }

    @Override
    public void delete(Curso curso) {
        manager.remove(curso);
    }
}