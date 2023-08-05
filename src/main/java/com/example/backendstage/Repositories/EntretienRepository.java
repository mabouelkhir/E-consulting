package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EntretienRepository extends JpaRepository<Entretien,Long> {

}
