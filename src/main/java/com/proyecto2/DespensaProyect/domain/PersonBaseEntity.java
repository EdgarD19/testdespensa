package com.proyecto2.DespensaProyect.domain;


import com.proyecto2.DespensaProyect.domain.entity.Pais;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class PersonBaseEntity {

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_birth")
    private Date dateBirth;

    @ManyToOne
    @JoinColumn(name = "nationality_id_pais")
    private Pais nationality;

    private String gender;

}
