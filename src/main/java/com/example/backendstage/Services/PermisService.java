package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Permis;
import com.example.backendstage.Models.PermisCategorie;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Repositories.PermisCategoriyRepository;
import com.example.backendstage.Repositories.PermisRepository;
import com.example.backendstage.Requests.PermisCategorieRequest;
import com.example.backendstage.Requests.PermisRequest;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PermisService {

    private final PermisRepository permisRepository;
    private final PermisCategoriyRepository permisCategoriyRepository;
    private final CandidatRepository candidatRepository;

    public PermisService(PermisRepository permisRepository, PermisCategoriyRepository permisCategoriyRepository, CandidatRepository candidatRepository) {
        this.permisRepository = permisRepository;
        this.permisCategoriyRepository = permisCategoriyRepository;
        this.candidatRepository = candidatRepository;
    }

    public Permis savePermis(Long candidatID, PermisRequest permisRequest){
        Candidat exCandidat = candidatRepository.findById(candidatID).get();
        Permis permis = new Permis();
        permis.setCandidat(exCandidat);
        permis.setCode(permisRequest.getCode());
        permis.setDate_validite(permisRequest.getDate_validite());
        return permisRepository.save(permis);

    }

    public PermisCategorie ajouterCategorie(Long permisID, PermisCategorieRequest permisCategorieRequest) {
        Permis permis = permisRepository.findById(permisID).get();
        PermisCategorie permisCategorie = new PermisCategorie();
        permisCategorie.setPermis(permis);
        permisCategorie.setCategorie(permisCategorieRequest.getCategorie());
        permisCategorie.setDate_delivrance(permisCategorieRequest.getDate_delivrance());
        return permisCategoriyRepository.save(permisCategorie);

    }
    public Permis updatePermis(Long permisID, PermisRequest permisRequest) {
        Permis permis = permisRepository.findById(permisID).orElse(null);
        if (permis != null) {
            permis.setCode(permisRequest.getCode());
            permis.setDate_validite(permisRequest.getDate_validite());
            return permisRepository.save(permis);
        }
        return null; // Handle the case where the Permis with the given ID is not found.
    }
    public PermisCategorie updatePermisCategorie(Long permisCategorieID, PermisCategorieRequest permisCategorieRequest) {
        PermisCategorie permisCategorie = permisCategoriyRepository.findById(permisCategorieID).orElse(null);
        if (permisCategorie != null) {
            permisCategorie.setCategorie(permisCategorieRequest.getCategorie());
            permisCategorie.setDate_delivrance(permisCategorieRequest.getDate_delivrance());
            return permisCategoriyRepository.save(permisCategorie);
        }
        return null; // Handle the case where the PermisCategorie with the given ID is not found.
    }
    public Permis getPermisById(Long permisID) {
        return permisRepository.findById(permisID).orElse(null); // Returns null if Permis is not found.
    }

    public List<PermisCategorie> getPermisByCandidatID(Long candidatID) {
    Candidat candidat = candidatRepository.findById(candidatID).get();
    Permis permis = permisRepository.getPermisByCandidat(candidat);

    List<PermisCategorie> permisCategorie = permisCategoriyRepository.getAllByPermis(permis);
    return permisCategorie;
    }
    public PermisCategorie getPermisCategorieById(Long permisCategorieID) {
        return permisCategoriyRepository.findById(permisCategorieID).orElse(null); // Returns null if PermisCategorie is not found.
    }
    public void deletePermisCategorieById(Long permisCategorieID) {
        permisCategoriyRepository.deleteById(permisCategorieID);
    }
    public void deletePermisById(Long permisID) {
        permisRepository.deleteById(permisID);
    }

    public List<PermisCategorie> getAllPermisCategorie(){
        return permisCategoriyRepository.findAll();
    }

    public List<Permis> getAllPermis() {
        return permisRepository.findAll();
    }
}
