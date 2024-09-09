package com.laboratorio.persistencia08;

import com.laboratorio.persistencia08.dao.AlumnoDAO;
import com.laboratorio.persistencia08.dao.CursoDAO;
import com.laboratorio.persistencia08.dao.impl.AlumnoDAOImpl;
import com.laboratorio.persistencia08.dao.impl.CursoDAOImpl;
import com.laboratorio.persistencia08.dto.EstadisticaDTO;
import com.laboratorio.persistencia08.dto.ResumenAlumnoDTO;
import com.laboratorio.persistencia08.dto.ResumenCursoDTO;
import com.laboratorio.persistencia08.entidades.Alumno;
import com.laboratorio.persistencia08.entidades.Curso;
import com.laboratorio.persistencia08.persistencia.AlumnoDB;
import com.laboratorio.persistencia08.persistencia.CursoDB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class Persistencia08 {

    private static EntityManager manager;

    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab-persistence-unit")) {
            manager = factory.createEntityManager();
            cargaInicial();
            // parte16();
            // partes17y18();
            // parte19();
            // parte20();
            // parte21();

            AlumnoDB alumnoDB = new AlumnoDB(manager);

            System.out.println("");
            System.out.println("*** Promoción de curso 2 a 3");
            if (alumnoDB.promocionarAlumnos(2, 3)) {
                System.out.println("La promoción de curso se realizado correctamente");
            } else {
                System.out.println("Ha ocurrido un error realizando la promoción de curso");
            }
        }
    }

    /* Parte 15 */
    public static void problemaQueries() {
        manager.getTransaction().begin();

        System.out.println("");
        System.out.println("");
        System.out.println("*** Problema queries");

        // String str = "SELECT c FROM Curso c INNER JOIN c.alumnos a";
        String str = "SELECT c FROM Curso c JOIN FETCH c.alumnos a";
        Query query = manager.createQuery(str);
        List<Curso> cursos = query.getResultList();

        for (Curso c : cursos) {
            System.out.println("Curso: " + c.toString());
            for (Alumno a : c.getAlumnos()) {
                System.out.println("Alumno: " + a.toString());
            }
        }

        manager.getTransaction().commit();
    }

    public static void cargaInicial() {
        manager.getTransaction().begin();

        try {
            Curso c1 = new Curso("Matemáticas", 1, "Jorge Campos");
            Curso c2 = new Curso("Matemáticas", 2, "Ángela Pérez");
            Curso c3 = new Curso("Matemáticas", 3, "Oscar Landaeta");

            Alumno a01 = new Alumno("Pedro Pérez", LocalDate.of(2005, Month.APRIL, 15), "pedro@mail.com", 8, c1);
            Alumno a02 = new Alumno("Luis Vivas", LocalDate.of(2005, Month.FEBRUARY, 6), "luis@mail.com", 7, c1);
            Alumno a03 = new Alumno("Angel Puentes", LocalDate.of(2004, Month.JULY, 9), "angel@mail.com", 4, c1);
            Alumno a04 = new Alumno("Jorge Cadenas", LocalDate.of(2004, Month.AUGUST, 19), "jorge@mail.com", 6, c1);
            Alumno a05 = new Alumno("María Valero", LocalDate.of(2005, Month.SEPTEMBER, 5), "maria@mail.com", 3, c1);
            Alumno a06 = new Alumno("Natalia Moreno", LocalDate.of(2005, Month.FEBRUARY, 9), "natalia@mail.com", 9, c1);
            Alumno a07 = new Alumno("Guillermo Parada", LocalDate.of(2004, Month.MAY, 23), "guillermo@mail.com", 7, c1);
            Alumno a08 = new Alumno("Olga García", LocalDate.of(2004, Month.JANUARY, 29), "olga@mail.com", 3, c1);
            Alumno a09 = new Alumno("Teresa Camargo", LocalDate.of(2005, Month.FEBRUARY, 12), "teresa@mail.com", 8, c1);
            Alumno a10 = new Alumno("Roberto Sainz", LocalDate.of(2005, Month.APRIL, 14), "roberto@mail.com", 6, c1);
            Alumno a11 = new Alumno("Alexis Peña", LocalDate.of(2005, Month.APRIL, 25), "alexis@mail.com", 4, c1);
            Alumno a12 = new Alumno("Isabel Ortega", LocalDate.of(2004, Month.MAY, 12), "isabel@mail.com", 8, c1);
            Alumno a13 = new Alumno("Daniel Sánchez", LocalDate.of(2005, Month.APRIL, 15), "daniel@mail.com", 6, c1);
            Alumno a14 = new Alumno("Javier Gómez", LocalDate.of(2005, Month.SEPTEMBER, 25), "javier@mail.com", 5, c1);
            Alumno a15 = new Alumno("Arturo Sánchez", LocalDate.of(2005, Month.APRIL, 15), "arturo@mail.com", 7, c1);
            c1.setAlumnos(List.of(a01, a02, a03, a04, a05, a06, a07, a08, a09, a10, a11, a12, a13, a14, a15));
            manager.persist(c1);

            Alumno a16 = new Alumno("Angela Pérez", LocalDate.of(2004, Month.APRIL, 18), "angela@mail.com", 4, c2);
            Alumno a17 = new Alumno("Luisa González", LocalDate.of(2003, Month.OCTOBER, 16), "luisa@mail.com", 6, c2);
            Alumno a18 = new Alumno("José Alfaro", LocalDate.of(2004, Month.NOVEMBER, 19), "jose@mail.com", 7, c2);
            Alumno a19 = new Alumno("Carlos Cadenas", LocalDate.of(2004, Month.DECEMBER, 29), "carlos@mail.com", 8, c2);
            Alumno a20 = new Alumno("Angeles Valero", LocalDate.of(2003, Month.SEPTEMBER, 11), "angeles@mail.com", 7, c2);
            Alumno a21 = new Alumno("Josefina Moreno", LocalDate.of(2004, Month.FEBRUARY, 13), "josefina@mail.com", 9, c2);
            Alumno a22 = new Alumno("Antonio Parades", LocalDate.of(2004, Month.JANUARY, 23), "antonio@mail.com", 7, c2);
            Alumno a23 = new Alumno("Hortensia García", LocalDate.of(2004, Month.JANUARY, 24), "hortensia@mail.com", 3, c2);
            Alumno a24 = new Alumno("Marta Araujo", LocalDate.of(2003, Month.MAY, 12), "marta@mail.com", 3, c2);
            Alumno a25 = new Alumno("Julio Rojas", LocalDate.of(2004, Month.APRIL, 17), "julio@mail.com", 6, c2);
            Alumno a26 = new Alumno("Alvaro Ríos", LocalDate.of(2003, Month.APRIL, 22), "alvaro@mail.com", 4, c2);
            Alumno a27 = new Alumno("Indira Ortega", LocalDate.of(2004, Month.MAY, 17), "indira@mail.com", 8, c2);
            Alumno a28 = new Alumno("Dolores Díaz", LocalDate.of(2003, Month.JULY, 15), "dolores@mail.com", 6, c2);
            Alumno a29 = new Alumno("Jacinto Gómez", LocalDate.of(2004, Month.SEPTEMBER, 21), "jacinto@mail.com", 5, c2);
            Alumno a30 = new Alumno("Ramón Fernández", LocalDate.of(2003, Month.OCTOBER, 15), "ramon@mail.com", 7, c2);
            c2.setAlumnos(List.of(a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30));
            manager.persist(c2);

            manager.persist(c3);

            /* Parte 18 */
            Alumno a31 = new Alumno("Román Pérez", LocalDate.of(2005, Month.SEPTEMBER, 23), "roman@mail.com", 0, null);
            Alumno a32 = new Alumno("Alfonso Tovar", LocalDate.of(2004, Month.NOVEMBER, 26), "alfonso@mail.com", 0, null);
            manager.persist(a31);
            manager.persist(a32);

            Curso c4 = new Curso("Castellano", 1, "Esmeralda Contreras");
            Curso c5 = new Curso("Castellano", 2, "Esmeralda Contreras");

            Alumno a33 = new Alumno("Octavio Gómez", LocalDate.of(2004, Month.SEPTEMBER, 7), "octavio@mail.com", 9, c4);
            Alumno a34 = new Alumno("Rosa Castillo", LocalDate.of(2004, Month.NOVEMBER, 6), "rosa@mail.com", 7, c4);
            c4.setAlumnos(List.of(a33, a34));
            manager.persist(c4);

            Alumno a35 = new Alumno("Candela Díaz", LocalDate.of(2003, Month.JULY, 17), "candela@mail.com", 8, c5);
            Alumno a36 = new Alumno("Norma Rojas", LocalDate.of(2004, Month.JANUARY, 8), "norma@mail.com", 7, c5);
            c5.setAlumnos(List.of(a35, a36));
            manager.persist(c5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getMessage());
            }
            manager.getTransaction().rollback();
            return;
        }

        manager.getTransaction().commit();
    }

    public static void parte16() {
        AlumnoDB alumnoDB = new AlumnoDB(manager);
        List<Alumno> alumnos = alumnoDB.findAll();
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** FINDBYID");
        Optional<Alumno> alumno = alumnoDB.findById(2);
        if (alumno.isPresent()) {
            System.out.println("Alumno: " + alumno.toString());
        }

        alumno = alumnoDB.findById(50);
        if (alumno.isPresent()) {
            System.out.println("Alumno: " + alumno.toString());
        }

        System.out.println("");
        System.out.println("*** FINDBYNOMBRE");
        alumnos = alumnoDB.findByNombre("sánchez");
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("Número de alumnos: " + alumnoDB.count());

        System.out.println("");
        System.out.println("Promedio del curso 1: " + alumnoDB.getNotaAvg(1));

        System.out.println("");
        System.out.println("*** Promoción de curso 2 a 3");
        if (alumnoDB.promocionarAlumnos(2, 3)) {
            System.out.println("La promoción de curso se realizado correctamente");
        } else {
            System.out.println("Ha ocurrido un error realizando la promoción de curso");
        }

        System.out.println("");
        System.out.println("*** Promoción de curso 1 a 2");
        if (alumnoDB.promocionarAlumnos(1, 2)) {
            System.out.println("La promoción de curso se realizado correctamente");
        } else {
            System.out.println("Ha ocurrido un error realizando la promoción de curso");
        }
    }

    public static void partes17y18() {
        CursoDB cursoDB = new CursoDB(manager);
        // cursoDB.getEstadisticaCurso(1);

        EstadisticaDTO estadisticaDTO = cursoDB.getEstadisticaCurso(1);
        System.out.println("Estadíticas del curso 1: " + estadisticaDTO.toString());

        System.out.println("Estadíticas del curso 2: " + cursoDB.getEstadisticaCurso(2).toString());

        AlumnoDB alumnoDB = new AlumnoDB(manager);
        System.out.println("");
        System.out.println("*** Página 1 de alumnos");
        List<Alumno> alumnos = alumnoDB.findPage(1, 12);
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** Página 2 de alumnos");
        alumnos = alumnoDB.findPage(2, 12);
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** Página 3 de alumnos");
        alumnos = alumnoDB.findPage(3, 12);
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** Alumnos aprobados del curso 2");
        alumnos = alumnoDB.getAlumnosAprobados(2);
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** Matrícula de alumnos");
        List<ResumenAlumnoDTO> resumenAlumnos = alumnoDB.getMatriculaAlumnos();
        for (ResumenAlumnoDTO r : resumenAlumnos) {
            System.out.println("Alumno: " + r.toString());
        }

        System.out.println("");
        System.out.println("*** Matrícula de cursos");
        List<ResumenCursoDTO> resumenCursos = cursoDB.getMatriculaCurso();
        for (ResumenCursoDTO r : resumenCursos) {
            System.out.println("Curso: " + r.toString());
        }

        System.out.println("");
        System.out.println("*** Matrícula de total");
        resumenCursos = cursoDB.getMatriculaTotal();
        for (ResumenCursoDTO r : resumenCursos) {
            System.out.println("Curso: " + r.toString());
        }

        problemaQueries();
    }

    public static void parte19() {
        AlumnoDAO alumnoDAO = new AlumnoDAOImpl(manager);
        List<Alumno> alumnos = alumnoDAO.findAll();
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        CursoDAO cursoDAO = new CursoDAOImpl(manager);
        Optional<Curso> curso = cursoDAO.findById(2);
        if (curso.isPresent()) {
            System.out.println("Curso: " + curso.toString());
        }
    }

    public static void parte20() {
        AlumnoDB alumnoDB = new AlumnoDB(manager);
        List<Alumno> alumnos = alumnoDB.findAllAC();
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** Página 2 de alumnos");
        alumnos = alumnoDB.findPageAC(2, 12);
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** FINDBYID");
        Optional<Alumno> alumno = alumnoDB.findByIdAC(2);
        if (alumno.isPresent()) {
            System.out.println("Alumno: " + alumno.toString());
        }

        System.out.println("");
        System.out.println("*** FINDBYNOMBRE");
        alumnos = alumnoDB.findByNombreAC("sánchE");
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("Número de alumnos: " + alumnoDB.count());

        System.out.println("");
        System.out.println("Promedio del curso 1: " + alumnoDB.getNotaAvg(1));

        System.out.println("");
        System.out.println("*** Alumnos aprobados del curso 2");
        alumnos = alumnoDB.getAlumnosAprobados(2);
        for (Alumno a : alumnos) {
            System.out.println("Alumno: " + a.toString());
        }

        System.out.println("");
        System.out.println("*** Matrícula de alumnos");
        List<ResumenAlumnoDTO> resumenAlumnos = alumnoDB.getMatriculaAlumnos();
        for (ResumenAlumnoDTO r : resumenAlumnos) {
            System.out.println("Alumno: " + r.toString());
        }

        System.out.println("");
        System.out.println("*** Promoción de curso 2 a 3");
        if (alumnoDB.promocionarAlumnos(2, 3)) {
            System.out.println("La promoción de curso se realizado correctamente");
        } else {
            System.out.println("Ha ocurrido un error realizando la promoción de curso");
        }
    }

    public static void parte21() {
        CursoDB cursoDB = new CursoDB(manager);
        Optional<Curso> curso = cursoDB.findById(2);
        if (curso.isPresent()) {
            System.out.println("Curso 2: " + curso.get().toString());
        }

        EstadisticaDTO estadisticaDTO = cursoDB.getEstadisticaCursoNativa(1);
        System.out.println("");
        System.out.println("Estadíticas del curso 1: " + estadisticaDTO.toString());

        System.out.println("");
        if (cursoDB.updateCursoName(1, "Nuevo nombre") >= 1) {
            System.out.println("Se actualizó el curso");
        }
    }
}
