package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Dossier;
import com.example.backendstage.Services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dossier")
public class DossierController {

        private final DossierService dossierService;
@Autowired
    public DossierController(DossierService dossierService) {
        this.dossierService = dossierService;
    }


    //pour enregistrer un nouvel dossier

    @PostMapping("/Save")
    public Dossier saveDossier(@RequestBody Dossier dossier) {
        return dossierService.saveDossier(dossier);
    }


    //pour récupérer tous les dossiers
        @GetMapping("/Dossier")
        public List<Dossier> getAllDossiers() {
            List<Dossier> dossiers= dossierService.getAllDossiers();
            return dossiers;
        }

        //pour supprimer un Dossier par son ID
        @DeleteMapping("/{id}")
        public void  deleteDossierById(@PathVariable Long id) {
            dossierService.deleteDossierById(id);
        }
    }


