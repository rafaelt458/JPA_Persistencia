package com.laboratorio.persistencia03.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarjetas_socios")
@Getter @Setter @NoArgsConstructor
public class TarjetaSocio {
    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "numero", nullable = false, length = 20, unique = true)
    private String numero;
    
    @Column(name = "miembro_desde", nullable = false)
    private LocalDate miembroDesde;
    
    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDate fechaExpiracion;
    
    @Column(name = "activa", nullable = false)
    private boolean activa;

    /* public TarjetaSocio(String numero) {
        this.numero = numero;
        this.miembroDesde = LocalDate.now();
        this.fechaExpiracion = LocalDate.now().plusYears(3);
        this.activa = true;
    } */
    
    @OneToOne(optional = false)
    @MapsId
    private Socio socio;
    
    public TarjetaSocio(String numero, Socio socio) {
        this.numero = numero;
        this.miembroDesde = LocalDate.now();
        this.fechaExpiracion = LocalDate.now().plusYears(3);
        this.activa = true;
        this.socio = socio;
    }
}
