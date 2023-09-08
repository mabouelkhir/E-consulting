package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Operation_Agent;
import com.example.backendstage.Models.Role;
import com.example.backendstage.Services.Operation_AgentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Operation_Agent")
public class Operation_AgentController {
    private final Operation_AgentService operation_agentService;


    public Operation_AgentController(Operation_AgentService operation_agentService) {
        this.operation_agentService = operation_agentService;
    }

    @PostMapping("/Save")
    public Operation_Agent saveOperation_Agent(@RequestBody Operation_Agent operation_agent) {
        return operation_agentService.saveOperation_Agent(operation_agent);
    }

    //pour récupérer tous les Operations_agent
    @GetMapping("/All")
    public List<Operation_Agent> getAllOperation_Agent() {
        List<Operation_Agent> operation_agents = operation_agentService.getAllOperation_Agent();
        return operation_agents;
    }
    @DeleteMapping("/{id}")
    public void  deleteOperation_AgentById(@PathVariable Long id) {
        operation_agentService.deleteOperation_AgentById(id);
    }
}
