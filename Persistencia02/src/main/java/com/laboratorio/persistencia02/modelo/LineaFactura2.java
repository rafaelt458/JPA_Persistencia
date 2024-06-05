package com.laboratorio.persistencia02.modelo;

import com.laboratorio.persistencia02.modelo.pkey.LineaFacturaPK;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "linea_factura_2")
@IdClass(LineaFacturaPK.class)
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class LineaFactura2 {
    @Id
    private String numeroFactura;
    
    @Id
    private Integer codigoProducto;
    
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;
    
    @Column(name = "porcentaje_IVA", nullable = false)
    private int porcentajeIVA;
    
    @Column(name = "porcentaje_descuento", nullable = false)
    private int porcentajeDescuento;
}
