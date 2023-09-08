package com.example.backendstage.Services;

import com.example.backendstage.Models.Fonction;
import com.example.backendstage.Models.SubFonction;
import com.example.backendstage.Repositories.FonctionRepository;
import com.example.backendstage.Repositories.SubFonctionRepository;
import com.example.backendstage.Requests.FonctionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FonctionService {
    private final FonctionRepository fonctionRepository;
    private final SubFonctionRepository subFonctionRepository;

    @Autowired
    public FonctionService(FonctionRepository fonctionRepository, SubFonctionRepository subFonctionRepository) {
        this.fonctionRepository = fonctionRepository;
        this.subFonctionRepository = subFonctionRepository;
    }

    // Méthode pour enregistrer une nouvelle fonction dans la base de données
    public Fonction saveFonctionRequest(FonctionRequest fonction) {
        Fonction fonction1 = new Fonction();
        fonction1.setNom_fonction(fonction.getNom_fonction());

        return fonctionRepository.save(fonction1);
    }

    // Méthode pour récupérer une fonction par son ID
    public Fonction getFonctionById(Long id) {
        return fonctionRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer toutes les fonctions de la base de données
    public List<Fonction> getAllFonctions() {
        return fonctionRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'une fonction
    public Fonction updateFonction(Long id, FonctionRequest updatedFonction) {
        // Check if the fonction with the given ID exists in the database
        Fonction existingFonction = fonctionRepository.findById(id).orElse(null);
        if (existingFonction == null) {
            // Handle the case when the fonction with the given ID does not exist
            return null;
        }

        // Update the properties of the existing fonction with the properties of the updatedFonction
        existingFonction.setNom_fonction(updatedFonction.getNom_fonction());

        // Save the updated fonction to the database
        return fonctionRepository.save(existingFonction);
    }


    // Méthode pour supprimer une fonction par son ID
    public void deleteFonctionById(Long id) {
        fonctionRepository.deleteById(id);
    }


}
