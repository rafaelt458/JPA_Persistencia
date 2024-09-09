package com.laboratorio.persistencia08.persistencia;

import com.laboratorio.persistencia08.dto.EstadisticaDTO;
import com.laboratorio.persistencia08.dto.ResumenCursoDTO;
import com.laboratorio.persistencia08.entidades.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CursoDB {
    private final EntityManager manager;

    public CursoDB(EntityManager manager) {
        this.manager = manager;
    }
    
    public Optional<Curso> findById(int id) {
        // String sql = "SELECT * FROM cursos WHERE id = ?";
        // String sql = "SELECT * FROM cursos WHERE id = :id";
        // Query query = manager.createNativeQuery(sql, Curso.class);
        TypedQuery<Curso> query = manager.createNamedQuery("Curso.findById", Curso.class);
        // query.setParameter(1, id);
        query.setParameter("id", id);
        
        try {
            // return Optional.of((Curso)query.getSingleResult());
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            System.out.println("No hay alumno con ese identificador");
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getMessage());
            }
            return Optional.empty();
        }
    }
    
    public /* void */ EstadisticaDTO getEstadisticaCurso(int id) {
        /* String str = "SELECT c.nombre, COUNT(a), AVG(a.nota), MAX(a.nota), MIN(a.nota)"
                + " FROM Curso c, Alumno a"
                + " WHERE c = a.curso AND c.id = :id"
                + " GROUP BY c.nombre";
        Query query = manager.createQuery(str, Object[].class);
        query.setParameter("id", id);
        List<Object[]> estadisticas = query.getResultList(); */
        
        String str = "SELECT new com.laboratorio.persistencia08.dto.EstadisticaDTO("
                + "c.nombre, COUNT(a), AVG(a.nota), MAX(a.nota), MIN(a.nota))"
                + " FROM Curso c INNER JOIN c.alumnos a"
                + " WHERE c.id = :id"
                + " GROUP BY c.nombre";
        /* String str = "SELECT new com.laboratorio.persistencia08.dto.EstadisticaDTO("
                + "c.nombre, COUNT(a), AVG(a.nota), MAX(a.nota), MIN(a.nota))"
                + " FROM Curso c, Alumno a"
                + " WHERE c = a.curso AND c.id = :id"
                + " GROUP BY c.nombre"; */
        TypedQuery<EstadisticaDTO> query = manager.createQuery(str, EstadisticaDTO.class);
        query.setParameter("id", id);
        return query.getSingleResult();
        
        /* for (Object[] estadistica : estadisticas) {
            System.out.println("Curso: " + estadistica[0]);
            System.out.println("Número de alumnos: " + estadistica[1]);
            System.out.println("Promedio: " + estadistica[2]);
            System.out.println("Nota máxima: " + estadistica[3]);
            System.out.println("Nota mínima: " + estadistica[4]);
        } */
        
        /* Object[] estadistica = estadisticas.get(0);
        
        return new EstadisticaDTO(
                estadistica[0].toString(),
                (long)estadistica[1],
                (double)estadistica[2],
                (int)estadistica[3],
                (int)estadistica[4]); */
    }
    
    public EstadisticaDTO getEstadisticaCursoNativa(int id) {
        Query query = manager.createNamedQuery("Curso.getEstaditicaById", EstadisticaDTO.class);
        query.setParameter("id", id);
        return (EstadisticaDTO)query.getSingleResult();
    }
    
    public List<ResumenCursoDTO> getMatriculaCurso() {
        String str = "SELECT new com.laboratorio.persistencia08.dto.ResumenCursoDTO("
                + "c.id, c.nombre, c.nivel, a.nombre)"
                + " FROM Alumno a RIGHT JOIN a.curso c"
                + " ORDER BY c.nombre, c.nivel";
        TypedQuery<ResumenCursoDTO> query = manager.createQuery(str, ResumenCursoDTO.class);
        return query.getResultList();
    }
    
    public List<ResumenCursoDTO> getMatriculaTotal() {
        String str = "SELECT new com.laboratorio.persistencia08.dto.ResumenCursoDTO("
                + "c.id, c.nombre, c.nivel, a.nombre)"
                + " FROM Curso c, Alumno a"
                + " ORDER BY c.nombre, c.nivel";
        TypedQuery<ResumenCursoDTO> query = manager.createQuery(str, ResumenCursoDTO.class);
        return query.getResultList();
    }
    
    public int updateCursoName(int id, String nombre) {
        String sql = "UPDATE cursos SET nombre = :nombre WHERE id = :id";
        Query query = manager.createNativeQuery(sql);
        query.setParameter("nombre", nombre);
        query.setParameter("id", id);
        
        try {
            manager.getTransaction().begin();
            int nrows = query.executeUpdate();
            manager.getTransaction().commit();
            
            return nrows;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }
}