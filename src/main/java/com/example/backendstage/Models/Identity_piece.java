package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "identity_pieces")
@Data
public class Identity_piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
@JsonIgnore
    @OneToMany(mappedBy = "identityPiece")
    private List<Candidat_IdentityPieces> candidatIdentityPieces;
}
