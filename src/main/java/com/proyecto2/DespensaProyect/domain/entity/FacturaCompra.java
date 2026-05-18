package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "factura_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"proveedor", "detalles"})
@EqualsAndHashCode(exclude = {"proveedor", "detalles"})
public class FacturaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura_compra")
    private Long idFacturaCompra;

    @Column(name = "fecha")
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    private Proveedor proveedor;

    @Column(name = "total", precision = 12, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "facturaCompra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleFacturaCompra> detalles;

    public void calcularTotal() {
        if (detalles != null && !detalles.isEmpty()) {
            this.total = detalles.stream()
                    .map(DetalleFacturaCompra::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}