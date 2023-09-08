package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Operation_Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Operation_AgentRepository extends JpaRepository<Operation_Agent,Long> {
    Optional<Operation_Agent> findById(Long operationagentId);
}
