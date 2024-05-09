package com.laboratorio.persistencia01;

import com.laboratorio.persistencia01.config.PersistenceUnitInfoImpl;
import com.laboratorio.persistencia01.modelo.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.jpa.HibernatePersistenceProvider;

public class Persistencia01 {

    public static void main(String[] args) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        
        // try (EntityManagerFactory factory
        //      = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new PersistenceUnitInfoImpl(), properties)) {
        
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {
            
            Map<String, Object> propiedadesPool = factory.getProperties();
            for (Map.Entry<String, Object> entry : propiedadesPool.entrySet()) {
                System.out.println(String.format("%s = %s", entry.getKey(), entry.getValue()));
            }
            
            EntityManager manager = factory.createEntityManager();
            
            manager.getTransaction().begin();
            
            // Persist
            //Empleado empleado1 = new Empleado(1, "Juan Cardillo", LocalDate.of(1998, Month.JUNE, 17), 1458.3);
            //manager.persist(empleado1);    
            // System.out.println("Empleado 1: " + empleado1.toString());
            
            // Find
            //Empleado empleado2 = manager.find(Empleado.class, 1);
            // Empleado empleado3 = manager.find(Empleado.class, 1);
            //System.out.println("Empleado 2: " + empleado2.toString());
            // System.out.println("Empleado 3: " + empleado3.toString());
            //if (empleado2 == empleado3) {
             //   System.out.println("Los objetos son iguales");
            //}
            
            // Refresh
            //empleado2.setNombres("Luis García");
            // System.out.println("Empleado 2: " + empleado2.toString());
            //manager.refresh(empleado2);
            //System.out.println("Empleado 2: " + empleado2.toString());
            
            // getReference
            //Empleado empleado4 = manager.getReference(Empleado.class, 1);
            // System.out.println("Empleado 4: " + empleado4.toString());
            
            // Modificación
            // empleado4.setNombres("Luis García");
            
            // Remove
            //manager.remove(empleado4);
            //System.out.println("Empleado 4: " + empleado4.toString());
            
            // Detach
            //Empleado empleado5 = manager.find(Empleado.class, 1);
            //manager.detach(empleado5);
            //manager.remove(empleado5);
            
            // Merge
            //Empleado empleado6 = new Empleado(1, "Alexis Peña", LocalDate.of(1995, Month.JANUARY, 7), 1198.9);
            //manager.merge(empleado6);
            
            //Empleado empleado7 = new Empleado(2, "Oscar Álvarez", LocalDate.of(1992, Month.FEBRUARY, 22), 1798.9);
            //manager.merge(empleado7);
            
            // Flush
            //Empleado empleado8 = new Empleado(3, "Alberto González", LocalDate.of(1990, Month.MAY, 25), 1233.7);
            //manager.persist(empleado8);
            //manager.flush();
            
            //System.out.println("Empleado 8: " + empleado8.toString());
            
            // Clear y Contains
            Empleado empleado9 = manager.find(Empleado.class, 1);
            if (manager.contains(empleado9)) {
                System.out.println("El empleado 9 está en el contexto de persistencia");
            } else {
                System.out.println("El empleado 9 NO está en el contexto de persistencia");
            }
            
            manager.clear();
            
            if (manager.contains(empleado9)) {
                System.out.println("El empleado 9 está en el contexto de persistencia");
            } else {
                System.out.println("El empleado 9 NO está en el contexto de persistencia");
            }
            
            manager.getTransaction().commit();
            
            System.out.println("Fin de la transacción");
        }
    }
}