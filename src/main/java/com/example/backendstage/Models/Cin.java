package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Candidat candidat;
}
