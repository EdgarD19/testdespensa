package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_factura_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"facturaCompra", "producto"})
@EqualsAndHashCode(exclude = {"facturaCompra", "producto"})
public class DetalleFacturaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_factura_compra")
    private Long idDetalleFacturaCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura_compra", referencedColumnName = "id_factura_compra")
    private FacturaCompra facturaCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @Column(name = "cantidad", precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Transient
    private BigDecimal subtotal;

    public BigDecimal getSubtotal() {
        if (cantidad != null && precioUnitario != null) {
            return cantidad.multiply(precioUnitario);
        }
        return BigDecimal.ZERO;
    }
}