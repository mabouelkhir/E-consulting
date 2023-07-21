package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Fonction;
import com.example.backendstage.Services.AgentService;
import com.example.backendstage.Services.FonctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/fonction")
public class FonctionController {
    private final FonctionService fonctionService;


    @Autowired
    public FonctionController(FonctionService fonctionService) {
        this.fonctionService = fonctionService;
    }

    //pour enregistrer une nouvelle fonnction
    @PostMapping("/Save")
    public Fonction saveFonction(@RequestBody Fonction fonction) {
        return fonctionService.saveFonction(fonction);
    }

    //pour récupérer tous les agents
    @GetMapping("/Agent")
    public List<Fonction> getAllFonctions() {
        List<Fonction> fonctions = fonctionService.getAllFonctions();
        return fonctions;
    }

    //pour supprimer un agent par son ID
    @DeleteMapping("/{id}")
    public void  deleteFonctionById(@PathVariable Long id) {
        fonctionService.deleteFonctionById(id);
    }
}
