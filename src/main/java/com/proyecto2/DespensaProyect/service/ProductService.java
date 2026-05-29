package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.Marca;
import com.proyecto2.DespensaProyect.domain.entity.Producto;
import com.proyecto2.DespensaProyect.domain.entity.ProductoProveedor;
import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import com.proyecto2.DespensaProyect.domain.entity.SubcategoriaProducto;
import com.proyecto2.DespensaProyect.domain.entity.UnidadMedida;
import com.proyecto2.DespensaProyect.mapper.ProductMapper;
import com.proyecto2.DespensaProyect.model.detailResponse.ProductResponse;
import com.proyecto2.DespensaProyect.model.request.PatchRequest;
import com.proyecto2.DespensaProyect.model.request.ProductRequest;
import com.proyecto2.DespensaProyect.model.response.ProductsResponse;
import com.proyecto2.DespensaProyect.repository.ProductoProveedorRepository;
import com.proyecto2.DespensaProyect.repository.MarcaRepository;
import com.proyecto2.DespensaProyect.repository.ProductoRepository;
import com.proyecto2.DespensaProyect.repository.ProveedorRepository;
import com.proyecto2.DespensaProyect.repository.SubcategoriaProductoRepository;
import com.proyecto2.DespensaProyect.repository.UnidadMedidaRepository;
import com.proyecto2.DespensaProyect.repository.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductoRepository productRepository;

    @Autowired
    private SubcategoriaProductoRepository subcategoryRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoProveedorRepository productoProveedorRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public ProductsResponse getProducts(String search, Integer page, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortBy != null && !sortBy.isEmpty()) ? Sort.by(Sort.Direction.fromString(sortDir), sortBy) : Sort.unsorted();
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Specification<Producto> spec = Specification.where(ProductSpecification.hasSearch(search));

        Page<Producto> productPage = productRepository.findAll(spec, pageable);
        Page<ProductResponse> responsePage = productPage.map(mapper::toResponse);
        return new ProductsResponse(responsePage);
    }

    public ProductResponse getProductsById(Long id) {
        return productRepository.findById(id).map(mapper::toResponse).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Optional<ProductResponse> getProductByBarcode(String code) {
        String normalizedCode = code.trim();
        return productRepository.findByCodigoBarra(normalizedCode).map(mapper::toResponse);
    }

    public void createProduct(ProductRequest request) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(request.getIdUnidadMedida()).orElseThrow(() -> new RuntimeException("Unidad de medida invalido o no disponible"));
        Proveedor proveedor = proveedorRepository.findById(request.getIdProveedor()).orElseThrow(() -> new RuntimeException("Proveedor invalido o no existe"));
        
        Producto newProduct = request.toEntity();
        newProduct.setNombre(request.getNombre());
        newProduct.setDescripcion(request.getDescripcion());
        newProduct.setPrecio(request.getPrecio());
        newProduct.setStockActual(request.getStockActual());
        if (request.getIdSubcategoria() != null) {
            SubcategoriaProducto subcategoria = subcategoryRepository.findById(request.getIdSubcategoria()).orElseThrow(() -> new RuntimeException("Subcategoria no encontrada"));
            newProduct.setSubcategoria(subcategoria);
        }
        newProduct.setUnidadMedida(unidadMedida);
        if (request.getIdMarca() != null) {
            Marca marca = marcaRepository.findById(request.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
            newProduct.setMarca(marca);
        }
        newProduct.setPrecioCompra(request.getPrecioCompra());
        newProduct.setStockMinimo(request.getStockMinimo());
        newProduct.setContenido(request.getContenido());
        newProduct.setActivo(request.getActivo() != null ? request.getActivo() : true);
        Producto guardado = productRepository.save(newProduct);

        ProductoProveedor vinculo = ProductoProveedor.builder()
                .producto(guardado)
                .proveedor(proveedor)
                .activo(true)
                .build();
        productoProveedorRepository.save(vinculo);
    }

    public ProductResponse patchProductById(Long id, PatchRequest request) {
        Producto producto = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if (request.getPrecio() != null) {
            producto.setPrecio(request.getPrecio());
        }
        if (request.getNombre() != null) {
            producto.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            producto.setDescripcion(request.getDescripcion());
        }
        if (request.getCodigoBarra() != null) {
            producto.setCodigoBarra(request.getCodigoBarra());
        }
        if (request.getIdSubcategoria() != null) {
            SubcategoriaProducto sub = subcategoryRepository.findById(request.getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrada"));
            producto.setSubcategoria(sub);
        }
        if (request.getIdUnidadMedida() != null) {
            UnidadMedida um = unidadMedidaRepository.findById(request.getIdUnidadMedida())
                .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada"));
            producto.setUnidadMedida(um);
        }
        if (request.getStockActual() != null) {
            producto.setStockActual(request.getStockActual());
        }
        if (request.getProductoPesable() != null) {
            producto.setProductoPesable(request.getProductoPesable());
        }
        if (request.getPrecioPorKg() != null) {
            producto.setPrecioPorKg(request.getPrecioPorKg());
        }
        if (request.getIdMarca() != null) {
            Marca marca = marcaRepository.findById(request.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
            producto.setMarca(marca);
        }
        if (request.getPrecioCompra() != null) {
            producto.setPrecioCompra(request.getPrecioCompra());
        }
        if (request.getStockMinimo() != null) {
            producto.setStockMinimo(request.getStockMinimo());
        }
        if (request.getContenido() != null) {
            producto.setContenido(request.getContenido());
        }
        if (request.getActivo() != null) {
            producto.setActivo(request.getActivo());
        }
        return mapper.toResponse(productRepository.save(producto));
    }

    public void deleteProductById(Long id ){
        productRepository.deleteById(id);
    }
}
