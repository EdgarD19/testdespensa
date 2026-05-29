package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.CategoriaProducto;
import com.proyecto2.DespensaProyect.domain.entity.Ciudad;
import com.proyecto2.DespensaProyect.domain.entity.Marca;
import com.proyecto2.DespensaProyect.domain.entity.Pais;
import com.proyecto2.DespensaProyect.domain.entity.SubcategoriaProducto;
import com.proyecto2.DespensaProyect.repository.CategoriaProductoRepository;
import com.proyecto2.DespensaProyect.repository.CiudadRepository;
import com.proyecto2.DespensaProyect.repository.MarcaRepository;
import com.proyecto2.DespensaProyect.repository.PaisRepository;
import com.proyecto2.DespensaProyect.repository.SubcategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaestrosABMService {

    @Autowired
    private CategoriaProductoRepository categoriaRepository;

    @Autowired
    private SubcategoriaProductoRepository subcategoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    // === CATEGORIAS ===

    @Transactional
    public CategoriaProducto crearCategoria(String nombre) {
        CategoriaProducto c = new CategoriaProducto();
        c.setNombre(nombre);
        return categoriaRepository.save(c);
    }

    @Transactional
    public CategoriaProducto actualizarCategoria(Long id, String nombre) {
        CategoriaProducto c = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + id));
        c.setNombre(nombre);
        return categoriaRepository.save(c);
    }

    @Transactional
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id))
            throw new RuntimeException("Categoria no encontrada: " + id);
        categoriaRepository.deleteById(id);
    }

    // === SUBCATEGORIAS ===

    @Transactional
    public SubcategoriaProducto crearSubcategoria(Long idCategoria, String nombre) {
        CategoriaProducto cat = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada: " + idCategoria));
        SubcategoriaProducto s = new SubcategoriaProducto();
        s.setNombre(nombre);
        s.setCategoria(cat);
        return subcategoriaRepository.save(s);
    }

    @Transactional
    public SubcategoriaProducto actualizarSubcategoria(Long id, String nombre) {
        SubcategoriaProducto s = subcategoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrada: " + id));
        s.setNombre(nombre);
        return subcategoriaRepository.save(s);
    }

    @Transactional
    public void eliminarSubcategoria(Long id) {
        if (!subcategoriaRepository.existsById(id))
            throw new RuntimeException("Subcategoria no encontrada: " + id);
        subcategoriaRepository.deleteById(id);
    }

    // === MARCAS ===

    @Transactional
    public Marca crearMarca(String nombre) {
        Marca m = new Marca();
        m.setNombre(nombre);
        return marcaRepository.save(m);
    }

    @Transactional
    public Marca actualizarMarca(Long id, String nombre) {
        Marca m = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada: " + id));
        m.setNombre(nombre);
        return marcaRepository.save(m);
    }

    @Transactional
    public void eliminarMarca(Long id) {
        if (!marcaRepository.existsById(id))
            throw new RuntimeException("Marca no encontrada: " + id);
        marcaRepository.deleteById(id);
    }

    // === PAISES ===

    @Transactional
    public Pais crearPais(String nombre) {
        Pais p = new Pais();
        p.setNombre(nombre);
        return paisRepository.save(p);
    }

    @Transactional
    public Pais actualizarPais(Long id, String nombre) {
        Pais p = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pais no encontrado: " + id));
        p.setNombre(nombre);
        return paisRepository.save(p);
    }

    @Transactional
    public void eliminarPais(Long id) {
        if (!paisRepository.existsById(id))
            throw new RuntimeException("Pais no encontrado: " + id);
        paisRepository.deleteById(id);
    }

    // === CIUDADES ===

    @Transactional
    public Ciudad crearCiudad(Long idPais, String nombre) {
        Pais p = paisRepository.findById(idPais)
                .orElseThrow(() -> new RuntimeException("Pais no encontrado: " + idPais));
        Ciudad c = new Ciudad();
        c.setNombre(nombre);
        c.setPais(p);
        return ciudadRepository.save(c);
    }

    @Transactional
    public Ciudad actualizarCiudad(Long id, String nombre) {
        Ciudad c = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada: " + id));
        c.setNombre(nombre);
        return ciudadRepository.save(c);
    }

    @Transactional
    public void eliminarCiudad(Long id) {
        if (!ciudadRepository.existsById(id))
            throw new RuntimeException("Ciudad no encontrada: " + id);
        ciudadRepository.deleteById(id);
    }
}
