package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String code;

    @Column
    private Date date_naissance;
    @Column
    private Date date_validite;


    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Candidat candidat;
}
