package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.*;
import com.proyecto2.DespensaProyect.mapper.ProveedorMapper;
import com.proyecto2.DespensaProyect.model.detailResponse.ProveedorResponse;
import com.proyecto2.DespensaProyect.model.request.ProveedorRequest;
import com.proyecto2.DespensaProyect.model.response.ProveedoresResponse;
import com.proyecto2.DespensaProyect.repository.*;
import com.proyecto2.DespensaProyect.repository.specification.ProveedorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private RubroRepository rubroRepository;

    @Autowired
    private ProveedorMapper mapper;

    @Transactional
    public void createProveedor(ProveedorRequest request) {
        if (request.getNumeroDocumento() != null && !request.getNumeroDocumento().isBlank()) {
            if (repository.existsByNumeroDocumento(request.getNumeroDocumento().trim())) {
                throw new RuntimeException("Ya existe un proveedor con ese RUC/documento");
            }
        }

        Proveedor proveedor = request.toEntity();

        if (request.getIdPais() != null) {
            proveedor.setPais(paisRepository.findById(request.getIdPais())
                    .orElseThrow(() -> new RuntimeException("País no encontrado")));
        }
        if (request.getIdCiudad() != null) {
            proveedor.setCiudad(ciudadRepository.findById(request.getIdCiudad())
                    .orElseThrow(() -> new RuntimeException("Ciudad no encontrada")));
        }
        if (request.getIdNacionalidad() != null) {
            proveedor.setNacionalidad(paisRepository.findById(request.getIdNacionalidad())
                    .orElseThrow(() -> new RuntimeException("Nacionalidad no encontrada")));
        }
        if (request.getRubroIds() != null) {
            List<Rubro> rubros = rubroRepository.findAllById(request.getRubroIds());
            proveedor.setRubros(rubros);
        }
        if (proveedor.getActivo() == null) {
            proveedor.setActivo(true);
        }

        repository.save(proveedor);
    }

    @Transactional
    public ProveedorResponse updateProveedor(Long id, ProveedorRequest request) {
        Proveedor existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));

        if (request.getNumeroDocumento() != null && !request.getNumeroDocumento().isBlank()) {
            if (repository.existsByNumeroDocumentoAndIdProveedorNot(request.getNumeroDocumento().trim(), id)) {
                throw new RuntimeException("Ya existe un proveedor con ese RUC/documento");
            }
        }

        existing.setNombre(request.getNombre());
        existing.setTipoPersona(request.getTipoPersona());
        existing.setApellido(request.getApellido());
        existing.setTipoDocumento(request.getTipoDocumento());
        existing.setNumeroDocumento(request.getNumeroDocumento());
        existing.setDescripcionNegocio(request.getDescripcionNegocio());
        existing.setPersonaContacto(request.getPersonaContacto());
        existing.setDireccion(request.getDireccion());
        existing.setFechaNacimiento(request.getFechaNacimiento());
        existing.setTelefono(request.getTelefono());
        existing.setCelular(request.getCelular());
        existing.setFormaPago(request.getFormaPago());
        existing.setBanco(request.getBanco());
        existing.setNumeroCuenta(request.getNumeroCuenta());
        existing.setDocumentoTransferencia(request.getDocumentoTransferencia());
        existing.setNombreRazonSocial(request.getNombreRazonSocial());
        existing.setAlias(request.getAlias());

        if (request.getActivo() != null) {
            existing.setActivo(request.getActivo());
        }

        if (request.getIdPais() != null) {
            existing.setPais(paisRepository.findById(request.getIdPais())
                    .orElseThrow(() -> new RuntimeException("País no encontrado")));
        } else {
            existing.setPais(null);
        }

        if (request.getIdCiudad() != null) {
            existing.setCiudad(ciudadRepository.findById(request.getIdCiudad())
                    .orElseThrow(() -> new RuntimeException("Ciudad no encontrada")));
        } else {
            existing.setCiudad(null);
        }

        if (request.getIdNacionalidad() != null) {
            existing.setNacionalidad(paisRepository.findById(request.getIdNacionalidad())
                    .orElseThrow(() -> new RuntimeException("Nacionalidad no encontrada")));
        } else {
            existing.setNacionalidad(null);
        }

        if (request.getRubroIds() != null) {
            List<Rubro> rubros = rubroRepository.findAllById(request.getRubroIds());
            existing.setRubros(rubros);
        } else {
            existing.setRubros(null);
        }

        return mapper.toResponse(repository.save(existing));
    }

    @Transactional
    public ProveedorResponse toggleActivo(Long id) {
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
        proveedor.setActivo(!Boolean.TRUE.equals(proveedor.getActivo()));
        return mapper.toResponse(repository.save(proveedor));
    }

    @Transactional(readOnly = true)
    public ProveedoresResponse getProveedores(String search, Integer page, Integer pageSize, String sortBy, String sortDir) {
        String property = toEntitySortProperty(sortBy);
        Sort sort = (property != null && !property.isEmpty())
                ? Sort.by(Sort.Direction.fromString(sortDir), property)
                : Sort.unsorted();
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Specification<Proveedor> specification = Specification.where(ProveedorSpecification.hasSearch(search));
        Page<Proveedor> proveedorPage = repository.findAll(specification, pageable);
        Page<ProveedorResponse> responsePage = proveedorPage.map(mapper::toResponse);
        return new ProveedoresResponse(responsePage);
    }

    @Transactional(readOnly = true)
    public ProveedorResponse getProveedorById(Long id) {
        return repository.findById(id).map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ese id: " + id));
    }

    private static String toEntitySortProperty(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) return null;
        if ("id".equalsIgnoreCase(sortBy.trim())) return "idProveedor";
        return sortBy.trim();
    }
}
