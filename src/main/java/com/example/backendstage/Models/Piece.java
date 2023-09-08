package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pieces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom_piece;
    @JsonIgnore
    @OneToMany(mappedBy = "piece")
    private List<DossierPieces> dossierPieces;



}
