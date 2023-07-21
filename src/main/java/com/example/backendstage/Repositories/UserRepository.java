package com.example.backendstage.Repositories;

import com.example.backendstage.Models.ERole;
import com.example.backendstage.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);

    Boolean existsByEmail(String email);

    User findByPasswordToken(String verificationToken);

    List<User> findByFirstNameIgnoreCase(String firstName);

    List<User> findByLastNameIgnoreCase( String lastName);

    long count();

    long countByRolesIs(ERole role);

    long countByRolesContaining(ERole role);
}
