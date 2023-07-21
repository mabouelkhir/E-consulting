package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Services.AgentService;
import com.example.backendstage.Services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/candidat")
public class CandidatController {
    private final CandidatService candidatService;

    @Autowired
    public CandidatController(CandidatService candidatService) {
        this.candidatService = candidatService;
    }




    //pour enregistrer un nouvel candidat
    @PostMapping("/Save")
    public Candidat saveCandidat(@RequestBody Candidat candidat) {
        return candidatService.saveCandidat(candidat);
    }

    //pour récupérer tous les candidats
    @GetMapping("/Candidat")
    public List<Candidat> getAllcandidats() {
        List<Candidat> candidats= candidatService.getAllCandidats();
        return candidats;
    }

    //pour supprimer un Candidat par son ID
    @DeleteMapping("/{id}")
    public void  deleteCandidatById(@PathVariable Long id) {
        candidatService.deleteCandidatById(id);
    }
}
