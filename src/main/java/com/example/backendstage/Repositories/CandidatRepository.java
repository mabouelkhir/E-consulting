package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Long> {
    Candidat findByEmail(String email);
    Candidat findCandidatByCin(String cin);
    Optional<Candidat> findById(Long id);

}
