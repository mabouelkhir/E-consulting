package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Cin;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.CinRepository;
import com.example.backendstage.Requests.CinRequest;
import com.example.backendstage.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinService {
    private final CandidatRepository candidatRepository;
    private final CinRepository cinRepository;

    @Autowired
    public CinService(CandidatRepository candidatRepository, CinRepository cinRepository) {
        this.candidatRepository = candidatRepository;
        this.cinRepository = cinRepository;
    }

    public Cin saveCin(Long idCandidat, CinRequest cinRequest) {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(idCandidat);
        Cin cinCandidat= new Cin();
        System.out.println(cinRequest);
        Optional<Cin> existCin = cinRepository.findByCode(cinRequest.getCode());
        if (optionalCandidat.isPresent()) {
            if (existCin.isPresent() && existCin.get() !=null) {
                throw new AlreadyExistsException("Une cin avec le même code existe déjà.");
            }
            Candidat candidat = optionalCandidat.get();
            System.out.println(candidat);
            cinCandidat.setCandidat(candidat);
            cinCandidat.setCode(cinRequest.getCode());
            cinCandidat.setDate_validite(cinRequest.getDate_validite());
            cinCandidat.setDate_naissance(cinRequest.getDate_naissance());
            cinRepository.save(cinCandidat);
            candidat.setCin(cinCandidat);
            candidatRepository.save(candidat);

        } else {
            throw new RuntimeException("Candidat non trouvé avec l'ID : " + idCandidat);
        }
        return cinCandidat;
    }
    public Cin getCinById(Long id) {
        return cinRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les dossiers de la base de données
    public List<Cin> getAllCins() {
        return cinRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'un dossier
    public Cin updateCin(Long id, CinRequest updateCin) {
        // Check if the dossier with the given ID exists in the database
        Cin existingCin = cinRepository.findById(id).orElse(null);
        if (existingCin == null) {
            // Handle the case when the dossier with the given ID does not exist
            return null;
        }
        // Update the properties of the existing dossier with the properties of the updatedDossier
        existingCin.setDate_naissance(updateCin.getDate_naissance());
        existingCin.setCode(updateCin.getCode());
        existingCin.setDate_validite(updateCin.getDate_validite());


        // Save the updated dossier to the database
        return cinRepository.save(existingCin);
    }
    public void deleteCinById(Long id) {
        cinRepository.deleteById(id);
    }



}
