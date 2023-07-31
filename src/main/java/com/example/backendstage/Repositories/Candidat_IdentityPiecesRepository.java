package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Candidat_IdentityPieces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Candidat_IdentityPiecesRepository extends JpaRepository<Candidat_IdentityPieces,Long> {
}
