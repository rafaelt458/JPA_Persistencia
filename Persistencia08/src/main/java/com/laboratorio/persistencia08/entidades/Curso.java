package com.laboratorio.persistencia08.entidades;

import com.laboratorio.persistencia08.dto.EstadisticaDTO;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cursos")
@SqlResultSetMapping(
        name = "EstadisticasCursoMapping",
        classes = @ConstructorResult(
                targetClass = EstadisticaDTO.class,
                columns = {
                    @ColumnResult(name = "nombreCurso"),
                    @ColumnResult(name = "nroAlumnos"),
                    @ColumnResult(name = "promedio"),
                    @ColumnResult(name = "notaMaxima"),
                    @ColumnResult(name = "notaMinima")
                }
        )
)
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "Curso.findById",
            query = "SELECT * FROM cursos WHERE id = :id",
            resultClass = Curso.class
    ),
    @NamedNativeQuery(
            name = "Curso.getEstaditicaById",
            query = "SELECT c.nombre AS nombreCurso, COUNT(a.*) AS nroAlumnos,"
                    + " AVG(a.nota_alumno) AS promedio, MAX(a.nota_alumno) AS notaMaxima,"
                    + " MIN(a.nota_alumno) AS notaMinima"
                    + " FROM Cursos c INNER JOIN Alumnos a ON c.id = a.id_curso"
                    + " WHERE c.id = :id GROUP BY c.nombre",
            resultSetMapping = "EstadisticasCursoMapping"
    )
})
@Getter @Setter @NoArgsConstructor
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "nivel", nullable = false)
    private int nivel;
    
    @Column(name = "profesor", length = 50, nullable = false)
    private String profesor;
    
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "curso", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Alumno> alumnos;

    public Curso(String nombre, int nivel, String profesor) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nombre=" + nombre + ", nivel=" + nivel + ", profesor=" + profesor + '}';
    }
}