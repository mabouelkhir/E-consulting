package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Dossier;
import com.example.backendstage.Models.DossierPieces;
import com.example.backendstage.Requests.DossierPieceRequest;
import com.example.backendstage.exception.AlreadyExistsException;
import com.example.backendstage.Requests.DossierRequest;
import com.example.backendstage.Services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/{idCandidat}/add")
    public ResponseEntity<?> ajouterDossierCandidat(@PathVariable Long idCandidat, @RequestBody DossierRequest dossierRequest) {
        try {
            Dossier dossierCandidat = dossierService.saveDossier(idCandidat, dossierRequest);
            return new ResponseEntity<>(dossierCandidat, HttpStatus.OK);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/{dossierId}")
    public List<DossierPieces> ajouterPiecesAuDossier(@PathVariable Long dossierId) {
        return dossierService.ajouterPiecesAuDossier(dossierId);
    }
    @PutMapping("/{dossierId}/update")
    public Dossier updateDossier(@PathVariable Long dossierId,@RequestBody DossierRequest dossierRequest){
    return dossierService.updateDossier(dossierId,dossierRequest);
    }
    @PutMapping("/{dossierId}/dossier/{pieceId}/update")
    public ResponseEntity<?> updateDossierPiece(@PathVariable Long dossierId, @PathVariable Long pieceId, @RequestBody DossierPieceRequest dossierPieceRequest) {
        try {
            DossierPieces dossierPieces = dossierService.updateDossierPiece(dossierId, pieceId,dossierPieceRequest);
            return new ResponseEntity<>(dossierPieces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    //pour récupérer tous les dossiers
        @GetMapping("/All")
        public List<Dossier> getAllDossiers() {
            List<Dossier> dossiers= dossierService.getAllDossiers();
            return dossiers;
        }

        @GetMapping("/{dossierID}")
        public Dossier getDossierById(@PathVariable Long dossierID){
    return dossierService.getDossierById(dossierID);
        }

        //pour supprimer un Dossier par son ID
        @DeleteMapping("/{id}/delete")
        public void  deleteDossierById(@PathVariable Long id) {
            dossierService.deleteDossierById(id);
        }
    }


