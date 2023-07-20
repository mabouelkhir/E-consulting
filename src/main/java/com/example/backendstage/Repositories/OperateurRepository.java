package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Operateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OperateurRepository extends JpaRepository<Operateur,Long> {
}
