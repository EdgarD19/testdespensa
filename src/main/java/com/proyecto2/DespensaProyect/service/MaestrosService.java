package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import com.proyecto2.DespensaProyect.domain.entity.SubcategoriaProducto;
import com.proyecto2.DespensaProyect.domain.entity.UnidadMedida;
import com.proyecto2.DespensaProyect.model.response.CategoryResponse;
import com.proyecto2.DespensaProyect.model.response.MaestroItemResponse;
import com.proyecto2.DespensaProyect.repository.CategoriaProductoRepository;
import com.proyecto2.DespensaProyect.repository.ProveedorRepository;
import com.proyecto2.DespensaProyect.repository.SubcategoriaProductoRepository;
import com.proyecto2.DespensaProyect.repository.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MaestrosService {

    @Autowired
    private CategoriaProductoRepository categoriaRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private SubcategoriaProductoRepository subcategoriaRepository;

    public List<CategoryResponse> listCategorias() {
        return categoriaRepository.findAll().stream()
                .map(c -> CategoryResponse.builder()
                        .id(c.getIdCategoria())
                        .nombre(c.getNombre())
                        .build())
                .toList();
    }

    public List<MaestroItemResponse> listUnidades() {
        return unidadMedidaRepository.findAll().stream()
                .map(this::toUnidadResponse)
                .toList();
    }

    public List<MaestroItemResponse> listProveedores() {
        return proveedorRepository.findAll().stream()
                .map(p -> MaestroItemResponse.builder()
                        .id(p.getIdProveedor())
                        .nombre(p.getNombre())
                        .build())
                .toList();
    }

    public List<MaestroItemResponse> listSubcategorias(Long idCategoria) {
        List<SubcategoriaProducto> lista = idCategoria != null
                ? subcategoriaRepository.findByCategoriaIdCategoria(idCategoria)
                : subcategoriaRepository.findAll();
        return lista.stream()
                .map(s -> MaestroItemResponse.builder()
                        .id(s.getIdSubcategoria())
                        .nombre(s.getNombre())
                        .build())
                .toList();
    }

    private MaestroItemResponse toUnidadResponse(UnidadMedida u) {
        return MaestroItemResponse.builder()
                .id(u.getIdUnidad())
                .nombre(u.getNombre())
                .abreviatura(u.getAbreviatura())
                .build();
    }
}
