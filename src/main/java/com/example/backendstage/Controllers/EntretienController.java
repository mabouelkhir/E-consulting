package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Entretien;
import com.example.backendstage.Requests.EntretionRequest;
import com.example.backendstage.Services.EntretienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/entretien")
public class EntretienController {
    private final EntretienService entretienService;
    @Autowired

    public EntretienController(EntretienService entretienService) {
        this.entretienService = entretienService;
    }


    //pour enregistrer un nouvel Entretien
    @PostMapping("/Save/{CandidatId}")
    public Entretien saveEntretien(@RequestBody EntretionRequest entretionRequest,@PathVariable Long CandidatId) {
        return entretienService.saveEntretien(entretionRequest,CandidatId);
    }

    //pour récupérer tous les Entretiens
    @GetMapping("/All")
    public List<Entretien> getAllEntretiens() {
        List<Entretien> entretiens = entretienService.getAllEntretiens();
        return entretiens;
    }


    //pour supprimer un Entretien par son ID
    @DeleteMapping("/{id}")
    public void  deleteEntretienById(@PathVariable Long id) {
        entretienService.deleteEntretienById(id);
    }
}
