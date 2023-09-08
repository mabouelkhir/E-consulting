package com.example.backendstage.Services;



import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Operation_Agent;
import com.example.backendstage.Models.TableOperation_Agent;

import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.Operation_AgentRepository;
import com.example.backendstage.Repositories.TableOperation_AgentRepository;
import com.example.backendstage.Requests.TableOperation_AgentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableOperation_AgentService {
    private final TableOperation_AgentRepository tableoperation_agentRepository;
    private final CandidatRepository candidatRepository;
    private final Operation_AgentRepository operation_agentRepository;


    @Autowired
    public TableOperation_AgentService(TableOperation_AgentRepository tableoperation_agentRepository, CandidatRepository candidatRepository, Operation_AgentRepository operation_agentRepository) {
        this.tableoperation_agentRepository = tableoperation_agentRepository;


        this.candidatRepository = candidatRepository;
        this.operation_agentRepository = operation_agentRepository;
    }

    public TableOperation_Agent getTableOperation_AgentByCandidatId(Long candidatID) {
        Candidat candidat = candidatRepository.findById(candidatID).get();
        return tableoperation_agentRepository.findTableOperation_AgentByCandidat(candidat);

    }




    public void deleteTableOperation_AgentById(Long id) {

        tableoperation_agentRepository.deleteById(id);
    }

    public TableOperation_Agent saveTableOperation_Agent(TableOperation_AgentRequest tableOperation_agentRequest, Long candidatId) throws CandidatNotFoundException{
        // Rechercher le candidat par son ID
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new CandidatNotFoundException("Candidat non trouvé avec l'ID : " + candidatId));
        TableOperation_Agent tableOperation_agent = new TableOperation_Agent();


        tableOperation_agent.setCandidat(candidat);
        tableOperation_agent.setOperation_agent(tableOperation_agentRequest.getOperation_agent());
        tableOperation_agent.setMontant(tableOperation_agentRequest.getMontant());
        return tableoperation_agentRepository.save(tableOperation_agent);
    }


    public List<TableOperation_Agent> getAllTableOperation_Agent() {
        return tableoperation_agentRepository.findAll();
    }
}
