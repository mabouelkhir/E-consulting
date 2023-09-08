package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository



public interface ReglementRepository extends JpaRepository<Reglement,Long> {

    Reglement findReglementByCandidat(Candidat candidat);

}
