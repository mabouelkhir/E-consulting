package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Employeur;
import com.example.backendstage.Services.EmployeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employeur")
public class EmployeurController {
    private final EmployeurService employeurService;
    @Autowired

    public EmployeurController(EmployeurService employeurService) {
        this.employeurService = employeurService;
    }


    //pour enregistrer un nouvel Employeur
    @PostMapping("/Save")
    public Employeur saveEmployeur(@RequestBody Employeur employeur) {
        return employeurService.saveEmployeur(employeur);
    }

    //pour récupérer tous les Employeurs
    @GetMapping("/Employeur")
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
