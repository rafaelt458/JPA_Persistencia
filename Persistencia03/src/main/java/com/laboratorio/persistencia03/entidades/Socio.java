package com.laboratorio.persistencia03.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "socios")
@Getter @Setter @NoArgsConstructor
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    
    @Column(name = "numero_documento", length = 20, nullable = false, unique = true)
    private String nroDocumento;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNac;
    
    /* @OneToOne(optional = false)
    TarjetaSocio tarjetaSocio;

    public Socio(String nombre, String nroDocumento, LocalDate fechaNac, TarjetaSocio tarjetaSocio) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
        this.tarjetaSocio = tarjetaSocio;
    } */
    
    /* @OneToOne(mappedBy = "socio")
    TarjetaSocio tarjetaSocio; */
    
    public Socio(String nombre, String nroDocumento, LocalDate fechaNac) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
    }

    /*
    @Override
    public String toString() {
        return "Socio{" + "id=" + id + ", nombre=" + nombre + ", nroDocumento=" + nroDocumento + ", fechaNac=" + fechaNac + ", tarjetaSocio=" + tarjetaSocio + '}';
    } */   
}