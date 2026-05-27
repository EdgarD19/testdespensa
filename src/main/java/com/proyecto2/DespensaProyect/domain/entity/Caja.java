package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "caja")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"empleado", "movimientos"})
@EqualsAndHashCode(exclude = {"empleado", "movimientos"})
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caja")
    private Long idCaja;

    @Column(name = "fecha_apertura")
    private LocalDateTime fechaApertura = LocalDateTime.now();

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "monto_inicial", precision = 12, scale = 2, nullable = false)
    private BigDecimal montoInicial = BigDecimal.ZERO;

    @Column(name = "monto_final", precision = 12, scale = 2, nullable = false)
    private BigDecimal montoFinal = BigDecimal.ZERO;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado = "ABIERTA";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private Empleado empleado;

    @OneToMany(mappedBy = "caja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovimientoCaja> movimientos;

    public boolean estaAbierta() {
        return "ABIERTA".equalsIgnoreCase(estado);
    }

    public boolean estaCerrada() {
        return "CERRADA".equalsIgnoreCase(estado);
    }
}