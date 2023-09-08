package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Employeur;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.EmployeurRepository;
import com.example.backendstage.Requests.EmployeurRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeurService {
    private final EmployeurRepository employeurRepository;
    private  final CandidatRepository candidatRepository;

    @Autowired
    public EmployeurService(EmployeurRepository employeurRepository, CandidatRepository candidatRepository) {
        this.employeurRepository = employeurRepository;
        this.candidatRepository = candidatRepository;
    }

    // Méthode pour enregistrer un nouvel employeur dans la base de données


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
        existingEmployeur.setCodeEmp(updatedEmployeur.getCodeEmp());
        existingEmployeur.setRef_contrat(updatedEmployeur.getRef_contrat());
        existingEmployeur.setCountry(updatedEmployeur.getCountry());
        return employeurRepository.save(existingEmployeur);
    }

    // Méthode pour supprimer un employeur par son ID
    public  void deleteEmployeurById(Long id) {
        employeurRepository.deleteById(id);
    }

    public Employeur saveEmployeur(Employeur employeur) {
        return employeurRepository.save(employeur);
    }
    public void assignCandidatToEmployeur(Long candidatId1, Long employeurId) {
        Candidat candidat = candidatRepository.findById(candidatId1).orElse(null);
        Employeur employeur = employeurRepository.findById(employeurId).orElse(null);

        if (candidat != null && employeur != null) {
            candidat.setEmployeur(employeur);
            employeur.getCandidatList().add(candidat);
            candidatRepository.save(candidat);
            employeurRepository.save(employeur);
        }
    }




}
