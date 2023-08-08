package com.example.backendstage.Repositories;

import com.example.backendstage.Models.ERole;
import com.example.backendstage.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);



    List<User> findByFirstNameIgnoreCase(String firstName);

    List<User> findByLastNameIgnoreCase( String lastName);

    long count();




    User findByEmail(String email);

}
