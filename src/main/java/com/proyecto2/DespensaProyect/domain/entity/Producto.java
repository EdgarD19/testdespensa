package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {
        "proveedores",
        "priceHistory",
        "detallesInventario",
        "movimientosInventario",
        "detallesFactura",
        "detallesFacturaCompra",
        "detallesOrdenCompra"
})
@EqualsAndHashCode(exclude = {
        "proveedores",
        "priceHistory",
        "detallesInventario",
        "movimientosInventario",
        "detallesFactura",
        "detallesFacturaCompra",
        "detallesOrdenCompra"
})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "codigo_producto", length = 20, unique = true, nullable = false)
    private String codigoBarra;

    @Column(name = "precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock_actual", precision = 12, scale = 2, nullable = false)
    private BigDecimal stockActual = BigDecimal.ZERO;

    @Column(name = "producto_pesable")
    private Boolean productoPesable = false;

    @Column(name = "precio_por_kg", precision = 12, scale = 2)
    private BigDecimal precioPorKg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    private Marca marca;

    @Column(name = "precio_compra", precision = 12, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "stock_minimo", precision = 12, scale = 2)
    private BigDecimal stockMinimo;

    @Column(name = "contenido", length = 100)
    private String contenido;

    @Column(name = "activo")
    private Boolean activo = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subcategoria", referencedColumnName = "id_subcategoria")
    private SubcategoriaProducto subcategoria;

    // Relación muchos a uno con UnidadMedida
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad", referencedColumnName = "id_unidad")
    private UnidadMedida unidadMedida;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoProveedor> proveedores;

    // Relación uno a muchos con HistorialPrecioProducto
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistorialPrecioProducto> priceHistory;

    // Relación uno a muchos con DetalleInventario
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleInventario> detallesInventario;

    // Relación uno a muchos con MovimientoInventario
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientosInventario;

    // Relación uno a muchos con DetalleFactura
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleFactura> detallesFactura;

    // Relación uno a muchos con DetalleFacturaCompra
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleFacturaCompra> detallesFacturaCompra;

    // Relación uno a muchos con DetalleOrdenCompra
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleOrdenCompra> detallesOrdenCompra;

    // Constructor personalizado sin relaciones
    public Producto(Long idProducto, String nombre, String descripcion,
                    BigDecimal precio, BigDecimal stockActual) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockActual = stockActual;
    }

    // Métodos helper

    /**
     * Verifica si hay stock disponible
     */
    public boolean tieneStock(BigDecimal cantidad) {
        return stockActual.compareTo(cantidad) >= 0;
    }

    /**
     * Incrementa el stock
     */
    public void incrementarStock(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.stockActual = this.stockActual.add(cantidad);
    }

    /**
     * Decrementa el stock
     */
    public void decrementarStock(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (!tieneStock(cantidad)) {
            throw new IllegalStateException(
                    "Stock insuficiente. Disponible: " + stockActual + ", Solicitado: " + cantidad
            );
        }
        this.stockActual = this.stockActual.subtract(cantidad);
    }

    /**
     * Verifica si el stock está bajo
     */
    public boolean stockBajo(BigDecimal stockMinimo) {
        return stockActual.compareTo(stockMinimo) < 0;
    }
}