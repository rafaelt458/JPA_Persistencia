package com.laboratorio.persistencia01.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Empleado {
    @Id
    private Integer codigo;
    
    private String nombres;
    
    private LocalDate fechaNac;
    
    private double salario;

    public Empleado() {
    }

    public Empleado(Integer codigo, String nombres, LocalDate fechaNac, double salario) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.fechaNac = fechaNac;
        this.salario = salario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "codigo=" + codigo + ", nombres=" + nombres + ", fechaNac=" + fechaNac + ", salario=" + salario + '}';
    }
}