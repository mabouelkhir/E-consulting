package com.example.backendstage.Repositories;

import com.example.backendstage.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Long> {
    Candidat findByEmail(String email);

    List<Candidat> findCandidatsByStatus(EStatus status);

    List<Candidat> findByFonctions(Fonction function);

    List<Candidat> findByEmployeur(Employeur employeur);

    List<Candidat> findByAgent(Agent agent);

    long countByStatus(EStatus status);
    List<Candidat> findByGroupe(String group);


}
