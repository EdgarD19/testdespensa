package com.proyecto2.DespensaProyect.mapper;

import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import com.proyecto2.DespensaProyect.model.detailResponse.ClientResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientResponse toResponse(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClientResponse.builder()
                .id(cliente.getIdCliente())
                .name(cliente.getFirstName())
                .lastName(cliente.getLastName())
                .tipoCliente(cliente.getTipoCliente())
                .razonSocial(cliente.getRazonSocial())
                .ruc(cliente.getRuc())
                .descripcionEmpresa(cliente.getDescripcionEmpresa())
                .contactoNombre(cliente.getContactoNombre())
                .contactoCelular(cliente.getContactoCelular())
                .documentNumber(cliente.getDocumentNumber())
                .documentType(cliente.getDocumentType())
                .phone(cliente.getPhone())
                .celular(cliente.getCelular())
                .email(cliente.getEmail())
                .direccion(cliente.getDireccion())
                .city(cliente.getCiudad() != null ? cliente.getCiudad().getNombre() : null)
                .dateBirth(cliente.getDateBirth())
                .nationality(cliente.getNationality() != null ? cliente.getNationality().getNombre() : null)
                .gender(cliente.getGender())
                .activo(cliente.getActivo())
                .observaciones(cliente.getObservaciones())
                .build();
    }
}
