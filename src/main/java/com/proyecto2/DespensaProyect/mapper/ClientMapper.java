package com.proyecto2.DespensaProyect.mapper;


import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import com.proyecto2.DespensaProyect.domain.entity.ContactoCliente;
import com.proyecto2.DespensaProyect.model.detailResponse.ClientResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper {

     public ClientResponse toResponse(Cliente cliente){
         if(cliente == null){
             return null;
         }
         return ClientResponse.builder()
                 .id(cliente.getIdCliente())
                 .name(cliente.getFirstName())
                 .lastName(cliente.getLastName())
                 .documentNumber(cliente.getDocumentNumber())
                 .phone(cliente.getPhone())
                 .city(cliente.getCiudad() != null ? cliente.getCiudad().getNombre() : null)
                 .dateBirth(cliente.getDateBirth())
                 .nationality(cliente.getNationality() != null ? cliente.getNationality().getNombre() : null)
                 .gender(cliente.getGender())
                 .contacts(cliente.getContactos() == null ? List.of() : cliente.getContactos()
                         .stream()
                         .map(ContactoCliente::getNombreContacto)
                         .toList())
                 .build();
     }

}
