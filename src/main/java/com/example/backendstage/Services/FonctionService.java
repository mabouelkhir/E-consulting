package com.example.backendstage.Services;

import com.example.backendstage.Models.Fonction;
import com.example.backendstage.Repositories.FonctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FonctionService {
    private final FonctionRepository fonctionRepository;

    @Autowired
    public FonctionService(FonctionRepository fonctionRepository) {
        this.fonctionRepository = fonctionRepository;
    }

    // Méthode pour enregistrer une nouvelle fonction dans la base de données
    public Fonction saveFonction(Fonction fonction) {
        return fonctionRepository.save(fonction);
    }

    // Méthode pour récupérer une fonction par son ID
    public Fonction getFonctionById(Long id) {
        return fonctionRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer toutes les fonctions de la base de données
    public List<Fonction> getAllFonctions() {
        return fonctionRepository.findAll();
    }

    // Méthode pour supprimer une fonction par son ID
    public void deleteFonctionById(Long id) {
        fonctionRepository.deleteById(id);
    }
}
