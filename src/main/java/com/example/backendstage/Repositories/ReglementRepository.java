package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReglementRepository extends JpaRepository<Reglement,Long> {

}
