package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisRepository extends JpaRepository<Permis,Long> {
}
