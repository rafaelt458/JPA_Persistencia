package com.laboratorio.persistencia06;

import com.laboratorio.persistencia06.entidades.Persona;
import com.laboratorio.persistencia06.entidades.Usuario;
import com.laboratorio.persistencia06.entidades.embeddable.FormaContacto;
import com.laboratorio.persistencia06.entidades.embeddable.PreferenciaUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class Persistencia06 {
    private static EntityManager manager;

    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {
            manager = factory.createEntityManager();

            manager.getTransaction().begin();
            
            // Usuario usuario = new Usuario("Pedro Pérez", "pedro@mail.com", "1234", "direccion", "telefono", "amarillo", "abstracto");
            Usuario usuario = new Usuario("Pedro Pérez", "pedro@mail.com", "1234", "direccion", "telefono", new PreferenciaUsuario("amarillo", "abstracto"));
            manager.persist(usuario);
            
            
            Persona persona = new Persona(
                    "Oscar Gutierrez",
                    List.of(
                            new FormaContacto("Teléfono", "12453434"),
                            new FormaContacto("WhatsApp", "12453434"),
                            new FormaContacto("SMS", "12453434")
                    )
            );
            manager.persist(persona);
                        
            manager.getTransaction().commit();
        }
    }
}