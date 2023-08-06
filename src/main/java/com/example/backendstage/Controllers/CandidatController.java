package com.example.backendstage.Controllers;

import com.example.backendstage.Models.*;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Requests.CandidatRequest;
import com.example.backendstage.Requests.Candidat_IdentityPieceRequest;
import com.example.backendstage.Services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    @PostMapping("/{id}/cv")
    public ResponseEntity<String> uploadCV(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            candidatService.uploadCV(id, file);
            return ResponseEntity.ok("CV uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CV upload failed!");
        }
    }
    @GetMapping("/{id}/cv")
    public ResponseEntity<InputStreamResource> downloadCV(@PathVariable Long id) {
        Candidat candidat = candidatService.getCandidatById(id);
        if (candidat == null || candidat.getCv() == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] cvData = candidat.getCv();
        String cvFileName = "candidat_cv_" + candidat.getId() + "_"+candidat.getNom()+"_"+ candidat.getPrenom() +".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(cvFileName, cvFileName);

        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(cvData));
        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
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
    @GetMapping("/countByStatus/{status}")
    public ResponseEntity<Long> countCandidatesByStatus(@PathVariable EStatus status) {
        long count = candidatService.countCandidatesByStatus(status);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/group/{group}")
    public ResponseEntity<List<Candidat>> getCandidatesByGroup(@PathVariable String group) {
        List<Candidat> candidates = candidatService.getCandidatesByGroup(group);
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }
    //récupérer les candidats actifs
    @GetMapping("/candidats/actifs")
    public List<Candidat> getActiveCandidats() {
        return candidatService.getActiveCandidates();
    }
    //récupérer les candidats inactifs
    @GetMapping("/candidats/inactifs")
    public List<Candidat> getInactiveCandidats() {
        return candidatService.getInactiveCandidates();
    }

}

