package com.example.backendstage.Services;

import com.example.backendstage.Models.Entretien;
import com.example.backendstage.Repositories.EntretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntretienService {
    private final EntretienRepository entretienRepository;

    @Autowired
    public EntretienService(EntretienRepository entretienRepository) {
        this.entretienRepository = entretienRepository;
    }

    // Méthode pour enregistrer un nouvel entretien dans la base de données
    public Entretien saveEntretien(Entretien entretien) {
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

    // Méthode pour supprimer un entretien par son ID
    public void deleteEntretienById(Long id) {
        entretienRepository.deleteById(id);
    }
}
