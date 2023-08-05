package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Long> {
    Candidat findByEmail(String email);

}
