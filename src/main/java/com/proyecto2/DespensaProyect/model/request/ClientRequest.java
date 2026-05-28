package com.proyecto2.DespensaProyect.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest implements Serializable {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("tipoCliente")
    private String tipoCliente;

    @JsonProperty("razonSocial")
    private String razonSocial;

    @JsonProperty("ruc")
    private String ruc;

    @JsonProperty("descripcionEmpresa")
    private String descripcionEmpresa;

    @JsonProperty("contactoNombre")
    private String contactoNombre;

    @JsonProperty("contactoCelular")
    private String contactoCelular;

    @JsonProperty("birthDate")
    private Date birthDate;

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("id_ciudad")
    private Long idCity;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("email")
    private String email;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("documentNumber")
    private String documentNumber;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("nationality_id_pais")
    private Long nationality;

    @JsonProperty("activo")
    private Boolean activo;

    @JsonProperty("observaciones")
    private String observaciones;

    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setFirstName(this.getFirstName());
        cliente.setLastName(this.getLastName());
        cliente.setTipoCliente(this.getTipoCliente());
        cliente.setRazonSocial(this.getRazonSocial());
        cliente.setRuc(this.getRuc());
        cliente.setDescripcionEmpresa(this.getDescripcionEmpresa());
        cliente.setContactoNombre(this.getContactoNombre());
        cliente.setContactoCelular(this.getContactoCelular());
        cliente.setDateBirth(this.getBirthDate());
        cliente.setPhone(this.getPhoneNumber());
        cliente.setCelular(this.getCelular());
        cliente.setEmail(this.getEmail());
        cliente.setDireccion(this.getDireccion());
        cliente.setDocumentNumber(this.getDocumentNumber());
        cliente.setGender(this.getGender());
        cliente.setDocumentType(this.getDocumentType());
        if (this.getActivo() != null) {
            cliente.setActivo(this.getActivo());
        }
        cliente.setObservaciones(this.getObservaciones());
        return cliente;
    }
}
