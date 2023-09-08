package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Employeur;
import com.example.backendstage.Repositories.EmployeurRepository;
import com.example.backendstage.Requests.EmployeurRequest;
import com.example.backendstage.Services.EmployeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {
    private final EmployeurService employeurService;
    private final EmployeurRepository employeurRepository;

    @Autowired

    public EmployeurController(EmployeurService employeurService, EmployeurRepository employeurRepository) {
        this.employeurService = employeurService;
        this.employeurRepository = employeurRepository;
    }


    //pour enregistrer un nouvel Employeur


    @PutMapping("/{id}/Update")
    public Employeur updateEmployeur(@PathVariable Long id, @RequestBody EmployeurRequest employeur){
        return  employeurService.updateEmployeur(id,employeur);}

    @PostMapping("/{candidatID}/assignCandidatToEmployer/{employerID}")
    public void assignCandidatToEmployer(@PathVariable Long candidatID,@PathVariable Long employerID){
        employeurService.assignCandidatToEmployeur(candidatID,employerID);
    }

    @GetMapping("/{id}")
    public Optional<Employeur> getEmployeur(@PathVariable Long id) {
        Optional<Employeur> employeur= employeurRepository.findById(id);
        return employeur;
    }

    //pour récupérer tous les Employeurs
    @GetMapping("/All")
    public List<Employeur> getAllEmployeurs() {
        List<Employeur> Employeurs = employeurService.getAllEmployeurs();
        return Employeurs;
    }

    //pour supprimer un Employeur par son ID
    @DeleteMapping("/{id}")
    public void  deleteEmployeurById(@PathVariable Long id) {
        employeurService.deleteEmployeurById(id);
    }
}
