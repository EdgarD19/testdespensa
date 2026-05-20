package com.proyecto2.DespensaProyect.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto2.DespensaProyect.domain.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest implements Serializable {

    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("birthDate")
    private Date birthDate;

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("id_ciudad")
    private Long idCity;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("documentNumber")
    private String documentNumber;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("nationality_id_pais")
    private Long nationality ;


    public Cliente toEntity(){
        Cliente cliente = new Cliente();
        cliente.setFirstName(this.getFirstName());
        cliente.setLastName(this.getLastName());
        cliente.setDateBirth(this.getBirthDate());
        cliente.setPhone(this.getPhoneNumber());
        cliente.setDocumentNumber(this.getDocumentNumber());
        cliente.setGender(this.getGender());
        cliente.setDocumentType(this.getDocumentType());
        return cliente;
    }

}
