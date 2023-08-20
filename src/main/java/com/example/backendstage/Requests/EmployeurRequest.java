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
    private String nom;
    private String prenom;
    private String num_tel;

    private String adresse;

    private String email;

    private String code_emp;

    private String city;
}
