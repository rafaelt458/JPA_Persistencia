package com.laboratorio.persistencia07;

import com.laboratorio.persistencia07.dto.AlumnoDto;
import com.laboratorio.persistencia07.entidades.Alumno;
import com.laboratorio.persistencia07.entidades.Curso;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Persistencia07 {
    private static EntityManager manager;
    
    public static void main(String[] args) {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();
            
            /* Curso curso1 = new Curso("Matemáticas", 1, "Jorge Campos");
            Curso curso2 = new Curso("Castellano", 2, "Ángela Pérez");
            Curso curso3 = new Curso("Inglés", 1, "Oscar Landaeta");
            Curso curso4 = new Curso("Castellano", 1, "Ángela Pérez");
            
            Alumno alumno1 = new Alumno("Pedro Pérez", LocalDate.of(2005, Month.APRIL, 15), "pedro@mail.com", 8, curso1);
            Alumno alumno2 = new Alumno("Luis Vivas", LocalDate.of(2005, Month.FEBRUARY, 6), "luis@mail.com", 7, curso1);
            Alumno alumno3 = new Alumno("Angel Puentes", LocalDate.of(2004, Month.JULY, 9), "angel@mail.com", 4, curso1);
            Alumno alumno4 = new Alumno("Jorge Cadenas", LocalDate.of(2004, Month.AUGUST, 19), "jorge@mail.com", 6, curso2);
            Alumno alumno5 = new Alumno("María Valero", LocalDate.of(2005, Month.SEPTEMBER, 5), "maria@mail.com", 3, curso2);
            Alumno alumno6 = new Alumno("Natalia Moreno", LocalDate.of(2005, Month.FEBRUARY, 9), "natalia@mail.com", 9, curso2);
            Alumno alumno7 = new Alumno("Guillermo Parada", LocalDate.of(2004, Month.MAY, 23), "guillermo@mail.com", 7, curso3);
            Alumno alumno8 = new Alumno("Olga García", LocalDate.of(2004, Month.JANUARY, 29), "olga@mail.com", 3, curso3);
            Alumno alumno9 = new Alumno("Teresa Camargo", LocalDate.of(2005, Month.FEBRUARY, 12), "teresa@mail.com", 8, curso3);
                        
            curso1.setAlumnos(List.of(alumno1, alumno2, alumno3));
            manager.persist(curso1);
            
            curso2.setAlumnos(List.of(alumno4, alumno5, alumno6));
            manager.persist(curso2);
            
            curso3.setAlumnos(List.of(alumno7, alumno8, alumno9));
            manager.persist(curso3);
            
            manager.persist(curso4); */
            
            /* String str = "SELECT new com.laboratorio.persistencia07.dto.AlumnoDto(a.id, a.nombre, a.nota) FROM Alumno a";
            Query query = manager.createQuery(str);
            List<AlumnoDto> alumnos = query.getResultList();
            
            for (AlumnoDto a : alumnos) {
                System.out.println("Alumno: " + a.toString());
            } */
            
            /* Curso curso5 = new Curso("Castellano", 3, "Ángela Pérez");
            Curso curso6 = new Curso("Matemáticas", 2, "Jorge Campos");
            Curso curso7 = new Curso("Matemáticas", 3, "Jorge Campos");
            
            Alumno alumno10 = new Alumno("Roberto Sainz", LocalDate.of(2005, Month.APRIL, 14), "roberto@mail.com", 6, curso5);
            Alumno alumno11 = new Alumno("Alexis Peña", LocalDate.of(2005, Month.APRIL, 25), "alexis@mail.com", 4, curso6);
            Alumno alumno12 = new Alumno("Isabel Ortega", LocalDate.of(2004, Month.MAY, 12), "isabel@mail.com", 8, curso7);
            
            curso5.setAlumnos(List.of(alumno10));
            manager.persist(curso5);
            curso6.setAlumnos(List.of(alumno11));
            manager.persist(curso6);
            curso7.setAlumnos(List.of(alumno12));
            manager.persist(curso7); */
            
            Alumno alumno = manager.find(Alumno.class, 1);
            String str = "SELECT c FROM Curso c WHERE :alum NOT MEMBER OF c.alumnos";
            Query query = manager.createQuery(str);
            query.setParameter("alum", alumno);
            List<Curso> cursos = query.getResultList();
            
            for (Curso c : cursos) {
                System.out.println("Curso: " + c.toString());
                for (Alumno a : c.getAlumnos()) {
                    System.out.println("Alumno: " + a.toString());
                }
            }
            
            
            manager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}