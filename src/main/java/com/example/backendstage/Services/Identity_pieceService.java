package com.example.backendstage.Services;

import com.example.backendstage.Models.Identity_piece;
import com.example.backendstage.Repositories.Identity_pieceRepository;
import com.example.backendstage.Requests.IdentityPieceRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Identity_pieceService {
    private final Identity_pieceRepository identityPieceRepository;

    public Identity_pieceService(Identity_pieceRepository identityPieceRepository) {
        this.identityPieceRepository = identityPieceRepository;
    }
    // Méthode pour enregistrer une nouvelle pièce d'identité dans la base de données
    public Identity_piece saveIdentityPiece(IdentityPieceRequest IdentityPieceRequest) {
        Identity_piece identity_piece = new Identity_piece();
        identity_piece.setName(IdentityPieceRequest.getName());
        return identityPieceRepository.save(identity_piece);
    }

    // Méthode pour récupérer une pièce d'identité par son ID
    public Identity_piece getIdentityPieceById(Long id) {
        return identityPieceRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer toutes les pièces d'identité de la base de données
    public List<Identity_piece> getAllIdentityPieces() {
        return identityPieceRepository.findAll();
    }
    // Méthode pour mettre à jour les informations d'une pièce d'identité
    public Identity_piece updateIdentityPiece(Long id, Identity_piece updatedIdentityPiece) {
        // Check if the identity piece with the given ID exists in the database
        Identity_piece existingIdentityPiece = identityPieceRepository.findById(id).orElse(null);
        if (existingIdentityPiece == null) {
            // Handle the case when the identity piece with the given ID does not exist
            return null;
        }

        // Update the properties of the existing identity piece with the properties of the updatedIdentityPiece
        existingIdentityPiece.setName(updatedIdentityPiece.getName());


        // Save the updated identity piece to the database
        return identityPieceRepository.save(existingIdentityPiece);
    }

    // Méthode pour supprimer une pièce d'identité par son ID
    public void deleteIdentityPieceById(Long id) {
        identityPieceRepository.deleteById(id);
    }
}
