package com.example.backendstage.Services;

import com.example.backendstage.Models.Identity_piece;
import com.example.backendstage.Repositories.Identity_pieceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Identity_pieceService {
    private final Identity_pieceRepository identityPieceRepository;

    public Identity_pieceService(Identity_pieceRepository identityPieceRepository) {
        this.identityPieceRepository = identityPieceRepository;
    }
    // Méthode pour enregistrer une nouvelle pièce d'identité dans la base de données
    public Identity_piece saveIdentityPiece(Identity_piece identityPiece) {
        return identityPieceRepository.save(identityPiece);
    }

    // Méthode pour récupérer une pièce d'identité par son ID
    public Identity_piece getIdentityPieceById(Long id) {
        return identityPieceRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer toutes les pièces d'identité de la base de données
    public List<Identity_piece> getAllIdentityPieces() {
        return identityPieceRepository.findAll();
    }

    // Méthode pour supprimer une pièce d'identité par son ID
    public void deleteIdentityPieceById(Long id) {
        identityPieceRepository.deleteById(id);
    }
}
