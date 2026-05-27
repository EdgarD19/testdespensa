package com.proyecto2.DespensaProyect.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "subcategoria_producto",
       uniqueConstraints = @UniqueConstraint(columnNames = {"nombre", "id_categoria"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"categoria", "productos"})
public class SubcategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategoria")
    private Long idSubcategoria;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false, referencedColumnName = "id_categoria")
    private CategoriaProducto categoria;

    @OneToMany(mappedBy = "subcategoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<Producto> productos;
}

