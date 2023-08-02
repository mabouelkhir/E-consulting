package com.example.backendstage.Services;

import com.example.backendstage.Models.*;
import com.example.backendstage.Repositories.Candidat_IdentityPiecesRepository;
import com.example.backendstage.Repositories.FonctionRepository;
import com.example.backendstage.Repositories.Identity_pieceRepository;
import com.example.backendstage.Requests.CandidatRequest;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Requests.Candidat_IdentityPieceRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;
    private final FonctionRepository fonctionRepository;
    private final Identity_pieceRepository identity_pieceRepository;
    private final Candidat_IdentityPiecesRepository candidatIdentityPiecesRepository;

    @Autowired
    public CandidatService(CandidatRepository candidatRepository,
                           FonctionRepository fonctionRepository,
                           Identity_pieceRepository identity_pieceRepository,
                           Candidat_IdentityPiecesRepository candidatIdentityPiecesRepository) {
        this.candidatRepository = candidatRepository;
        this.fonctionRepository = fonctionRepository;
        this.identity_pieceRepository = identity_pieceRepository;
        this.candidatIdentityPiecesRepository = candidatIdentityPiecesRepository;
    }

    // Méthode pour enregistrer un nouveau candidat dans la base de données
    public Candidat saveCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    // Méthode pour récupérer un candidat par son ID
    public Candidat getCandidatById(Long id) {
        return candidatRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les candidats de la base de données
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'un candidat
    public Candidat updateCandidat(Long id, CandidatRequest updatedCandidat) {
        // Check if the candidat with the given ID exists in the database
        Candidat existingCandidat = candidatRepository.findById(id).orElse(null);
        if (existingCandidat == null) {
            // Handle the case when the candidat with the given ID does not exist
            return null;
        }
        // Update the properties of the existing candidat with the properties of the updatedCandidat
        existingCandidat.setDate_naissance(updatedCandidat.getDate_naissance());
        existingCandidat.setSexe(updatedCandidat.getSexe());
        existingCandidat.setAdresse(updatedCandidat.getAdresse());
        existingCandidat.setNum_tel(updatedCandidat.getNum_tel());
        existingCandidat.setCin(updatedCandidat.getCin());
        existingCandidat.setObs(updatedCandidat.getObs());
        existingCandidat.setTl(updatedCandidat.getTl());
        existingCandidat.setStatus(updatedCandidat.getStatus());
        existingCandidat.setSituation_fam(updatedCandidat.getSituation_fam());
        existingCandidat.setChildren(updatedCandidat.getChildren());

        // Save the updated candidat to the database
        return candidatRepository.save(existingCandidat);
    }

    // Méthode pour supprimer un candidat par son ID
    public void deleteCandidatById(Long id) {
        candidatRepository.deleteById(id);
    }

    public Candidat assignFonctionToCandidat(Long candidatId, Long fonctionId) {
        Candidat candidat = candidatRepository.findById(candidatId).orElse(null);
        Fonction fonction = fonctionRepository.findById(fonctionId).orElse(null);

        if (candidat != null && fonction != null) {
            Set<Fonction> fonctionSet = candidat.getFonctions();
            if (fonctionSet == null) {
                fonctionSet = new HashSet<>();
            }

            fonctionSet.add(fonction);
            candidat.setFonctions(fonctionSet);
            candidatRepository.save(candidat);
        }

        return candidat;
    }

    public Candidat removeFonctionToCandidat(Long candidatId, Long fonctionId) {
        Candidat candidat = candidatRepository.findById(candidatId).orElse(null);
        Fonction fonction = fonctionRepository.findById(fonctionId).orElse(null);

        if (candidat != null && fonction != null) {
            Set<Fonction> fonctionSet = candidat.getFonctions();
            if (fonctionSet != null) {
                fonctionSet.remove(fonction);
                candidat.setFonctions(fonctionSet);
                candidatRepository.save(candidat);
            }
        }

        return candidat;
    }

    public Candidat_IdentityPieces ajouterIdentityPiecesAuCandidat(Long candidat_id,Long piece_id, Candidat_IdentityPieceRequest candidatIdentityPieceRequest){
        Candidat candidat = candidatRepository.findById(candidat_id).orElseThrow(EntityNotFoundException::new);
        Identity_piece piece = identity_pieceRepository.findById(piece_id).orElseThrow(EntityNotFoundException::new);
        Candidat_IdentityPieces candidatIdentityPieces = new Candidat_IdentityPieces();
        candidatIdentityPieces.setCandidat(candidat);
        candidatIdentityPieces.setIdentityPiece(piece);
        candidatIdentityPieces.setCode(candidatIdentityPieceRequest.getCode());
        candidatIdentityPieces.setEtat(candidatIdentityPieceRequest.getEtat());
        candidatIdentityPieces.setCreated_at(LocalDateTime.now());
        candidatIdentityPieces.setDate_validite(candidatIdentityPieceRequest.getDate_validite());

        candidatIdentityPiecesRepository.save(candidatIdentityPieces);
        return candidatIdentityPieces;

    }
}