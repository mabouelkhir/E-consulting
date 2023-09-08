package com.example.backendstage.Repositories;


import com.example.backendstage.Models.Rendez_vous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Rendez_vousRepository extends JpaRepository<Rendez_vous,Long> {

}
