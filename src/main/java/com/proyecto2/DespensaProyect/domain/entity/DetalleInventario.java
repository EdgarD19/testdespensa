package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"inventario" , "producto"})
@EqualsAndHashCode(exclude = {"inventario", "producto"})
public class DetalleInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_inventario")
    private Long idDetalleInventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario", referencedColumnName = "id_inventario")
    private Inventario inventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @Column(name = "cantidad_sistema", precision = 10, scale = 2)
    private BigDecimal cantidadSistema;

    @Column(name = "cantidad_fisica", precision = 10, scale = 2)
    private BigDecimal cantidadFisica;

    @Column(name = "diferencia", precision = 10, scale = 2)
    private BigDecimal diferencia;

    // Método helper para calcular diferencia automáticamente
    @PrePersist
    @PreUpdate
    public void calcularDiferencia() {
        if (cantidadSistema != null && cantidadFisica != null) {
            this.diferencia = cantidadFisica.subtract(cantidadSistema);
        }
    }

    public boolean tieneDiferencia() {
        return diferencia != null && diferencia.compareTo(BigDecimal.ZERO) != 0;
    }
}