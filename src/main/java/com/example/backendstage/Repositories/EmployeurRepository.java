package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Employeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EmployeurRepository extends JpaRepository<Employeur,Long> {
    Employeur findByEmail(String email);

    }




