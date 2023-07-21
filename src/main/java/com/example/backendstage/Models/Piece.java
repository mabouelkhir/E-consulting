package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pieces")
@Data
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private EPiece nom_piece;


    public Piece() {
    }

    public Piece(EPiece nom_piece) {
        this.nom_piece = nom_piece;
    }
}
