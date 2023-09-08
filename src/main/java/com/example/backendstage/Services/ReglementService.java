package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Reglement;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.ReglementRepository;
import com.example.backendstage.Requests.ReglementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ReglementService {
    private final ReglementRepository reglementRepository;
    private final CandidatRepository candidatRepository;


    @Autowired
    public ReglementService(ReglementRepository reglementRepository, CandidatRepository candidatRepository, CandidatService candidatService) {
        this.reglementRepository = reglementRepository;
        this.candidatRepository = candidatRepository;

    }

    // Méthode pour enregistrer un nouveau règlement dans la base de données


    // Méthode pour récupérer un règlement par son ID
    public Reglement getReglementById(Long id) {
        return reglementRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les règlements de la base de données
    public List<Reglement> getAllReglements() {
        return reglementRepository.findAll();
    }

    // Méthode pour supprimer un règlement par son ID
    public void deleteReglementById(Long id) {
        reglementRepository.deleteById(id);
    }

    public Reglement updateReglement(Long id, ReglementRequest updatedReglement) {
        Reglement existingReglement = reglementRepository.findById(id).orElse(null);
        if (existingReglement == null) {
            return null;
        }
        existingReglement.setType_reglement(updatedReglement.getType_reglement());
        existingReglement.setDate_reglement(updatedReglement.getDate_reglement());
        existingReglement.setMontant(updatedReglement.getMontant());
        existingReglement.setRef_contrat(updatedReglement.getRef_contrat());

        return reglementRepository.save(existingReglement);
    }

    public Reglement saveReglement(ReglementRequest reglementRequest, Long candidatId) throws CandidatNotFoundException {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(candidatId);

        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();
            Reglement reglement = new Reglement();
            reglement.setCandidat(candidat);

            reglement.setType_reglement(reglementRequest.getType_reglement());
            reglement.setDate_reglement(reglementRequest.getDate_reglement());
            reglement.setMontant(reglementRequest.getMontant());
            reglement.setRef_contrat(reglementRequest.getRef_contrat());

            return reglementRepository.save(reglement);
        } else {
            // Handle the case when no Candidat is found with the given ID
            throw new CandidatNotFoundException("Candidat not found with ID: " + candidatId);
        }
    }




    public Reglement getReglementByCandidatId(Long candidatID) {
        Candidat candidat = candidatRepository.findById(candidatID).get();
        return reglementRepository.findReglementByCandidat(candidat);

    }




}

