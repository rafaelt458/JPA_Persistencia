package com.laboratorio.persistencia07.entidades;

import com.laboratorio.persistencia07.entidades.Curso;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-29T17:57:05", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, LocalDate> fechaNac;
    public static volatile SingularAttribute<Alumno, Curso> curso;
    public static volatile SingularAttribute<Alumno, Integer> id;
    public static volatile SingularAttribute<Alumno, String> nombre;
    public static volatile SingularAttribute<Alumno, Integer> nota;
    public static volatile SingularAttribute<Alumno, String> email;

}