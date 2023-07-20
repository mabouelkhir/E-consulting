package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DossierRepository extends JpaRepository<Dossier,Long> {
}
