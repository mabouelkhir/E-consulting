package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Permis_Categorie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermisCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Permis permis;

    private EPermis categorie;

    private Date date_delivrance;


}
