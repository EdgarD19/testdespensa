package com.proyecto2.DespensaProyect.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorRequest implements Serializable {

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("tipoPersona")
    private String tipoPersona;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

    @JsonProperty("numeroDocumento")
    private String numeroDocumento;

    @JsonProperty("descripcionNegocio")
    private String descripcionNegocio;

    @JsonProperty("personaContacto")
    private String personaContacto;

    @JsonProperty("id_pais")
    private Long idPais;

    @JsonProperty("id_ciudad")
    private Long idCiudad;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("fechaNacimiento")
    private Date fechaNacimiento;

    @JsonProperty("nacionalidad_id_pais")
    private Long idNacionalidad;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("celular")
    private String celular;

    @JsonProperty("formaPago")
    private String formaPago;

    @JsonProperty("banco")
    private String banco;

    @JsonProperty("numeroCuenta")
    private String numeroCuenta;

    @JsonProperty("documentoTransferencia")
    private String documentoTransferencia;

    @JsonProperty("nombreRazonSocial")
    private String nombreRazonSocial;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("activo")
    private Boolean activo;

    @JsonProperty("rubros")
    private List<Long> rubroIds;

    public Proveedor toEntity() {
        Proveedor p = new Proveedor();
        p.setNombre(this.getNombre());
        p.setTipoPersona(this.getTipoPersona());
        p.setApellido(this.getApellido());
        p.setTipoDocumento(this.getTipoDocumento());
        p.setNumeroDocumento(this.getNumeroDocumento());
        p.setDescripcionNegocio(this.getDescripcionNegocio());
        p.setPersonaContacto(this.getPersonaContacto());
        p.setDireccion(this.getDireccion());
        p.setFechaNacimiento(this.getFechaNacimiento());
        p.setTelefono(this.getTelefono());
        p.setCelular(this.getCelular());
        p.setFormaPago(this.getFormaPago());
        p.setBanco(this.getBanco());
        p.setNumeroCuenta(this.getNumeroCuenta());
        p.setDocumentoTransferencia(this.getDocumentoTransferencia());
        p.setNombreRazonSocial(this.getNombreRazonSocial());
        p.setAlias(this.getAlias());
        if (this.getActivo() != null) {
            p.setActivo(this.getActivo());
        }
        return p;
    }
}
