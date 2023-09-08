package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestationRepository extends JpaRepository<Prestation, Long> {
}
