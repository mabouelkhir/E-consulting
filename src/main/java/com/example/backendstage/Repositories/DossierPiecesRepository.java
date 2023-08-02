package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Dossier;
import com.example.backendstage.Models.DossierPieces;
import com.example.backendstage.Models.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierPiecesRepository extends JpaRepository<DossierPieces,Long> {
    DossierPieces findByDossierAndPiece(Dossier dossier,Piece piece);
}
