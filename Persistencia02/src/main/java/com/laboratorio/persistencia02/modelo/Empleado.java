package com.laboratorio.persistencia02.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

@Entity
@Table( name = "empleados",
        schema = "persistencia",
        uniqueConstraints = {@UniqueConstraint(name = "telefono_unique", columnNames = {"telefono"})},
        indexes = {@Index(columnList = "fecha_nacimiento", unique = false)}
)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Empleado {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "secuencia_empleado", sequenceName = "empleado_secuencia", initialValue = 10, allocationSize = 20)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_empleado")
    //@TableGenerator(name = "empleado_seq", table = "tabla_secuencias")
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "empleado_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    
    @Column(name = "nombres", nullable = false, length = 60)
    private String nombres;
    
    @Column(name="documento", nullable = false, length = 15)
    @NaturalId
    private String documento;
    
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNac;
    
    // @Transient
    @Formula("DATE_PART('year', AGE(fecha_nacimiento))")
    private int edad;
    
    // @Column(name = "tipo_empleado", nullable = false, columnDefinition = "SMALLINT default 1")
    @Column(name = "tipo_empleado", nullable = false)
    private short tipo;
    
    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;
    
    public int calcularEdad() {
        Period period = Period.between(fechaNac, LocalDate.now());
        return period.getYears();
    }

    public Empleado(String nombres, String documento, String telefono, LocalDate fechaNac, int edad, short tipo, BigDecimal salario) {
        this.nombres = nombres;
        this.documento = documento;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.edad = edad;
        this.tipo = tipo;
        this.salario = salario;
    }
}