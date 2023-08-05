package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Reglement;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.ReglementRepository;
import com.example.backendstage.Requests.ReglementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReglementService {
    private final ReglementRepository reglementRepository;
    private final CandidatRepository candidatRepository;

    @Autowired
    public ReglementService(ReglementRepository reglementRepository, CandidatRepository candidatRepository) {
        this.reglementRepository = reglementRepository;
        this.candidatRepository = candidatRepository;
    }

    // Méthode pour enregistrer un nouveau règlement dans la base de données
    public Reglement saveReglement(ReglementRequest reglementRequest, Long candidatId) {
        Candidat candidat = candidatRepository.findById(candidatId).get();
        Reglement reglement = new Reglement();
        reglement.setCandidat(candidat);
        reglement.setDate_reglement(reglementRequest.getDate_reglement());
        reglement.setType_reglement(reglementRequest.getType_reglement());
        reglement.setMontant(reglementRequest.getMontant());
        return reglementRepository.save(reglement);
    }

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

        existingReglement.setDate_reglement(updatedReglement.getDate_reglement());
        existingReglement.setType_reglement(updatedReglement.getType_reglement());
        existingReglement.setMontant(updatedReglement.getMontant());

        return reglementRepository.save(existingReglement);
    }
}

