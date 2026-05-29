package com.proyecto2.DespensaProyect.mapper;

import com.proyecto2.DespensaProyect.domain.entity.Proveedor;
import com.proyecto2.DespensaProyect.domain.entity.Rubro;
import com.proyecto2.DespensaProyect.model.detailResponse.ProveedorResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProveedorMapper {

    public ProveedorResponse toResponse(Proveedor p) {
        if (p == null) return null;

        List<Long> rubroIds = Collections.emptyList();
        List<String> rubroNombres = Collections.emptyList();
        if (p.getRubros() != null) {
            rubroIds = p.getRubros().stream().map(Rubro::getIdRubro).toList();
            rubroNombres = p.getRubros().stream().map(Rubro::getNombre).toList();
        }

        return ProveedorResponse.builder()
                .id(p.getIdProveedor())
                .nombre(p.getNombre())
                .tipoPersona(p.getTipoPersona())
                .apellido(p.getApellido())
                .tipoDocumento(p.getTipoDocumento())
                .numeroDocumento(p.getNumeroDocumento())
                .descripcionNegocio(p.getDescripcionNegocio())
                .personaContacto(p.getPersonaContacto())
                .pais(p.getPais() != null ? p.getPais().getNombre() : null)
                .idPais(p.getPais() != null ? p.getPais().getIdPais() : null)
                .ciudad(p.getCiudad() != null ? p.getCiudad().getNombre() : null)
                .idCiudad(p.getCiudad() != null ? p.getCiudad().getIdCiudad() : null)
                .direccion(p.getDireccion())
                .fechaNacimiento(p.getFechaNacimiento())
                .nacionalidad(p.getNacionalidad() != null ? p.getNacionalidad().getNombre() : null)
                .idNacionalidad(p.getNacionalidad() != null ? p.getNacionalidad().getIdPais() : null)
                .telefono(p.getTelefono())
                .celular(p.getCelular())
                .formaPago(p.getFormaPago())
                .banco(p.getBanco())
                .numeroCuenta(p.getNumeroCuenta())
                .documentoTransferencia(p.getDocumentoTransferencia())
                .nombreRazonSocial(p.getNombreRazonSocial())
                .alias(p.getAlias())
                .activo(p.getActivo())
                .rubroIds(rubroIds)
                .rubroNombres(rubroNombres)
                .build();
    }
}
