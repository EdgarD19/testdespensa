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

    @Column(name = "tipo_cliente", length = 20)
    private String tipoCliente;

    @Column(name = "razon_social", length = 200)
    private String razonSocial;

    @Column(name = "ruc", length = 50)
    private String ruc;

    @Column(name = "descripcion_empresa", columnDefinition = "TEXT")
    private String descripcionEmpresa;

    @Column(name = "contacto_nombre", length = 100)
    private String contactoNombre;

    @Column(name = "contacto_celular", length = 50)
    private String contactoCelular;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "celular", length = 50)
    private String celular;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "activo")
    @Builder.Default
    private Boolean activo = true;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Factura> facturas;

    public Cliente(Long idCliente, String firstName, String lastName, String phone) {
        this.idCliente = idCliente;
        setFirstName(firstName);
        setLastName(lastName);
        this.phone = phone;
    }

    public String getNombreCompleto() {
        if (getRazonSocial() != null && !getRazonSocial().isEmpty()) {
            return getRazonSocial();
        }
        if (getLastName() != null && !getLastName().isEmpty()) {
            return getFirstName() + " " + getLastName();
        }
        return getFirstName();
    }
}
