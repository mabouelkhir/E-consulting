package com.example.backendstage.Controllers;

import com.example.backendstage.Models.*;
import com.example.backendstage.Requests.ReglementRequest;
import com.example.backendstage.Services.CandidatNotFoundException;
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
    @PostMapping("/Save/candidat/{candidatId}")
    public Reglement saveReglement(@RequestBody ReglementRequest reglement,@PathVariable Long candidatId) throws CandidatNotFoundException {

        return reglementService.saveReglement(reglement,candidatId);
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
    @GetMapping("/candidat/{candidatID}")
    public Reglement getReglementsByCandidatId(@PathVariable Long candidatID){
        Reglement reglements = reglementService.getReglementByCandidatId(candidatID);

        return reglements;
    }










    //pour supprimer un reglement par son ID
    @DeleteMapping("/{id}")
    public void  deleteReglementById(@PathVariable Long id) {
        reglementService.deleteReglementById(id);
    }
}
