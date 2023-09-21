package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employeurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Employeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String nom;
    @Column
    private String prenom;

    @Column
    private String num_tel;

    @Column
    private String adresse;
    @Column
    private String email;
    @Column(name = "code_emp")
    private String codeEmp;
    @Column
    private String ref_contrat;
    @Column
    private Date createdAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "employeurs" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Candidat> candidatSet ;

    @OneToMany
    @JoinColumn(name = "prestation_id")
    private List<Prestation> prestation;
    @Column
    private String city;

    @Column
    private String country;

    @Column(length = 500000)
    @Lob
    private byte[] image;


    public void setPrestations(Prestation prestation) {
    }

    public List<Prestation> getPrestations() {

        return prestation;
    }
}
