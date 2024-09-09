package com.laboratorio.persistencia08.dao.impl;

import com.laboratorio.persistencia08.dao.AlumnoDAO;
import com.laboratorio.persistencia08.entidades.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class AlumnoDAOImpl implements AlumnoDAO {
    private final EntityManager manager;

    public AlumnoDAOImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Alumno> findAll() {
        TypedQuery<Alumno> query = manager.createQuery("SELECT a FROM Alumno a", Alumno.class);
        return query.getResultList();
    }

    @Override
    public Optional<Alumno> findById(Integer id) {
        return Optional.of(manager.find(Alumno.class, id));
    }

    @Override
    public void create(Alumno alumno) {
        manager.persist(alumno);
    }

    @Override
    public void update(Alumno alumno) {
        manager.merge(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        manager.remove(alumno);
    }   
}