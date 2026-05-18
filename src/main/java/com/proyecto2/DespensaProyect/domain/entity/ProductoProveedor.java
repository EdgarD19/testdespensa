package com.proyecto2.DespensaProyect.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"producto", "proveedor"})
@EqualsAndHashCode(exclude = {"producto", "proveedor"})
public class ProductoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_proveedor")
    private Long idProductoProveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @Column(name = "precio_costo", precision = 10, scale = 2)
    private BigDecimal precioCosto;

    @Column(name = "codigo_proveedor", length = 100)
    private String codigoProveedor;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;
}