package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "identity_pieces")
@Data
public class Identity_piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Eidentity_piece type;
    @Column
    private String code;
    @Column
    private LocalDateTime date_validite;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
}
