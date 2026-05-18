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
@EqualsAndHashCode(exclude = {"contactos", "pedidos", "facturas"})
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
    private List<ContactoCliente> contactos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Factura> facturas;

    // Constructor sin relaciones
    public Cliente(Long idCliente, String nombre, String apellido, String documento, String telefono) {
        this.idCliente = idCliente;
        this.phone = telefono;
    }

    // Método helper
    public String getNombreCompleto() {
        if (getLastName() != null && !getLastName().isEmpty()) {
            return getFirstName() + " " + getLastName();
        }
        return getLastName();
    }


}