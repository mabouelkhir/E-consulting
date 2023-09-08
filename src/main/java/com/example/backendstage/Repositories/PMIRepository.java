package com.example.backendstage.Repositories;

import com.example.backendstage.Models.PMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PMIRepository extends JpaRepository<PMI,Long> {
}
