package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Reglement;
import com.example.backendstage.Services.AgentService;
import com.example.backendstage.Services.ReglementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reglement")
public class ReglementController {
    private final ReglementService reglementService;
    @Autowired
    public ReglementController(ReglementService reglementService) {
        this.reglementService = reglementService;
    }

    //pour enregistrer un nouvel reglement
    @PostMapping("/Save")
    public Reglement saveReglement(@RequestBody Reglement reglement) {
        return reglementService.saveReglement(reglement);
    }

    //pour récupérer tous les reglements
    @GetMapping("/All")
    public List<Reglement> getAllReglements() {
        List<Reglement> reglementss = reglementService.getAllReglements();
        return reglementss;
    }

    //pour supprimer un reglement par son ID
    @DeleteMapping("/{id}")
    public void  deleteReglementById(@PathVariable Long id) {
        reglementService.deleteReglementById(id);
    }
}
