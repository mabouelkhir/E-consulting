package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Operation_Agent;
import com.example.backendstage.Models.Reglement;
import com.example.backendstage.Models.TableOperation_Agent;
import com.example.backendstage.Requests.ReglementRequest;
import com.example.backendstage.Requests.TableOperation_AgentRequest;
import com.example.backendstage.Services.CandidatNotFoundException;
import com.example.backendstage.Services.Operation_AgentService;
import com.example.backendstage.Services.TableOperation_AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/TableOperation_Agent")
public class TableOperation_AgentController {
    private final TableOperation_AgentService tableoperation_agentService;


    @Autowired
    public TableOperation_AgentController(TableOperation_AgentService tableoperation_agentService) {
        this.tableoperation_agentService = tableoperation_agentService;

    }

    @PostMapping("/Save/candidat/{candidatId}")

    public TableOperation_Agent saveTableOperation_Agent(@RequestBody TableOperation_AgentRequest tableoperation_agent, @PathVariable Long candidatId)throws CandidatNotFoundException  {

        return tableoperation_agentService.saveTableOperation_Agent(tableoperation_agent,candidatId);
    }



    @GetMapping("/candidat/{candidatID}")
    public TableOperation_Agent getTableOperation_AgentByCandidatId(@PathVariable Long candidatID){
         TableOperation_Agent tableOperation_agent = tableoperation_agentService.getTableOperation_AgentByCandidatId(candidatID);

        return tableOperation_agent;
    }


    //pour récupérer tous les Operations_agent
    @GetMapping("/All")
    public List<TableOperation_Agent> getAllTableOperation_Agent() {
        List<TableOperation_Agent> tableoperation_agents = tableoperation_agentService.getAllTableOperation_Agent();
        return tableoperation_agents;
    }
    @DeleteMapping("/{id}")
    public void  deleteTableOperation_AgentById(@PathVariable Long id) {
        tableoperation_agentService.deleteTableOperation_AgentById(id);
    }
}
