package com.example.backendstage.Services;

import com.example.backendstage.Models.Fonction;
import com.example.backendstage.Models.SubFonction;
import com.example.backendstage.Repositories.FonctionRepository;
import com.example.backendstage.Repositories.SubFonctionRepository;
import com.example.backendstage.Requests.SubFonctionRequest;
import com.example.backendstage.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubFonctionService {
    private final SubFonctionRepository subFonctionRepository;
    private final FonctionRepository fonctionRepository;
@Autowired
    public SubFonctionService(SubFonctionRepository subFonctionRepository, FonctionRepository fonctionRepository) {
        this.subFonctionRepository = subFonctionRepository;
    this.fonctionRepository = fonctionRepository;
}
    public SubFonction createSubFonction(Long fonctionID, SubFonctionRequest subFonctionRequest) {
        Fonction exFonction = fonctionRepository.findById(fonctionID).orElse(null);

        if (exFonction != null) {
            SubFonction subFonction = new SubFonction();
            subFonction.setNomSubFonction(subFonctionRequest.getNomSubFonction());
            subFonction.setFonction(exFonction); // Associate subFonction with the parent Fonction
            subFonctionRepository.save(subFonction);

            return subFonction;
        }

        return null; // Or throw an exception if necessary
    }

    public SubFonction updateSubFonction(Long subFonctionId, SubFonctionRequest updatedSubFonction) throws NotFoundException {
        if (!subFonctionRepository.existsById(subFonctionId)) {
            throw new NotFoundException("SubFonction not found");
        }
        SubFonction exSub = subFonctionRepository.findById(subFonctionId).get();
        exSub.setNomSubFonction(updatedSubFonction.getNomSubFonction());
        return subFonctionRepository.save(exSub);
    }
    public void deleteSubFonction(Long subFonctionId) {
        subFonctionRepository.deleteById(subFonctionId);
    }

    public List<SubFonction> getAllSubFonctionsWithFunctions() {
        return subFonctionRepository.findAll();
    }
    public SubFonction getSubFonctionById(Long subFonctionId) throws NotFoundException {
        return subFonctionRepository.findById(subFonctionId)
                .orElseThrow(() -> new NotFoundException("SubFonction not found"));
    }
}
