package com.example.backendstage.Services;

import com.example.backendstage.Models.Operateur;
import com.example.backendstage.Repositories.OperateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OperateurService {

    private final OperateurRepository operateurRepository;

    @Autowired
    public OperateurService(OperateurRepository operateurRepository) {
        this.operateurRepository = operateurRepository;
    }

    // Méthode pour enregistrer un nouvel opérateur dans la base de données
    public Operateur saveOperateur(Operateur operateur) {
        return operateurRepository.save(operateur);
    }

    // Méthode pour récupérer un opérateur par son ID
    public Operateur getOperateurById(Long id) {
        return operateurRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les opérateurs de la base de données
    public List<Operateur> getAllOperateurs() {
        return operateurRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'un opérateur
    public Operateur updateOperateur(Long id, Operateur updatedOperateur) {
        // Check if the operateur with the given ID exists in the database
        Operateur existingOperateur = operateurRepository.findById(id).orElse(null);
        if (existingOperateur == null) {
            // Handle the case when the operateur with the given ID does not exist
            return null;
        }

        // Update the properties of the existing operateur with the properties of the updatedOperateur
        existingOperateur.setNom(updatedOperateur.getNom());
        existingOperateur.setPrenom(updatedOperateur.getPrenom());
        existingOperateur.setSexe(updatedOperateur.getSexe());
        existingOperateur.setEmail(updatedOperateur.getEmail());
        existingOperateur.setTel(updatedOperateur.getTel());
        existingOperateur.setAdresse(updatedOperateur.getAdresse());
        existingOperateur.setDate_naissance(updatedOperateur.getDate_naissance());
        existingOperateur.setCin(updatedOperateur.getCin());

        // Update the image if it's provided in the updatedOperateur
        // if (updatedOperateur.getImage() != null) {
        // try {
        //     existingOperateur.setImage(updatedOperateur.getImage().getBytes());
        //   } catch (IOException e) {
        //       throw new RuntimeException("Failed to upload image");
        //   }
        // }

        // Save the updated operateur to the database
        return operateurRepository.save(existingOperateur);
    }

    // Méthode pour supprimer un opérateur par son ID
    public void deleteOperateurById(Long id) {
        operateurRepository.deleteById(id);
    }
}
