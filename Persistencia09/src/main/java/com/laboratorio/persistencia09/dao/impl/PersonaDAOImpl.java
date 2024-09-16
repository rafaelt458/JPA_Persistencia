package com.laboratorio.persistencia09.dao.impl;

import com.laboratorio.persistencia09.dao.PersonaDAO;
import com.laboratorio.persistencia09.modelo.Persona;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class PersonaDAOImpl implements PersonaDAO {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Persona> getPersonas() {
        try {
            String str = "SELECT p FROM Persona p ORDER BY p.apellidos, p.nombres";
            TypedQuery<Persona> query = manager.createQuery(str, Persona.class);
            return query.getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Persona buscar(int codigo) {
        try {
            return manager.find(Persona.class, codigo);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean insertar(String nombres, String apellidos, String fechaNac, String experiencia) {
        try {
            Persona persona = new Persona(nombres, apellidos, fechaNac, experiencia);
            manager.persist(persona);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean editar(int codigo, String nombres, String apellidos, String fechaNac, String experiencia) {
        try {
            Persona persona = new Persona(codigo, nombres, apellidos, fechaNac, experiencia);
            manager.merge(persona);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean eliminar(int codigo) {
        try {
            Persona persona = manager.find(Persona.class, codigo);
            if (persona == null) {
                return false;
            }
            manager.remove(persona);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }   
}