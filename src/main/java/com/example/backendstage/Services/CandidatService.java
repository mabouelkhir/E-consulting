package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;

    @Autowired
    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
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

    // Méthode pour supprimer un candidat par son ID
    public void deleteCandidatById(Long id) {
        candidatRepository.deleteById(id);
    }
}