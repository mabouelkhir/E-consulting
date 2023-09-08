package com.example.backendstage.Requests;

import com.example.backendstage.Models.Candidat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EmployeurRequest {

    private String num_tel;

    private String adresse;

    private String email;

    private String codeEmp;

    private String ref_contrat;

    private String city;
    private String country;
}
