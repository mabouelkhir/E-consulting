package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByEmail(String email);

}
