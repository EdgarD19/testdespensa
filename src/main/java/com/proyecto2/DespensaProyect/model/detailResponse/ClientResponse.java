package com.proyecto2.DespensaProyect.model.detailResponse;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClientResponse {

    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String phone;
    private String city;
    private Date dateBirth;
    private String nationality;
    private String gender;
    private List<String> contacts;

}
