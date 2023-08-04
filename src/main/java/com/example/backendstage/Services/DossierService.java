package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Dossier;
import com.example.backendstage.Models.DossierPieces;
import com.example.backendstage.Models.Piece;
import com.example.backendstage.Repositories.DossierPiecesRepository;
import com.example.backendstage.Repositories.PieceRepository;
import com.example.backendstage.Requests.DossierPieceRequest;
import com.example.backendstage.exception.AlreadyExistsException;
import com.example.backendstage.Repositories.CandidatRepository;
import com.example.backendstage.Requests.DossierRequest;
import com.example.backendstage.Repositories.DossierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DossierService {
    private final DossierRepository dossierRepository;
    private final CandidatRepository candidatRepository;
    private final DossierPiecesRepository dossierPiecesRepository;
    private final PieceRepository pieceRepository;


    @Autowired
    public DossierService(DossierRepository dossierRepository, CandidatRepository candidatRepository,DossierPiecesRepository dossierPiecesRepository,PieceRepository pieceRepository) {
        this.dossierRepository = dossierRepository;
        this.candidatRepository = candidatRepository;
        this.dossierPiecesRepository=dossierPiecesRepository;
        this.pieceRepository=pieceRepository;
    }

    // Méthode pour enregistrer un nouveau dossier dans la base de données
    public Dossier saveDossier(Long idCandidat, DossierRequest dossierRequest) {
        Optional<Candidat> optionalCandidat = candidatRepository.findById(idCandidat);
        Dossier dossierCandidat= new Dossier();
        System.out.println(dossierRequest);
        Optional<Dossier> existDossir = dossierRepository.findByNumeroDossier(dossierRequest.getNumeroDossier());
        if (optionalCandidat.isPresent()) {
            if (existDossir.isPresent() && existDossir.get() !=null) {
                throw new AlreadyExistsException("Un dossier avec le même numéro existe déjà.");
            }
            Candidat candidat = optionalCandidat.get();
            System.out.println(candidat);
            dossierCandidat.setCandidat(candidat);
            dossierCandidat.setNumeroDossier(dossierRequest.getNumeroDossier());
            dossierCandidat.setDate_creation(dossierRequest.getDate_creation());
            dossierCandidat.setNote(dossierRequest.getNote());
            dossierCandidat.setStatus(dossierRequest.getStatus());

            dossierRepository.save(dossierCandidat);

        } else {
            throw new RuntimeException("Patient non trouvé avec l'ID : " + idCandidat);
        }
        return dossierCandidat;
    }
    // Méthode pour récupérer un dossier par son ID
    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les dossiers de la base de données
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'un dossier
    public Dossier updateDossier(Long id, Dossier updatedDossier) {
        // Check if the dossier with the given ID exists in the database
        Dossier existingDossier = dossierRepository.findById(id).orElse(null);
        if (existingDossier == null) {
            // Handle the case when the dossier with the given ID does not exist
            return null;
        }
        // Update the properties of the existing dossier with the properties of the updatedDossier
        existingDossier.setDate_creation(updatedDossier.getDate_creation());
        existingDossier.setCandidat(updatedDossier.getCandidat());
        existingDossier.setStatus(updatedDossier.getStatus());
        existingDossier.setNote(updatedDossier.getNote());

        // Save the updated dossier to the database
        return dossierRepository.save(existingDossier);
    }
    public List<DossierPieces> ajouterPiecesAuDossier(Long dossier_id) {
        Dossier dossier = dossierRepository.findById(dossier_id).orElseThrow(EntityNotFoundException::new);

        List<Piece> pieces = pieceRepository.findAll();

        List<DossierPieces> dossierPiecesList = pieces.stream().map(piece -> {
            DossierPieces dossierPieces = new DossierPieces();
            dossierPieces.setDossier(dossier);
            dossierPieces.setPiece(piece);
            dossierPieces.setDelivered(false);
            dossierPieces.setDeliveryDate(null);
            dossierPieces.setNote("No note yet");
            return dossierPieces;
        }).collect(Collectors.toList());

        dossierPiecesRepository.saveAll(dossierPiecesList);
        return dossierPiecesList;
    }
    public DossierPieces updateDossierPiece(Long dossierId, Long pieceId, DossierPieceRequest dossierPieceRequest) {
        Dossier dossier = dossierRepository.findById(dossierId).orElseThrow(EntityNotFoundException::new);
        Piece piece = pieceRepository.findById(pieceId).orElseThrow(EntityNotFoundException::new);

        DossierPieces dossierPiece = dossierPiecesRepository.findByDossierAndPiece(dossier, piece);

        if (dossierPiece == null) {
            // Handle the case when the DossierPieces entry is not found for the given Dossier and Piece.
            // You can throw an exception or handle it as per your requirement.
            throw new EntityNotFoundException("DossierPiece entry not found for the given Dossier and Piece.");
        }

        // Set the fields you want to update
        dossierPiece.setDelivered(true);
        dossierPiece.setDeliveryDate(LocalDate.now());
        dossierPiece.setNote(dossierPieceRequest.getNote());

        dossierPiecesRepository.save(dossierPiece);
        return dossierPiece;
    }
    // Méthode pour supprimer un dossier par son ID
    public void deleteDossierById(Long id) {
        dossierRepository.deleteById(id);
    }
}
