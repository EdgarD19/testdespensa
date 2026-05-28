package com.proyecto2.DespensaProyect.model.detailResponse;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClientResponse {

    private Long id;
    private String name;
    private String lastName;
    private String tipoCliente;
    private String razonSocial;
    private String ruc;
    private String descripcionEmpresa;
    private String contactoNombre;
    private String contactoCelular;
    private String documentNumber;
    private String documentType;
    private String phone;
    private String celular;
    private String email;
    private String direccion;
    private String city;
    private Date dateBirth;
    private String nationality;
    private String gender;
    private Boolean activo;
    private String observaciones;
}
