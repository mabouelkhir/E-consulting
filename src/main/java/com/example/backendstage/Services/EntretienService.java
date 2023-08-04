package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Entretien;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.EntretienRepository;
import com.example.backendstage.Requests.EntretionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntretienService {
    private final EntretienRepository entretienRepository;
    private final CandidatRepository condidatRepository;

    @Autowired
    public EntretienService(EntretienRepository entretienRepository, CandidatRepository condidatRepository) {
        this.entretienRepository = entretienRepository;
        this.condidatRepository = condidatRepository;
    }



    // Méthode pour enregistrer un nouvel entretien dans la base de données
    public Entretien saveEntretien(EntretionRequest entretienRequest,Long CandidatId) {
        Candidat candidat = condidatRepository.findById(CandidatId).get();
        Entretien entretien = new Entretien();
        entretien.setCandidat(candidat);
        entretien.setDate_entretien(entretienRequest.getDate_entretien());
        entretien.setTitre(entretienRequest.getTitre());
        entretien.setStatus(entretienRequest.getStatus());
        entretien.setDescription(entretienRequest.getDescription());


        return entretienRepository.save(entretien);
    }

    // Méthode pour récupérer un entretien par son ID
    public Entretien getEntretienById(Long id) {
        return entretienRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les entretiens de la base de données
    public  List<Entretien> getAllEntretiens() {
        return entretienRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'un entretien
    public Entretien updateEntretien(Long id, Entretien updatedEntretien) {
        // Check if the entretien with the given ID exists in the database
        Entretien existingEntretien = entretienRepository.findById(id).orElse(null);
        if (existingEntretien == null) {
            // Handle the case when the entretien with the given ID does not exist
            return null;
        }

        // Update the properties of the existing entretien with the properties of the updatedEntretien
        existingEntretien.setTitre(updatedEntretien.getTitre());
        existingEntretien.setDescription(updatedEntretien.getDescription());
        existingEntretien.setDate_entretien(updatedEntretien.getDate_entretien());
        existingEntretien.setStatus(updatedEntretien.getStatus());
        existingEntretien.setCandidat(updatedEntretien.getCandidat());

        // Save the updated entretien to the database
        return entretienRepository.save(existingEntretien);
    }

    // Méthode pour supprimer un entretien par son ID
    public void deleteEntretienById(Long id) {
        entretienRepository.deleteById(id);
    }
}
