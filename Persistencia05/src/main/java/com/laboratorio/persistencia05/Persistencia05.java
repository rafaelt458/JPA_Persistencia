package com.laboratorio.persistencia05;

import com.laboratorio.persistencia05.entidades.Estudiante;
import com.laboratorio.persistencia05.entidades.Persona;
import com.laboratorio.persistencia05.entidades.Profesor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;

public class Persistencia05 {
    private static EntityManager manager;

    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {
            manager = factory.createEntityManager();

            manager.getTransaction().begin();
            
            Estudiante estudiante = new Estudiante("Pedro Pérez", "2do. B", LocalDate.of(2010, Month.MARCH, 13));
            manager.persist(estudiante);
            
            Profesor profesor = new Profesor("Román Alonso", "Matemáticas", "982.543.123");
            manager.persist(profesor);
            
            manager.getTransaction().commit();
            
            manager.clear();
            
            manager.getTransaction().begin();
            
            // Persona persona = manager.find(Persona.class, 1L);
            Estudiante estudiante1 = manager.find(Estudiante.class, 1L);
            
            manager.getTransaction().commit();
        }
    }
}