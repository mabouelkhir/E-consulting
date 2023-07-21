package com.example.backendstage.Services;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    public class AgentService {

        private final AgentRepository agentRepository;

        @Autowired
        public AgentService(AgentRepository agentRepository) {
            this.agentRepository = agentRepository;
        }

        // Méthode pour enregistrer un nouvel agent dans la base de données
        public Agent saveAgent(Agent agent) {
            return agentRepository.save(agent);
        }

        // Méthode pour récupérer un agent par son ID
        public Agent getAgentById(Long id) {
            return agentRepository.findById(id).orElse(null);
        }

        // Méthode pour récupérer tous les agents de la base de données
        public List<Agent> getAllAgents() {
            return agentRepository.findAll();
        }

        // Méthode pour supprimer un agent par son ID
        public void deleteAgentById(Long id) {
            agentRepository.deleteById(id);
        }
}
