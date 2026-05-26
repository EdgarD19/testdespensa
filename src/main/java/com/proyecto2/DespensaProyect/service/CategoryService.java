package com.proyecto2.DespensaProyect.service;

import com.proyecto2.DespensaProyect.domain.entity.CategoriaProducto;
import com.proyecto2.DespensaProyect.model.request.CategoryRequest;
import com.proyecto2.DespensaProyect.model.response.CategoryResponse;
import com.proyecto2.DespensaProyect.repository.CategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CategoryService {
    private static final Logger LOGGER = Logger.getLogger(CategoryService.class.getName());

    @Autowired
    private CategoriaProductoRepository repository;

//    public CategoryResponse getCategory(String sortBy, String sortDir, Integer page, Integer pageSize) {
//        Sort sort = (sortBy != null && !sortBy.isEmpty()) ? Sort.by(Sort.Direction.fromString(sortDir), sortBy) : Sort.unsorted();
//        Pageable pageable = PageRequest.of(page, pageSize, sort);
//        Specification<CategoriaProducto> specification = Specification.where(CategorySpecification.hasSearch(search));
//
//
//
//
//
//            repository.findAll();
//
//
////        Sort sort = (sortBy != null && !sortBy.isEmpty()) ? Sort.by(Sort.Direction.fromString(sortDir), sortBy) : Sort.unsorted();
////        Pageable pageable = PageRequest.of(page, pageSize, sort);
////
////        Specification<Cliente> specification = Specification.where(ClientSpecification.hasSearch(search));
////        Page<Cliente> clientePage = repository.findAll(specification, pageable);
////        Page<ClientResponse> responsePage = clientePage.map(clientMapper::toResponse);
////        return new ClientsResponse(responsePage);
//
//    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> listCategories() {
        return repository.findAll().stream()
                .map(c -> CategoryResponse.builder()
                        .id(c.getIdCategoria())
                        .nombre(c.getNombre())
                        .build())
                .toList();
    }

    public void createCategory(CategoryRequest request) {
        CategoriaProducto newCategoria = request.toEntity();
        newCategoria.setNombre(request.getName());
        repository.save(newCategoria);
    }

}
