package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Employeur;
import com.example.backendstage.Repositories.EmployeurRepository;
import com.example.backendstage.Requests.CandidatRequest;
import com.example.backendstage.Requests.EmployeurRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeurService {
    private final EmployeurRepository employeurRepository;

    @Autowired
    public EmployeurService(EmployeurRepository employeurRepository) {
        this.employeurRepository = employeurRepository;
    }

    // Méthode pour enregistrer un nouvel employeur dans la base de données
    public Employeur saveEmployeur(Employeur employeur) {
        return employeurRepository.save(employeur);
    }

    // Méthode pour récupérer un employeur par son ID
    public Employeur getEmployeurById(Long id) {
        return employeurRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les employeurs de la base de données
    public  List<Employeur> getAllEmployeurs() {
        return employeurRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'un employeur
    public Employeur updateEmployeur(Long id, EmployeurRequest updatedEmployeur) {

        Employeur existingEmployeur = employeurRepository.findById(id).orElse(null);
        if (existingEmployeur == null) {
            return null;
        }

        existingEmployeur.setNum_tel(updatedEmployeur.getNum_tel());
        existingEmployeur.setCity(updatedEmployeur.getCity());
        existingEmployeur.setAdresse(updatedEmployeur.getAdresse());
        existingEmployeur.setEmail(updatedEmployeur.getEmail());
        existingEmployeur.setCode_emp(updatedEmployeur.getCode_emp());
        existingEmployeur.setNom(updatedEmployeur.getNom());
        existingEmployeur.setPrenom(updatedEmployeur.getPrenom());

        return employeurRepository.save(existingEmployeur);
    }

    // Méthode pour supprimer un employeur par son ID
    public  void deleteEmployeurById(Long id) {
        employeurRepository.deleteById(id);
    }
}
