package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"contactos", "productos", "facturasCompra", "ordenesCompra", "rubros"})
@EqualsAndHashCode(exclude = {"contactos", "productos", "facturasCompra", "ordenesCompra", "rubros"})
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "tipo_persona", length = 20)
    private String tipoPersona;

    @Column(name = "apellido", length = 100)
    private String apellido;

    @Column(name = "tipo_documento", length = 20)
    private String tipoDocumento;

    @Column(name = "numero_documento", length = 50, unique = true)
    private String numeroDocumento;

    @Column(name = "descripcion_negocio", columnDefinition = "TEXT")
    private String descripcionNegocio;

    @Column(name = "persona_contacto", length = 150)
    private String personaContacto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nacionalidad_id_pais")
    private Pais nacionalidad;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "celular", length = 50)
    private String celular;

    @Column(name = "forma_pago", length = 20)
    private String formaPago;

    @Column(name = "banco", length = 100)
    private String banco;

    @Column(name = "numero_cuenta", length = 50)
    private String numeroCuenta;

    @Column(name = "documento_transferencia", length = 50)
    private String documentoTransferencia;

    @Column(name = "nombre_razon_social", length = 200)
    private String nombreRazonSocial;

    @Column(name = "alias", length = 100)
    private String alias;

    @Column(name = "activo")
    @Builder.Default
    private Boolean activo = true;

    @ManyToMany
    @JoinTable(
        name = "proveedor_rubro",
        joinColumns = @JoinColumn(name = "id_proveedor"),
        inverseJoinColumns = @JoinColumn(name = "id_rubro")
    )
    private List<Rubro> rubros;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContactoProveedor> contactos;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoProveedor> productos;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FacturaCompra> facturasCompra;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrdenCompra> ordenesCompra;
}
