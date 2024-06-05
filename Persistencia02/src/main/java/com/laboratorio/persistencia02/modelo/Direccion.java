package com.laboratorio.persistencia02.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "direcciones")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Direccion {
    @Id
    // @TableGenerator(name = "direccion_seq", table = "tabla_secuencias")
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "direccion_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;
    
    @Column(name = "ciudad", nullable = false, length = 20)
    private String ciudad;
    
    @Column(name = "pais", nullable = false, length = 30)
    private String pais;

    public Direccion(String direccion, String ciudad, String pais) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
    }
}
