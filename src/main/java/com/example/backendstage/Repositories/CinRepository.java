package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Cin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinRepository extends JpaRepository<Cin,Long> {
    Optional<Cin> findByCode(String code);
}
