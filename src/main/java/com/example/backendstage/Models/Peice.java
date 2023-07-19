package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pieces")
@Data
public class Peice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column
    private EPiece nom_piece;


    public Peice() {
    }

    public Peice(EPiece nom_piece) {
        this.nom_piece = nom_piece;
    }
}
