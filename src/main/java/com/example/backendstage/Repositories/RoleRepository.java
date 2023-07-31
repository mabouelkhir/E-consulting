package com.example.backendstage.Repositories;

import com.example.backendstage.Models.ERole;
import com.example.backendstage.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);

}
