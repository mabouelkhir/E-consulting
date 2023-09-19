package com.example.backendstage.Controllers;

import com.example.backendstage.Models.SubFonction;
import com.example.backendstage.Requests.SubFonctionRequest;
import com.example.backendstage.Services.SubFonctionService;
import com.example.backendstage.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/subfonctions")
public class SubFonctionController {
    @Autowired
    private SubFonctionService subFonctionService;

    @PostMapping("/{fonctionID}/add")
    public ResponseEntity<SubFonction> createSubFonction(@PathVariable Long fonctionID,@RequestBody SubFonctionRequest subFonction) {
        SubFonction createdSubFonction = subFonctionService.createSubFonction(fonctionID,subFonction);
        return ResponseEntity.ok(createdSubFonction);
    }

    @PutMapping("/{subFonctionId}")
    public ResponseEntity<SubFonction> updateSubFonction(
            @PathVariable Long subFonctionId,
            @RequestBody SubFonctionRequest updatedSubFonction) throws NotFoundException {
        SubFonction updateSubFonction = subFonctionService.updateSubFonction(subFonctionId, updatedSubFonction);
        return ResponseEntity.ok(updateSubFonction);
    }

    @DeleteMapping("/{subFonctionId}")
    public ResponseEntity<Void> deleteSubFonction(@PathVariable Long subFonctionId) {
        subFonctionService.deleteSubFonction(subFonctionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<SubFonction> getAllSubFonctionsWithFunctions() {
        return subFonctionService.getAllSubFonctionsWithFunctions();

    }

    @GetMapping("/{subFonctionId}")
    public ResponseEntity<SubFonction> getSubFonctionById(@PathVariable Long subFonctionId) throws NotFoundException {
        SubFonction subFonction = subFonctionService.getSubFonctionById(subFonctionId);
        return ResponseEntity.ok(subFonction);
    }

}
