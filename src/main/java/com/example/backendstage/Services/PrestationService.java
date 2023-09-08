package com.example.backendstage.Services;

import com.example.backendstage.Models.Employeur;
import com.example.backendstage.Models.Prestation;
import com.example.backendstage.Repositories.EmployeurRepository;
import com.example.backendstage.Repositories.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrestationService {
    private final PrestationRepository prestationRepository;
    private final EmployeurRepository employeurRepository;

    @Autowired
    public PrestationService(PrestationRepository prestationRepository, EmployeurRepository employeurRepository) {
        this.prestationRepository = prestationRepository;
        this.employeurRepository = employeurRepository;
    }

    public Prestation savePrestation(Prestation prestation) {
        return prestationRepository.save(prestation);
    }

    public Prestation getPrestationById(Long id) {
        return prestationRepository.findById(id).orElse(null);
    }

    public List<Prestation> getAllPrestations() {
        return prestationRepository.findAll();
    }

    public Prestation updatePrestation(Long id, Prestation updatedPrestation) {
        Prestation existingPrestation = prestationRepository.findById(id).orElse(null);
        if (existingPrestation != null) {
            // Mettez à jour les propriétés de existingPrestation avec celles de updatedPrestation
            existingPrestation.setDebut(updatedPrestation.getDebut());
            existingPrestation.setFin(updatedPrestation.getFin());
            existingPrestation.setMontant(updatedPrestation.getMontant());
            existingPrestation.setEtat_financier(updatedPrestation.getEtat_financier());
            // Enregistrez la prestation mise à jour dans la base de données
            return prestationRepository.save(existingPrestation);
        } else {
            return null;
        }
    }

    public void deletePrestationById(Long id) {
        prestationRepository.deleteById(id);
    }

    public void assignEmployeurToPrestation(Long employeurId, Long prestationId) {
        Prestation prestation = prestationRepository.findById(prestationId).orElse(null);
        Employeur employeur = employeurRepository.findById(employeurId).orElse(null);



        if (employeur != null && prestation != null) {

            List<Prestation> prestationList =  employeur.getPrestations();
            if(prestationList == null){
                prestationList= new ArrayList<>();
            }

            prestationList.add(prestation);
            employeur.setPrestations(prestation);
            prestationRepository.save(prestation);
        }
    }
}

