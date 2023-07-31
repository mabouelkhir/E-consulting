package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    //pour enregistrer un nouvel agent
    @PostMapping("/Save")
    public Agent saveAgent(@RequestBody Agent agent) {
        return agentService.saveAgent(agent);
    }

    //pour récupérer tous les agents
    @GetMapping("/All")
    public List<Agent> getAllAgents() {
        List<Agent> agents = agentService.getAllAgents();
        return agents;
    }

    //pour supprimer un agent par son ID
    @DeleteMapping("/{id}")
    public void  deleteAgentById(@PathVariable Long id) {
        agentService.deleteAgentById(id);
    }








}
