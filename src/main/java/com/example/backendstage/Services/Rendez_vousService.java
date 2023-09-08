package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;

import com.example.backendstage.Models.Rendez_vous;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.Rendez_vousRepository;
import com.example.backendstage.Requests.Rendez_vousRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Rendez_vousService {
    private final Rendez_vousRepository rendez_vousRepository;
    private final CandidatRepository candidatRepository;

    @Autowired
    public Rendez_vousService(Rendez_vousRepository rendez_vousRepository,CandidatRepository candidatRepository) {
        this.rendez_vousRepository = rendez_vousRepository;
        this.candidatRepository = candidatRepository;
    }



    // Méthode pour enregistrer un nouvel entretien dans la base de données
    public Rendez_vous saveRendez_vous(Rendez_vousRequest rendez_vousRequest, Long CandidatId) {
        Candidat candidat =candidatRepository.findById(CandidatId).get();
        Rendez_vous rendez_vous = new Rendez_vous();
        rendez_vous.setCandidat(candidat);
        rendez_vous.setDate_entretien(rendez_vousRequest.getDate_entretien());
        rendez_vous.setTitre(rendez_vousRequest.getTitre());
        rendez_vous.setStatus(rendez_vousRequest.getStatus());
        rendez_vous.setDescription(rendez_vousRequest.getDescription());
        return rendez_vousRepository.save(rendez_vous);
    }

    // Méthode pour récupérer un entretien par son ID
    public Rendez_vous getRendez_vousById(Long id) {
        return rendez_vousRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les entretiens de la base de données
    public  List<Rendez_vous> getAllRendez_vous() {
        return rendez_vousRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'un entretien
    public Rendez_vous updateRendez_vous(Long id, Rendez_vousRequest updatedRendez_vous) {
        // Check if the entretien with the given ID exists in the database
        Rendez_vous existingRendez_vous = rendez_vousRepository.findById(id).orElse(null);
        if (existingRendez_vous == null) {
            // Handle the case when the entretien with the given ID does not exist
            return null;
        }

        // Update the properties of the existing entretien with the properties of the updatedEntretien
        existingRendez_vous.setTitre(updatedRendez_vous.getTitre());
        existingRendez_vous.setDescription(updatedRendez_vous.getDescription());
        existingRendez_vous.setDate_entretien(updatedRendez_vous.getDate_entretien());
        existingRendez_vous.setStatus(updatedRendez_vous.getStatus());

        // Save the updated entretien to the database
        return rendez_vousRepository.save(existingRendez_vous);
    }

    // Méthode pour supprimer un entretien par son ID
    public void deleteRendez_vousById(Long id) {
        rendez_vousRepository.deleteById(id);
    }
}
