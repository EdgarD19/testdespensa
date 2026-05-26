package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transferencia_pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"factura"})
@EqualsAndHashCode(exclude = {"factura"})
public class TransferenciaPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Long idTransferencia;

    @Column(name = "banco", length = 100)
    private String banco;

    @Column(name = "numero_comprobante", length = 100)
    private String numeroComprobante;

    @Column(name = "titular", length = 150)
    private String titular;

    @Column(name = "fecha_transferencia", nullable = false)
    private LocalDateTime fechaTransferencia;

    @Column(name = "monto", nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    private Factura factura;
}
