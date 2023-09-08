package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
}
