package com.proyecto2.DespensaProyect.domain.entity;

import com.proyecto2.DespensaProyect.domain.PersonBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"facturas"})
public class Cliente extends PersonBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "phone", length = 30)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Factura> facturas;

    // Constructor sin relaciones
    public Cliente(Long idCliente, String firstName, String lastName, String phone) {
        this.idCliente = idCliente;
        setFirstName(firstName);
        setLastName(lastName);
        this.phone = phone;
    }

    // Método helper
    public String getNombreCompleto() {
        if (getLastName() != null && !getLastName().isEmpty()) {
            return getFirstName() + " " + getLastName();
        }
        return getFirstName();
    }
}