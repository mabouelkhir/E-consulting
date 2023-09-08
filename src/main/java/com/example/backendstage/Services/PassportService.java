package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Passport;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.PassportRepository;
import com.example.backendstage.Requests.PassportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PassportService {
    private final PassportRepository passportRepository;
    private final CandidatRepository candidatRepository;


    @Autowired
    public PassportService(PassportRepository passportRepository, CandidatRepository candidatRepository) {
        this.passportRepository = passportRepository;
        this.candidatRepository = candidatRepository;
    }

    public Passport createPassport(Long candidatID, PassportRequest passportRequest) {
        Candidat candidat = candidatRepository.findById(candidatID).orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidatID));

        Passport newPassport = new Passport();
        newPassport.setCode(passportRequest.getCode());
        newPassport.setDate_validite(passportRequest.getDate_validite());
        newPassport.setCandidat(candidat);

        // Calculate the validity of the new passport
        newPassport.passportValide();

        if (!newPassport.isValid()) {
            throw new RuntimeException("La durée de vie du passport est insuffisante.");
        }

        return passportRepository.save(newPassport);
    }

    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    public Optional<Passport> getPassportById(Long id) {
        return passportRepository.findById(id);
    }

    public Passport updatePassport(Long id, PassportRequest passportRequest) {
        Optional<Passport> existingPassport = passportRepository.findById(id);

        if (existingPassport.isPresent()) {
            Passport passport = existingPassport.get();
            passport.setCode(passportRequest.getCode());
            passport.setDate_validite(passportRequest.getDate_validite());
            return passportRepository.save(passport);
        } else {
            throw new RuntimeException("Passport not found with id: " + id);
        }
    }

    public void deletePassport(Long id) {
        passportRepository.deleteById(id);
    }
}
