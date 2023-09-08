package com.example.backendstage.Services;

import com.example.backendstage.Models.Operation_Agent;
import com.example.backendstage.Repositories.Operation_AgentRepository;
import com.example.backendstage.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Operation_AgentService {
    private final Operation_AgentRepository operation_agentRepository;

    @Autowired
    public Operation_AgentService(Operation_AgentRepository operation_agentRepository) {
        this.operation_agentRepository = operation_agentRepository;
    }

    public Operation_Agent saveOperation_Agent(Operation_Agent operation_agent) {
        return operation_agentRepository.save(operation_agent);
    }

    public List<Operation_Agent> getAllOperation_Agent() {

        return operation_agentRepository.findAll();
    }

    public void deleteOperation_AgentById(Long id) {

            operation_agentRepository.deleteById(id);
        }

}
