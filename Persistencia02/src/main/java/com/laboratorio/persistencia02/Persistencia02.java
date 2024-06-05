/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.laboratorio.persistencia02;

import com.laboratorio.persistencia02.modelo.Direccion;
import com.laboratorio.persistencia02.modelo.Empleado;
import com.laboratorio.persistencia02.modelo.LineaFactura1;
import com.laboratorio.persistencia02.modelo.LineaFactura2;
import com.laboratorio.persistencia02.modelo.pkey.LineaFacturaPK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Rafael
 */
public class Persistencia02 {
    
    private static void estadoEntidad(EntityManager manager, Empleado empleado) {
        if (manager.contains(empleado)) {
            System.out.println("El empleado está en el contexto de persistencia");
        } else {
            System.out.println("El empleado NO está en el contexto de persistencia");
        }
    }

    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {            
            EntityManager manager = factory.createEntityManager();
            
            manager.getTransaction().begin();
            
            // Estado New
            /* Empleado empleado = new Empleado("Pedro Pérez", "12345678", "12345678", LocalDate.of(1990, Month.MARCH, 10), 0, (short)1, new BigDecimal(1250.45));
            estadoEntidad(manager, empleado); */ 
            
            // Estado Managed
            // manager.persist(empleado);
            // estadoEntidad(manager, empleado);
            
            // Estado Managed
            Empleado empleado = manager.find(Empleado.class, 1);
            estadoEntidad(manager, empleado);
            
            // Estado Detached
            // manager.detach(empleado);
            // estadoEntidad(manager, empleado);
            
            // manager.getTransaction().commit();
            
            // Estado Detached
            //manager.close();
            // manager = factory.createEntityManager();
            // estadoEntidad(manager, empleado);
            
            // manager.getTransaction().begin();
            
            empleado.setNombres("Angel Peña");
            manager.merge(empleado);
            estadoEntidad(manager, empleado);
            
            // Estado Removed
            manager.remove(empleado);
            estadoEntidad(manager, empleado);
            
            manager.getTransaction().commit();
            
            
            
            /* manager.getTransaction().begin();
            
            // Empleado empleado = new Empleado(1, "Pedro Pérez", "12345678", "12345678", LocalDate.of(1990, Month.MARCH, 10), 0, 1, new BigDecimal(1250.45));
            Empleado empleado1 = new Empleado("Pedro Pérez", "12345678", "12345678", LocalDate.of(1990, Month.MARCH, 10), 0, 1, new BigDecimal(1250.45));
            manager.persist(empleado1);
            
            Empleado empleado2 = new Empleado("Juan Castillo", "12345679", "12345679", LocalDate.of(1990, Month.MARCH, 10), 0, 1, new BigDecimal(1250.45));
            manager.persist(empleado2);
            
            Direccion direccion = new Direccion("Calle 10 #12", "Madrid", "España");
            manager.persist(direccion);
            
            LineaFacturaPK lfPK = new LineaFacturaPK("F00452534", 45);
            LineaFactura1 lf1 = new LineaFactura1(lfPK, 5, new BigDecimal(13.45), 21, 0);
            manager.persist(lf1);
            
            LineaFactura2 lf2 = new LineaFactura2("F00452534", 45, 4, new BigDecimal(16.14), 21, 0);
            manager.persist(lf2);
                        
            manager.getTransaction().commit(); */
            
            
/*            manager.getTransaction().begin();
            
            manager.detach(empleado);
            empleado = manager.find(Empleado.class, 1);
            // System.out.println("Edad: " + empleado.calcularEdad());
            System.out.println("Edad: " + empleado.getEdad());
            
            manager.getTransaction().commit(); */
        }
    }
}
