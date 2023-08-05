package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Entretien;
import com.example.backendstage.Models.Reglement;
import com.example.backendstage.Requests.CandidatRequest;
import com.example.backendstage.Requests.EntretienRequest;
import com.example.backendstage.Requests.ReglementRequest;
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
    @PostMapping("/Save/{CandidatId}")
    public Reglement saveReglement(@RequestBody ReglementRequest reglement,@PathVariable Long CandidatId) {

        return reglementService.saveReglement(reglement,CandidatId);
    }
    @PutMapping("/{id}/Update")
    public Reglement updateReglement(@PathVariable Long id,@RequestBody ReglementRequest reglement){return  reglementService.updateReglement(id,reglement);}
    //
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
