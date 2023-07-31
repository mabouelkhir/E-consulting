package com.example.backendstage.Repositories;

import com.example.backendstage.Models.DossierPieces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierPiecesRepository extends JpaRepository<DossierPieces,Long> {
}
