package com.example.backendstage.Repositories;

import com.example.backendstage.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Candidat_IdentityPiecesRepository extends JpaRepository<Candidat_IdentityPieces,Long> {
    Candidat_IdentityPieces findByCandidatAndIdentityPiece(Candidat candidat, Identity_piece identity_piece);

}
