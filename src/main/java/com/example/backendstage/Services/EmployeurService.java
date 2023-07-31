package com.example.backendstage.Services;

import com.example.backendstage.Models.Employeur;
import com.example.backendstage.Repositories.EmployeurRepository;
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
    public Employeur updateEmployeur(Long id, Employeur updatedEmployeur) {
        // Check if the employeur with the given ID exists in the database
        Employeur existingEmployeur = employeurRepository.findById(id).orElse(null);
        if (existingEmployeur == null) {
            // Handle the case when the employeur with the given ID does not exist
            return null;
        }

        // Update the properties of the existing employeur with the properties of the updatedEmployeur
        existingEmployeur.setNum_tel(updatedEmployeur.getNum_tel());
        existingEmployeur.setAdresse(updatedEmployeur.getAdresse());
        existingEmployeur.setEmail(updatedEmployeur.getEmail());
        existingEmployeur.setCode_emp(updatedEmployeur.getCode_emp());
        existingEmployeur.setCandidatList(updatedEmployeur.getCandidatList());
        existingEmployeur.setCity(updatedEmployeur.getCity());

        // Save the updated employeur to the database
        return employeurRepository.save(existingEmployeur);
    }

    // Méthode pour supprimer un employeur par son ID
    public  void deleteEmployeurById(Long id) {
        employeurRepository.deleteById(id);
    }
}
