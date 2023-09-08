package com.example.backendstage.Controllers;


import com.example.backendstage.Models.Rendez_vous;
import com.example.backendstage.Requests.Rendez_vousRequest;
import com.example.backendstage.Services.Rendez_vousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rendez_vous")
public class Rendez_vousController {
    private final Rendez_vousService rendez_vousService;
    @Autowired

    public Rendez_vousController(Rendez_vousService rendez_vousService) {
        this.rendez_vousService = rendez_vousService;
    }


    //pour enregistrer un nouvel Entretien
    @PostMapping("/Save/{CandidatId}")
    public Rendez_vous saveRendez_vous(@RequestBody Rendez_vousRequest rendez_vous, @PathVariable Long CandidatId) {
        return rendez_vousService.saveRendez_vous(rendez_vous,CandidatId);
    }

    //pour récupérer tous les Entretiens
    @GetMapping("/All")
    public List<Rendez_vous> getAllRendez_vous() {
        List<Rendez_vous> rendez_vous = rendez_vousService.getAllRendez_vous();
        return rendez_vous;
    }
    @PutMapping("/{id}/Update")
    public Rendez_vous updateRendez_vous(@PathVariable Long id, @RequestBody Rendez_vousRequest rendez_vous){
        return  rendez_vousService.updateRendez_vous(id,rendez_vous);
    }

    //pour supprimer un Entretien par son ID
    @DeleteMapping("/{id}")
    public void  deleteRendez_vousById(@PathVariable Long id) {
        rendez_vousService.deleteRendez_vousById(id);
    }
}
