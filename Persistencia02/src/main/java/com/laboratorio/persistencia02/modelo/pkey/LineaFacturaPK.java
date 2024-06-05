package com.laboratorio.persistencia02.modelo.pkey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class LineaFacturaPK implements Serializable {
    @Column(name = "numero_factura", length = 20)
    private String numeroFactura;
    
    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @Override
    public int hashCode() {
        return Objects.hash(this.numeroFactura, this.codigoProducto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineaFacturaPK other = (LineaFacturaPK) obj;
        if (!Objects.equals(this.numeroFactura, other.numeroFactura)) {
            return false;
        }
        return Objects.equals(this.codigoProducto, other.codigoProducto);
    }
}