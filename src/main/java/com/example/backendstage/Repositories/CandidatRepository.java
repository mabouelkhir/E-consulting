package com.example.backendstage.Repositories;

import com.example.backendstage.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Long> {
    Candidat findByEmail(String email);

    List<Candidat> findCandidatsByStatus(EStatus status);

    List<Candidat> findByFonctions(Fonction function);
    Optional<Candidat> findById(Long candidatId);

    List<Candidat> findByEmployeur(Employeur employeur);

    List<Candidat> findByAgent(Agent agent);

    long countByStatus(EStatus status);
    List<Candidat> findByGroupe(String group);


    List<Candidat> findByEmployeurId(Long employeurID);


    @Query("SELECT c FROM Candidat c JOIN c.employeur e WHERE e.codeEmp = :codeEmp")
    List<Candidat> findByEmployeurCodeEmp(String codeEmp);




}

