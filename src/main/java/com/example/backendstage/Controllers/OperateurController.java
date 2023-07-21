package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Operateur;
import com.example.backendstage.Services.OperateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/operateur")
public class OperateurController {

    private final OperateurService operateurService;
    @Autowired
    public OperateurController(OperateurService operateurService) {
        this.operateurService = operateurService;
    }

    //pour enregistrer un nouvel operator
    @PostMapping("/Save")
    public Operateur saveOperator(@RequestBody Operateur operateur) {
        return operateurService.saveOperateur(operateur);
    }

    //pour récupérer tous les operator
    @GetMapping("/Operator")
    public List<Operateur> getAllOperateurs() {
        List<Operateur> operateurs = operateurService.getAllOperateurs();
        return operateurs;
    }

    //pour supprimer un agent par son ID
    @DeleteMapping("/{id}")
    public void  deleteOperateurById(@PathVariable Long id) {
        operateurService.deleteOperateurById(id);
    }
}
