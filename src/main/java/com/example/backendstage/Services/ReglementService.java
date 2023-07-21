package com.example.backendstage.Services;

import com.example.backendstage.Models.Reglement;
import com.example.backendstage.Repositories.ReglementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReglementService {
    private final ReglementRepository reglementRepository;

    @Autowired
    public ReglementService(ReglementRepository reglementRepository) {
        this.reglementRepository = reglementRepository;
    }

    // Méthode pour enregistrer un nouveau règlement dans la base de données
    public Reglement saveReglement(Reglement reglement) {
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

}
