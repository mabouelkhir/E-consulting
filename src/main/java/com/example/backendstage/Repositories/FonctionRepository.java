package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FonctionRepository extends JpaRepository<Fonction,Long> {
}
