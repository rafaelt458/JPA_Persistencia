package com.laboratorio.persistencia04;

import com.laboratorio.persistencia04.entidades.Alumno;
import com.laboratorio.persistencia04.entidades.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnitUtil;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Persistencia04 {
    private static EntityManager manager;

    public static void main(String[] args) {
        // parte11();
        
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {
            manager = factory.createEntityManager();
            PersistenceUnitUtil unitUtil = factory.getPersistenceUnitUtil();
            
            manager.getTransaction().begin();

            /* Curso curso = new Curso("Matemáticas", 1, "Jorge Campos");
            
            Alumno alumno1 = new Alumno("Pedro Pérez", LocalDate.of(2005, Month.APRIL, 15), "pedro@gmail.com", curso);
            Alumno alumno2 = new Alumno("Luis Vivas", LocalDate.of(2005, Month.FEBRUARY, 6), "luis@gmail.com", curso);
            Alumno alumno3 = new Alumno("Angel Puentes", LocalDate.of(2004, Month.JULY, 9), "angel@gmail.com", curso);
            
            curso.setAlumnos(List.of(alumno1, alumno2, alumno3));
            manager.persist(curso); */
            
            /* Alumno alumno = manager.find(Alumno.class, 1);
            
            System.out.println("Se recuperó el curso: " + unitUtil.isLoaded(alumno, "curso"));
            System.out.println("Curso: " + alumno.getCurso().toString()); */
            
            Curso curso = manager.find(Curso.class, 1);

            System.out.println("Se recuperaron los alumnos: " + unitUtil.isLoaded(curso, "alumnos"));
            // manager.detach(curso);
            
            for (Alumno a : curso.getAlumnos()) {
                System.out.println("Alumnos: " + a.toString());
            }
            
            manager.getTransaction().commit();
        }
    }
    
    public static void parte11() {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {
            manager = factory.createEntityManager();
            
            manager.getTransaction().begin();

            Curso curso = new Curso("Matemáticas", 1, "Jorge Campos");
            
            Alumno alumno1 = new Alumno("Pedro Pérez", LocalDate.of(2005, Month.APRIL, 15), "pedro@gmail.com", curso);
            Alumno alumno2 = new Alumno("Luis Vivas", LocalDate.of(2005, Month.FEBRUARY, 6), "luis@gmail.com", curso);
            Alumno alumno3 = new Alumno("Angel Puentes", LocalDate.of(2004, Month.JULY, 9), "angel@gmail.com", curso);
            
            // Caso 1
            // manager.persist(curso);
            // manager.persist(alumno1);
            // manager.persist(alumno2);
            // manager.persist(alumno3);
            
            // Caso 2
            curso.setAlumnos(List.of(alumno1, alumno2, alumno3));
            manager.persist(curso);
            
            manager.getTransaction().commit();
            
            
            manager.clear();
            
            manager.getTransaction().begin();
            
            alumno1.setNombre("Pedro Luis Pérez");
            alumno2.setNombre("Luis Manuel Vivas");            
            manager.merge(curso);
            
            manager.getTransaction().commit();
            
            /*
            manager.getTransaction().begin();
            
            curso = manager.find(Curso.class, 1);
            manager.remove(curso);
            
            manager.getTransaction().commit(); */
            
            
            manager.getTransaction().begin();
            
            curso = manager.find(Curso.class, 1);
            curso.getAlumnos().remove(1);
            
            manager.getTransaction().commit();
        }
    }
}