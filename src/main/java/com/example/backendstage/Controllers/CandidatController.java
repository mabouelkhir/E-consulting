package com.example.backendstage.Controllers;

import com.example.backendstage.Models.*;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Requests.CandidatRequest;
import com.example.backendstage.Requests.Candidat_IdentityPieceRequest;
import com.example.backendstage.Services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/candidat")
public class CandidatController {
    private final CandidatService candidatService;
    private final CandidatRepository candidatRepository;

    @Autowired
    public CandidatController(CandidatService candidatService,
                              CandidatRepository candidatRepository) {
        this.candidatService = candidatService;
        this.candidatRepository = candidatRepository;
    }

    //pour enregistrer un nouvel candidat
    @PostMapping("/Save")
    public Candidat saveCandidat(@RequestBody Candidat candidat) {
        return candidatService.saveCandidat(candidat);
    }
    @PostMapping("/{id_candidat}/identity_piece/{id_piece}")
    public ResponseEntity<?> ajouterIdentityPiecesAuCandidat(@PathVariable Long id_candidat, @PathVariable Long id_piece, @RequestBody Candidat_IdentityPieceRequest candidatIdentityPieceRequest) {
        try {
            Candidat_IdentityPieces candidatIdentityPieces = candidatService.ajouterIdentityPiecesAuCandidat(id_candidat, id_piece, candidatIdentityPieceRequest);
            return new ResponseEntity<>(candidatIdentityPieces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/Update")
    public Candidat updateCandidat(@PathVariable Long id,@RequestBody CandidatRequest candidat){return  candidatService.updateCandidat(id,candidat);}
    //ADD FUNCTION TO CANDIDAT
    @PutMapping("/{candidat_id}/fonction/{fonction_id}/add")
    public Candidat assignFonctionToCandidat(@PathVariable Long candidat_id,@PathVariable Long fonction_id){
        return candidatService.assignFonctionToCandidat(candidat_id,fonction_id);
    }
    @PutMapping("/{candidat_id}/fonction/{fonction_id}/remove")
    public Candidat removeFonctionToCandidat(@PathVariable Long candidat_id,@PathVariable Long fonction_id){
        return candidatService.removeFonctionToCandidat(candidat_id,fonction_id);
    }


    //pour récupérer tous les candidats
    @GetMapping("/All")
    public List<Candidat> getAllcandidats() {
        List<Candidat> candidats= candidatService.getAllCandidats();
        return candidats;
    }
    @GetMapping("/{id}")
    public Optional<Candidat> getCandidat(@PathVariable Long id) {
        Optional<Candidat> candidat= candidatRepository.findById(id);
        return candidat;
    }

    //pour supprimer un Candidat par son ID
    @DeleteMapping("/{id}")
    public void  deleteCandidatById(@PathVariable Long id) {
        candidatService.deleteCandidatById(id);
    }
}
