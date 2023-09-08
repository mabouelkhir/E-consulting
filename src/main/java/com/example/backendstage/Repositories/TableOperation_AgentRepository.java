package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.TableOperation_Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableOperation_AgentRepository  extends JpaRepository<TableOperation_Agent,Long> {
    TableOperation_Agent findTableOperation_AgentByCandidat(Candidat candidat);
}
