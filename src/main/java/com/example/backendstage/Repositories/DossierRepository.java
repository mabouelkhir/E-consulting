package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface DossierRepository extends JpaRepository<Dossier,Long> {
    Optional<Dossier> findByNumeroDossier(String numero);


    List<Dossier> findByStatus(String status

    );
}
