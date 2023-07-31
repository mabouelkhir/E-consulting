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

    // Méthode pour mettre à jour les informations d'un agent
    public Agent updateAgent(Long id, Agent updatedAgent) {
        // Check if the agent with the given ID exists in the database
        Agent existingAgent = agentRepository.findById(id).orElse(null);
        if (existingAgent == null) {
            // Handle the case when the agent with the given ID does not exist
            return null;
        }

        // Update the properties of the existing agent with the properties of the updatedAgent
        existingAgent.setNom(updatedAgent.getNom());
        existingAgent.setPrenom(updatedAgent.getPrenom());
        existingAgent.setSexe(updatedAgent.getSexe());
        existingAgent.setEmail(updatedAgent.getEmail());
        existingAgent.setTel(updatedAgent.getTel());
        existingAgent.setAdresse(updatedAgent.getAdresse());

        // Save the updated agent to the database
        return agentRepository.save(existingAgent);
    }
        // Méthode pour supprimer un agent par son ID
        public void deleteAgentById(Long id) {
            agentRepository.deleteById(id);
        }
}
