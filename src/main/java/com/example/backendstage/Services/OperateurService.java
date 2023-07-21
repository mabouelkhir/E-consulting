package com.example.backendstage.Services;

import com.example.backendstage.Models.Operateur;
import com.example.backendstage.Repositories.OperateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Méthode pour supprimer un opérateur par son ID
    public void deleteOperateurById(Long id) {
        operateurRepository.deleteById(id);
    }
}
