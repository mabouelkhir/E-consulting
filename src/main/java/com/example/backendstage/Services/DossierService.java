package com.example.backendstage.Services;

import com.example.backendstage.Models.Dossier;
import com.example.backendstage.Repositories.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierService {
    private final DossierRepository dossierRepository;

    @Autowired
    public DossierService(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    // Méthode pour enregistrer un nouveau dossier dans la base de données
    public Dossier saveDossier(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    // Méthode pour récupérer un dossier par son ID
    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les dossiers de la base de données
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    // Méthode pour supprimer un dossier par son ID
    public void deleteDossierById(Long id) {
        dossierRepository.deleteById(id);
    }
}
