package com.proyecto2.DespensaProyect.service;


import com.proyecto2.DespensaProyect.domain.entity.Ciudad;
import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import com.proyecto2.DespensaProyect.domain.entity.Pais;
import com.proyecto2.DespensaProyect.mapper.ClientMapper;
import com.proyecto2.DespensaProyect.model.detailResponse.ClientResponse;
import com.proyecto2.DespensaProyect.model.request.ClientRequest;
import com.proyecto2.DespensaProyect.model.response.ClientsResponse;
import com.proyecto2.DespensaProyect.repository.CiudadRepository;
import com.proyecto2.DespensaProyect.repository.ClienteRepository;
import com.proyecto2.DespensaProyect.repository.PaisRepository;
import com.proyecto2.DespensaProyect.repository.specification.ClientSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class ClientService {
    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private PaisRepository paisRepository;


    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void createClient(ClientRequest request){
        Ciudad ciudad = ciudadRepository.findById(request.getIdCity()).orElseThrow(() -> new RuntimeException("Ciudad no emncontrada"));
        Long nationalityId =
                request.getNationality() != null ? request.getNationality() : 1L;
        Pais pais = paisRepository.findById(nationalityId)
                .orElseThrow(() -> new RuntimeException("Pais no encontrado"));
        Cliente newClient = request.toEntity();
        newClient.setFirstName(request.getFirstName());
        newClient.setLastName(request.getLastName());
        newClient.setDateBirth(request.getBirthDate());
        newClient.setCiudad(ciudad);
        newClient.setPhone(request.getPhoneNumber());
        newClient.setDocumentNumber(request.getDocumentNumber());
        newClient.setGender(request.getGender());
        newClient.setNationality(pais);
        newClient.setDocumentType(request.getDocumentType());
        clienteRepository.save(newClient);
    }

    @Transactional
    public ClientResponse updateClient(Long id, ClientRequest request) {
        Cliente existing = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        Ciudad ciudad = ciudadRepository.findById(request.getIdCity())
                .orElseThrow(() -> new RuntimeException("Ciudad no emncontrada"));
        Long nationalityId =
                request.getNationality() != null ? request.getNationality() : 1L;
        Pais pais = paisRepository.findById(nationalityId)
                .orElseThrow(() -> new RuntimeException("Pais no encontrado"));
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setDateBirth(request.getBirthDate());
        existing.setCiudad(ciudad);
        existing.setPhone(request.getPhoneNumber());
        existing.setDocumentNumber(request.getDocumentNumber());
        existing.setGender(request.getGender());
        existing.setNationality(pais);
        existing.setDocumentType(request.getDocumentType());
        return clientMapper.toResponse(clienteRepository.save(existing));
    }

    @Transactional
    public void deleteClient(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        if (clienteRepository.countPedidosByCliente(id) > 0) {
            throw new RuntimeException("No se puede eliminar el cliente: tiene pedidos asociados");
        }
        if (clienteRepository.countFacturasByCliente(id) > 0) {
            throw new RuntimeException("No se puede eliminar el cliente: tiene facturas asociadas");
        }
        clienteRepository.deleteById(id);
    }



    /**
     * Alinea nombres habituales del API con propiedades JPA de la entidad Cliente
     * (p. ej. el front envía "id" pero el campo persistido es idCliente).
     */
    private static String toEntitySortProperty(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) {
            return null;
        }
        if ("id".equalsIgnoreCase(sortBy.trim())) {
            return "idCliente";
        }
        return sortBy.trim();
    }

    @Transactional(readOnly = true)
    public ClientsResponse getClients(String search, Integer page, Integer pageSize, String sortBy, String sortDir){

        String property = toEntitySortProperty(sortBy);
        Sort sort = (property != null && !property.isEmpty())
                ? Sort.by(Sort.Direction.fromString(sortDir), property)
                : Sort.unsorted();
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Specification<Cliente> specification = Specification.where(ClientSpecification.hasSearch(search));
        Page<Cliente> clientePage = repository.findAll(specification, pageable);
        Page<ClientResponse> responsePage = clientePage.map(clientMapper::toResponse);
        return new ClientsResponse(responsePage);
    }

    @Transactional(readOnly = true)
    public ClientResponse getClientById(Long id){
        return repository.findById(id).map(clientMapper::toResponse).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ese id : " + id));
    }

}
