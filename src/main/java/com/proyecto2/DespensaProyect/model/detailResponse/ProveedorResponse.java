package com.proyecto2.DespensaProyect.model.detailResponse;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProveedorResponse {

    private Long id;
    private String nombre;
    private String tipoPersona;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String descripcionNegocio;
    private String personaContacto;
    private String pais;
    private Long idPais;
    private String ciudad;
    private Long idCiudad;
    private String direccion;
    private Date fechaNacimiento;
    private String nacionalidad;
    private Long idNacionalidad;
    private String telefono;
    private String celular;
    private String formaPago;
    private String banco;
    private String numeroCuenta;
    private String documentoTransferencia;
    private String nombreRazonSocial;
    private String alias;
    private Boolean activo;
    private List<Long> rubroIds;
    private List<String> rubroNombres;
}
