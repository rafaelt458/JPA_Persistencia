package com.laboratorio.persistencia08.persistencia;

import com.laboratorio.persistencia08.dto.ResumenAlumnoDTO;
import com.laboratorio.persistencia08.entidades.Alumno;
import com.laboratorio.persistencia08.entidades.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class AlumnoDB {

    private final EntityManager manager;

    public AlumnoDB(EntityManager manager) {
        this.manager = manager;
    }

    public List<Alumno> findAll() {
        /* String str = "SELECT a FROM Alumno a ORDER BY a.nombre";
        TypedQuery<Alumno> query = manager.createQuery(str, Alumno.class); */

        TypedQuery<Alumno> query = manager.createNamedQuery("Alumno.findAll", Alumno.class);

        return query.getResultList();
    }

    public List<Alumno> findAllAC() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente)
                .orderBy(cb.asc(fuente.get("nombre")));

        return manager.createQuery(query)
                .getResultList();
    }

    public List<Alumno> findPage(int pageNumber, int pageSize) {
        TypedQuery<Alumno> query = manager.createNamedQuery("Alumno.findAll", Alumno.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<Alumno> findPageAC(int pageNumber, int pageSize) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente)
                .orderBy(cb.asc(fuente.get("nombre")));

        return manager.createQuery(query)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Alumno> findById(Integer id) {
        /* String str = "SELECT a FROM Alumno a WHERE a.id = :id";
        TypedQuery<Alumno> query = manager.createQuery(str, Alumno.class);
        query.setParameter("id", id); */

        String str = "SELECT a FROM Alumno a WHERE a.id = ?1";
        TypedQuery<Alumno> query = manager.createQuery(str, Alumno.class);
        query.setParameter(1, id);

        try {
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

    public Optional<Alumno> findByIdAC(Integer id) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente)
                .where(cb.equal(fuente.get("id"), id));

        try {
            return Optional.of(manager.createQuery(query).getSingleResult());
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

    public List<Alumno> findByNombre(String nombre) {
        String str = "SELECT a FROM Alumno a WHERE UPPER(a.nombre) LIKE '%' || UPPER(:nombre) || '%' ORDER BY a.id";
        TypedQuery<Alumno> query = manager.createQuery(str, Alumno.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }

    public List<Alumno> findByNombreAC(String nombre) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        String patron = "%" + nombre.toUpperCase() + "%";
        query.select(fuente)
                .where(cb.like(cb.upper(fuente.get("nombre")), patron))
                .orderBy(cb.asc(fuente.get("id")));

        return manager.createQuery(query)
                .getResultList();
    }

    public long count() {
        String str = "SELECT COUNT(a) FROM Alumno a";
        TypedQuery<Long> query = manager.createQuery(str, Long.class);
        return query.getSingleResult();
    }

    public long countAC() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(cb.count(fuente));

        return manager.createQuery(query)
                .getSingleResult();
    }

    public double getNotaAvg(int idCurso) {
        /* String str = "SELECT AVG(a.nota) FROM Alumno a WHERE a.curso.id = :id";
        TypedQuery<Double> query = manager.createQuery(str, Double.class);
        query.setParameter("id", idCurso);
        return query.getSingleResult(); */

        String str = "SELECT a FROM Alumno a WHERE a.curso.id = :id";
        TypedQuery<Alumno> query = manager.createQuery(str, Alumno.class);
        query.setParameter("id", idCurso);

        return query.getResultStream()
                .mapToInt(Alumno::getNota)
                .average().getAsDouble();
    }

    public double getNotaAvgAC(int idCurso) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(cb.avg(fuente.get("nota")))
                .where(cb.equal(fuente.get("curso").get("id"), idCurso));

        return manager.createQuery(query)
                .getSingleResult();
    }

    public boolean promocionarAlumnos(int idOrigen, int idDestino) {
        String str1 = "UPDATE Alumno a SET a.curso.id = :idDestino, a.nota = 0 WHERE a.curso.id = :idOrigen AND a.nota >= 5";
        String str2 = "DELETE FROM Alumno a WHERE a.curso.id = :idOrigen AND a.nota < 5";

        try {
            manager.getTransaction().begin();

            Query query1 = manager.createQuery(str1);
            query1.setParameter("idOrigen", idOrigen);
            query1.setParameter("idDestino", idDestino);
            int result1 = query1.executeUpdate();

            Query query2 = manager.createQuery(str2);
            query2.setParameter("idOrigen", idOrigen);
            int result2 = query2.executeUpdate();

            manager.getTransaction().commit();
            System.out.println(String.format("%d alumnos han pasado al siguiente curso y %d han reprobado.", result1, result2));
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getMessage());
            }
            return false;
        }
    }

    public boolean promocionarAlumnosAC(int idOrigen, int idDestino) {
        try {
            manager.getTransaction().begin();

            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaUpdate<Alumno> update = cb.createCriteriaUpdate(Alumno.class);
            Root<Alumno> fuente1 = update.from(Alumno.class);

            update.set(fuente1.get("curso").get("id"), idDestino)
                    .set(fuente1.get("nota"), 0)
                    .where(
                            cb.equal(fuente1.get("curso").get("id"), idOrigen),
                            cb.greaterThanOrEqualTo(fuente1.get("nota"), 5));

            Query query1 = manager.createQuery(update);
            int result1 = query1.executeUpdate();

            CriteriaDelete<Alumno> delete = cb.createCriteriaDelete(Alumno.class);
            Root<Alumno> fuente2 = delete.from(Alumno.class);

            delete.where(
                    cb.equal(fuente2.get("curso").get("id"), idOrigen),
                    cb.lessThan(fuente2.get("nota"), 5));

            Query query2 = manager.createQuery(delete);
            int result2 = query2.executeUpdate();

            manager.getTransaction().commit();
            System.out.println(String.format("%d alumnos han pasado al siguiente curso y %d han reprobado.", result1, result2));
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getMessage());
            }
            return false;
        }
    }
    
    public boolean promocionarAlumnosSP(int idOrigen, int idDestino) {
        try {
            /* StoredProcedureQuery query = manager.createStoredProcedureQuery("promover_alumnos");
            query.registerStoredProcedureParameter("idOrigen", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("idDestino", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nro_actualizaciones", Integer.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("nro_eliminaciones", Integer.class, ParameterMode.OUT); */
            
            StoredProcedureQuery query = manager.createNamedStoredProcedureQuery("Alumno.promocionarAumnos");
            
            query.setParameter("idOrigen", idOrigen);
            query.setParameter("idDestino", idDestino);
            
            query.execute();
            
            int result1 = (int)query.getOutputParameterValue("nro_actualizaciones");
            int result2 = (int)query.getOutputParameterValue("nro_eliminaciones");
            
            System.out.println(String.format("%d alumnos han pasado al siguiente curso y %d han reprobado.", result1, result2));
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getMessage());
            }
            return false;
        }
    }
    
    public List<Alumno> getAlumnosAprobados(int id) {
        String str = "SELECT a FROM Alumno a INNER JOIN a.curso c WHERE c.id = :id AND a.nota >= 5 ORDER BY a.nombre";
        TypedQuery<Alumno> query = manager.createQuery(str, Alumno.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Alumno> getAlumnosAprobadosAC(int id) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente)
                .where(
                        cb.equal(fuente.get("curso").get("id"), id),
                        cb.greaterThanOrEqualTo(fuente.get("nota"), 5)
                )
                .orderBy(cb.asc(fuente.get("nombre")));

        return manager.createQuery(query)
                .getResultList();
    }

    public List<ResumenAlumnoDTO> getMatriculaAlumnos() {
        String str = "SELECT new com.laboratorio.persistencia08.dto.ResumenAlumnoDTO("
                + "a.id, a.nombre, c.nombre)"
                + " FROM Alumno a LEFT JOIN a.curso c"
                + " ORDER BY a.nombre";
        TypedQuery<ResumenAlumnoDTO> query = manager.createQuery(str, ResumenAlumnoDTO.class);
        return query.getResultList();
    }

    public List<ResumenAlumnoDTO> getMatriculaAlumnosAC() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<ResumenAlumnoDTO> query = cb.createQuery(ResumenAlumnoDTO.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        Join<Alumno, Curso> curso = fuente.join("curso", JoinType.LEFT);

        query.multiselect(
                fuente.get("id"),
                fuente.get("nombre"),
                curso.get("nombre"))
                .orderBy(cb.asc(fuente.get("nombre")));

        return manager.createQuery(query)
                .getResultList();
    }
}
