package com.example.backendstage.Services;
import com.example.backendstage.Models.EStatus;
import com.example.backendstage.Models.Candidat;

import com.example.backendstage.Models.*;
import com.example.backendstage.Repositories.*;
import com.example.backendstage.Requests.CandidatRequest;
import com.example.backendstage.Requests.Candidat_IdentityPieceRequest;
import com.example.backendstage.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;
    private final UserRepository userRepository;
    private final FonctionRepository fonctionRepository;
    private final Identity_pieceRepository identity_pieceRepository;
    private final Candidat_IdentityPiecesRepository candidatIdentityPiecesRepository;
    private  final EmployeurRepository employeurRepository;

    @Autowired
    public CandidatService(CandidatRepository candidatRepository,
                           FonctionRepository fonctionRepository,
                           Identity_pieceRepository identity_pieceRepository,
                           Candidat_IdentityPiecesRepository candidatIdentityPiecesRepository,
                           UserRepository userRepository, EmployeurRepository employeurRepository) {
        this.candidatRepository = candidatRepository;
        this.fonctionRepository = fonctionRepository;
        this.identity_pieceRepository = identity_pieceRepository;
        this.candidatIdentityPiecesRepository = candidatIdentityPiecesRepository;
        this.userRepository = userRepository;
        this.employeurRepository = employeurRepository;
    }

    // Méthode pour enregistrer un nouveau candidat dans la base de données
    public Candidat saveCandidat(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    // Méthode pour récupérer un candidat par son ID
    public Candidat getCandidatById(Long id) {
        return candidatRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les candidats de la base de données
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    // Méthode pour mettre à jour les informations d'un candidat
    public Candidat updateCandidat(Long id, CandidatRequest updatedCandidat) {
        // Check if the candidat with the given ID exists in the database
        Candidat existingCandidat = candidatRepository.findById(id).orElse(null);
        if (existingCandidat == null) {
            // Handle the case when the candidat with the given ID does not exist
            return null;
        }
        // Update the properties of the existing candidat with the properties of the updatedCandidat
        existingCandidat.setDate_naissance(updatedCandidat.getDate_naissance());
        existingCandidat.setSexe(updatedCandidat.getSexe());
        existingCandidat.setAdresse(updatedCandidat.getAdresse());
        existingCandidat.setNum_tel(updatedCandidat.getNum_tel());
        existingCandidat.setObs(updatedCandidat.getObs());
        existingCandidat.setTl(updatedCandidat.getTl());
        existingCandidat.setStatus(updatedCandidat.getStatus());
        existingCandidat.setSituation_fam(updatedCandidat.getSituation_fam());
        existingCandidat.setChildren(updatedCandidat.getChildren());

        // Save the updated candidat to the database
        return candidatRepository.save(existingCandidat);
    }

    // Méthode pour supprimer un candidat par son ID
    public void deleteCandidatById(Long id) {
        Candidat exCandidat = candidatRepository.findById(id).get();
        String email = exCandidat.getEmail();
        candidatRepository.deleteById(id);
        User user = userRepository.findByEmail(email);
        Long userID = user.getId();
        userRepository.deleteById(userID);
    }

    public Candidat assignFonctionToCandidat(Long candidatId, Long fonctionId) {
        Candidat candidat = candidatRepository.findById(candidatId).orElse(null);
        Fonction fonction = fonctionRepository.findById(fonctionId).orElse(null);

        if (candidat != null && fonction != null) {
            Set<Fonction> fonctionSet = candidat.getFonctions();
            if (fonctionSet == null) {
                fonctionSet = new HashSet<>();
            }

            fonctionSet.add(fonction);
            candidat.setFonctions(fonctionSet);
            candidatRepository.save(candidat);
        }

        return candidat;
    }

    public Candidat removeFonctionToCandidat(Long candidatId, Long fonctionId) {
        Candidat candidat = candidatRepository.findById(candidatId).orElse(null);
        Fonction fonction = fonctionRepository.findById(fonctionId).orElse(null);

        if (candidat != null && fonction != null) {
            Set<Fonction> fonctionSet = candidat.getFonctions();
            if (fonctionSet != null) {
                fonctionSet.remove(fonction);
                candidat.setFonctions(fonctionSet);
                candidatRepository.save(candidat);
            }
        }

        return candidat;
    }

    public Candidat_IdentityPieces ajouterIdentityPiecesAuCandidat(Long candidat_id, Long piece_id, Candidat_IdentityPieceRequest candidatIdentityPieceRequest) {
        Candidat candidat = candidatRepository.findById(candidat_id).orElseThrow(EntityNotFoundException::new);
        Identity_piece piece = identity_pieceRepository.findById(piece_id).orElseThrow(EntityNotFoundException::new);
        Candidat_IdentityPieces candidatIdentityPieces = new Candidat_IdentityPieces();
        candidatIdentityPieces.setCandidat(candidat);
        candidatIdentityPieces.setIdentityPiece(piece);
        candidatIdentityPieces.setCode(candidatIdentityPieceRequest.getCode());
        candidatIdentityPieces.setEtat(candidatIdentityPieceRequest.getEtat());
        candidatIdentityPieces.setCreated_at(LocalDateTime.now());
        candidatIdentityPieces.setDate_validite(candidatIdentityPieceRequest.getDate_validite());

        candidatIdentityPiecesRepository.save(candidatIdentityPieces);
        return candidatIdentityPieces;

    }

    public void uploadCV(Long id, MultipartFile file) throws IOException {
        Candidat candidate = candidatRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found!"));

        // Validate the file (e.g., check if it's a PDF)
        if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed!");
        }

        candidate.setCv(file.getBytes());
        candidatRepository.save(candidate);
    }

    public List<Candidat> getCandidatesByStatus(EStatus status) {
        return candidatRepository.findCandidatsByStatus(status);
    }

    public List<Candidat> getCandidatesByFunction(Fonction function) {
        return candidatRepository.findByFonctions(function);
    }

    

    public List<Candidat> getCandidatesByAgent(Agent agent) {
        return candidatRepository.findByAgent(agent);
    }

    public long countCandidatesByStatus(EStatus status) {
        return candidatRepository.countByStatus(status);
    }

    public List<Candidat> getCandidatesByGroup(String group) {
        return candidatRepository.findByGroupe(group);
    }

    public List<Candidat> getActiveCandidates() {
        EStatus activeStatus = EStatus.ACTIF;
        return candidatRepository.findCandidatsByStatus(activeStatus);
    }

    public List<Candidat> getInactiveCandidates() {
        EStatus inactiveStatus = EStatus.INACTIF;
        return candidatRepository.findCandidatsByStatus(inactiveStatus);
    }

    public void activateCandidat(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
        candidat.setStatus(EStatus.ACTIF);
        candidatRepository.save(candidat);
    }

    public void desactivateCandidat(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
        candidat.setStatus(EStatus.INACTIF);
        candidatRepository.save(candidat);
    }
    public void activateCandidatTLS(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
        candidat.setTlsRecu(Boolean.TRUE);
        candidatRepository.save(candidat);
    }

    public void desactivateCandidatTLS(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
        candidat.setTlsRecu(Boolean.FALSE);
        candidat.setVisaRecu(Boolean.FALSE);

        candidatRepository.save(candidat);
    }
    public void activateCandidatOFII(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
       candidat.setOfiiRecu(Boolean.TRUE);
        candidatRepository.save(candidat);
    }

    public void desactivateCandidatOFII(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
        candidat.setOfiiRecu(Boolean.FALSE);
        candidat.setVisaRecu(Boolean.FALSE);
        candidatRepository.save(candidat);
    }

    public void activateCandidatVISA(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
        candidat.setTlsRecu(Boolean.TRUE);
        candidat.setOfiiRecu(Boolean.TRUE);
        candidat.setVisaRecu(Boolean.TRUE);
        candidatRepository.save(candidat);
    }

    public void desactivateCandidatVISA(Long id) throws NotFoundException {
        Candidat candidat = getCandidatById(id);
        candidat.setTlsRecu(Boolean.FALSE);
        candidat.setOfiiRecu(Boolean.FALSE);
        candidat.setVisaRecu(Boolean.FALSE);

        candidatRepository.save(candidat);
    }




    public Candidat updateColorForCandidat(Long candidatId, String colorKey) throws CandidatNotFoundException {
        Candidat candidat = candidatRepository.findById(candidatId).orElse(null);
        if (candidat == null) {
            throw new CandidatNotFoundException("Candidat non trouvé pour l'ID : " + candidatId);
        }

        // Mettre à jour la couleur du candidat en fonction de la clé de couleur
        String newColor = null;
        switch (colorKey) {

            case "visa":
                candidat.setVisaColor(newColor);
                break;
            case "TLS":
                candidat.setTLSColor(newColor);
                break;
            case "OFFI":
                candidat.setOFFIColor(newColor);
                // Ajoutez d'autres cas pour d'autres clés de couleur
                break;
            default:
                // Gérer le cas où la clé de couleur n'est pas reconnue
                throw new IllegalArgumentException("Clé de couleur non valide : " + colorKey);
        }

        candidatRepository.save(candidat);
        return candidat;
    }

    public List<Candidat> getCandidatesByEmployeurId(Long employeurID) {
        return candidatRepository.findByEmployeurId(employeurID);

    }

    public List<Candidat> getCandidatsByEmployeurCode(String employeurCode) {
        return candidatRepository.findByEmployeurCodeEmp(employeurCode);
    }


}

