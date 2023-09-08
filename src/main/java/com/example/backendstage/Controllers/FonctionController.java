package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Fonction;
import com.example.backendstage.Requests.FonctionRequest;
import com.example.backendstage.Services.FonctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> saveFonction(@RequestBody FonctionRequest  fonction) {
        try{
            Fonction fonction1 = fonctionService.saveFonctionRequest(fonction);
            return new ResponseEntity<>(fonction1, HttpStatus.OK);
        }catch (Exception e){
             return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/{fonctionID}/update")
    public Fonction updateFonction(@PathVariable Long fonctionID,@RequestBody FonctionRequest fonctionRequest){
        return fonctionService.updateFonction(fonctionID,fonctionRequest);
    }


    @GetMapping("/All")
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
