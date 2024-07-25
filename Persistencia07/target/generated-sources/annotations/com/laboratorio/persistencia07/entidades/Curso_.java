package com.laboratorio.persistencia07.entidades;

import com.laboratorio.persistencia07.entidades.Alumno;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-29T17:57:05", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile ListAttribute<Curso, Alumno> alumnos;
    public static volatile SingularAttribute<Curso, String> profesor;
    public static volatile SingularAttribute<Curso, Integer> id;
    public static volatile SingularAttribute<Curso, String> nombre;
    public static volatile SingularAttribute<Curso, Integer> nivel;

}