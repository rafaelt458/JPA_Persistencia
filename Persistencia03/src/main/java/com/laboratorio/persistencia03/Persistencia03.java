package com.laboratorio.persistencia03;

import com.laboratorio.persistencia03.entidades.Alumno;
import com.laboratorio.persistencia03.entidades.Curso;
import com.laboratorio.persistencia03.entidades.Rol;
import com.laboratorio.persistencia03.entidades.Salon;
import com.laboratorio.persistencia03.entidades.Socio;
import com.laboratorio.persistencia03.entidades.TarjetaSocio;
import com.laboratorio.persistencia03.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

public class Persistencia03 {
    public static void main(String[] args) {
        // parte1();
        // parte2();
        
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {            
            EntityManager manager = factory.createEntityManager();
            
            manager.getTransaction().begin();
            
            Rol rol1 = new Rol("Administrador");
            Rol rol2 = new Rol("Usuario");
            Rol rol3 = new Rol("Invitado");
            
            manager.persist(rol1);
            manager.persist(rol2);
            manager.persist(rol3);
            
            /* Usuario usuario1 = new Usuario("laboratorio", "1234", "Laboratorio de Rafa", "lab@mail.com", List.of(rol1, rol2, rol1));
            Usuario usuario2 = new Usuario("pperez", "1234", "Pedro Pérez", "pedro@mail.com", List.of(rol2));
            Usuario usuario3 = new Usuario("lgarcia", "1234", "Luis García", "luis@mail.com", List.of(rol3)); */
            
            Usuario usuario1 = new Usuario("laboratorio", "1234", "Laboratorio de Rafa", "lab@mail.com", Set.of(rol1, rol2));
            Usuario usuario2 = new Usuario("pperez", "1234", "Pedro Pérez", "pedro@mail.com", Set.of(rol2));
            Usuario usuario3 = new Usuario("lgarcia", "1234", "Luis García", "luis@mail.com", Set.of(rol3));
            
            manager.persist(usuario1);
            manager.persist(usuario2);
            manager.persist(usuario3);
            
            manager.getTransaction().commit();
            
            manager.clear();
            
            manager.getTransaction().begin();
            
            rol1 = manager.find(Rol.class, 1);
            System.out.println("*** Usuarios del rol: " + rol1.toString());
            for (Usuario u : rol1.getUsuarios()) {
                System.out.println("*** Usuario: " + u.toString());
            }
            
            rol2 = manager.find(Rol.class, 2);
            System.out.println("*** Usuarios del rol: " + rol2.toString());
            for (Usuario u : rol2.getUsuarios()) {
                System.out.println("*** Usuario: " + u.toString());
            }
            
            rol3 = manager.find(Rol.class, 3);
            System.out.println("*** Usuarios del rol: " + rol3.toString());
            for (Usuario u : rol3.getUsuarios()) {
                System.out.println("*** Usuario: " + u.toString());
            }
            
            manager.getTransaction().commit();
        }
    }
    
    public static void parte1() {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {            
            EntityManager manager = factory.createEntityManager();
            
            manager.getTransaction().begin();
            
            // Parte 1 - Unidireccional
            /* TarjetaSocio tarjeta1 = new TarjetaSocio("ABCD-111-222-333");
            Socio socio1 = new Socio("Pedro Pérez", "12345678", LocalDate.of(2000, Month.APRIL, 15), tarjeta1);
            
            manager.persist(tarjeta1);
            manager.persist(socio1);
            
            Socio socio2 = new Socio("Luis García", "12345679", LocalDate.of(2001, Month.JUNE, 21), tarjeta1);
            manager.persist(socio2); */
            
            
           // Parte 2 - Unidireccional
           /* Socio socio1 = new Socio("Pedro Pérez", "12345678", LocalDate.of(2000, Month.APRIL, 15));
           TarjetaSocio tarjeta1 = new TarjetaSocio("ABCD-111-222-333", socio1);
            
           manager.persist(socio1);
           manager.persist(tarjeta1);
           
           Socio socio2 = new Socio("Luis García", "12345679", LocalDate.of(2001, Month.JUNE, 21));
           manager.persist(socio2); */
            
            // Parte 3 - Bidireccional
/*           Socio socio1 = new Socio("Pedro Pérez", "12345678", LocalDate.of(2000, Month.APRIL, 15));
           TarjetaSocio tarjeta1 = new TarjetaSocio("ABCD-111-222-333", socio1);
           //socio1.setTarjetaSocio(tarjeta1);
            
           manager.persist(socio1);
           manager.persist(tarjeta1);
            
            
            manager.getTransaction().commit();
            
            System.out.println("Socio 1: " + socio1.toString());
            
            manager.getTransaction().begin();
            
            manager.clear();
            socio1 = null;
            Socio socio2 = manager.find(Socio.class, 1);
            System.out.println("Socio 2: " + socio2.toString()); */
            
            
            // Parte 4 - Mismo id para ambas entidades
            Socio socio1 = new Socio("Pedro Pérez", "12345678", LocalDate.of(2000, Month.APRIL, 15));
            TarjetaSocio tarjeta1 = new TarjetaSocio("ABCD-111-222-333", socio1);
            
            manager.persist(socio1);
            manager.persist(tarjeta1);
            
            manager.getTransaction().commit();
        }
    }
    
    public static void parte2() {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {            
            EntityManager manager = factory.createEntityManager();
            
            manager.getTransaction().begin();
            
            Curso curso = new Curso("Matemáticas", 1, "Jorge Campos");
            manager.persist(curso);
            
            Alumno alumno1 = new Alumno("Pedro Pérez", LocalDate.of(2005, Month.APRIL, 15), "pedro@gmail.com", curso);
            manager.persist(alumno1);
            
            Alumno alumno2 = new Alumno("Luis Vivas", LocalDate.of(2005, Month.FEBRUARY, 6), "luis@gmail.com", curso);
            manager.persist(alumno2);
            
            manager.getTransaction().commit();
            
            
            manager.getTransaction().begin();
            
            manager.clear();
            curso = manager.find(Curso.class, 1);
            System.out.println("Curso: " + curso.toString());
            
            manager.getTransaction().commit();
            
            manager.getTransaction().begin();
            
            alumno1 = manager.find(Alumno.class, 1);
            alumno2 = manager.find(Alumno.class, 2);
            
            Salon salon = new Salon("Sala-01", List.of(alumno1, alumno2));
            manager.persist(salon);
            
            manager.getTransaction().commit();
        }
    }
}
