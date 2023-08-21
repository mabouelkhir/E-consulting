package com.example.backendstage.Repositories;

import com.example.backendstage.Models.SubFonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubFonctionRepository extends JpaRepository<SubFonction,Long> {
}
