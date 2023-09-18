package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Permis;
import com.example.backendstage.Models.PermisCategorie;
import com.example.backendstage.Requests.PermisCategorieRequest;
import com.example.backendstage.Requests.PermisRequest;
import com.example.backendstage.Services.PermisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/permis")
public class PermisController {
    private final PermisService permisService;

    @Autowired
    public PermisController(PermisService permisService) {
        this.permisService = permisService;
    }

    @PostMapping("/{candidatID}/AddPermis")
    public Permis savePermis(@PathVariable Long candidatID, @RequestBody PermisRequest permisRequest){
        return permisService.savePermis(candidatID,permisRequest);
    }

    @PostMapping("/{permisID}/ajouterCategorie")
    public PermisCategorie ajouterCategorie(@PathVariable Long permisID, @RequestBody PermisCategorieRequest permisCategorieRequest){
        return permisService.ajouterCategorie(permisID,permisCategorieRequest);
    }

    @GetMapping("/permisCategorie/All")
    public List<PermisCategorie> getAllPermisCategorie(){
        return permisService.getAllPermisCategorie();
    }
    @GetMapping("/All")
    public List<Permis> getAllPermis(){
        return permisService.getAllPermis();
    }

    @GetMapping("getByPermis/{candidatID}")
    public List<PermisCategorie> getPermisByCandidatID(@PathVariable Long candidatID){
        return permisService.getPermisByCandidatID(candidatID);
    }
    @PutMapping("/updatePermis/{permisID}")
    public Permis updatePermis(@PathVariable Long permisID, @RequestBody PermisRequest permisRequest){
        return permisService.updatePermis(permisID, permisRequest);
    }

    @PutMapping("/updatePermisCategorie/{permisCategorieID}")
    public PermisCategorie updatePermisCategorie(@PathVariable Long permisCategorieID, @RequestBody PermisCategorieRequest permisCategorieRequest){
        return permisService.updatePermisCategorie(permisCategorieID, permisCategorieRequest);
    }

    @GetMapping("/getPermis/{permisID}")
    public Permis getPermisById(@PathVariable Long permisID){
        return permisService.getPermisById(permisID);
    }

    @GetMapping("/getPermisCategorie/{permisCategorieID}")
    public PermisCategorie getPermisCategorieById(@PathVariable Long permisCategorieID){
        return permisService.getPermisCategorieById(permisCategorieID);
    }

    @DeleteMapping("/deletePermis/{permisID}")
    public void deletePermisById(@PathVariable Long permisID){
        permisService.deletePermisById(permisID);
    }

    @DeleteMapping("/deletePermisCategorie/{permisCategorieID}")
    public void deletePermisCategorieById(@PathVariable Long permisCategorieID){
        permisService.deletePermisCategorieById(permisCategorieID);
    }
}
