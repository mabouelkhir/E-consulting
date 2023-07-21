package com.example.backendstage.Services;

import com.example.backendstage.Models.Piece;
import com.example.backendstage.Repositories.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PieceService {
    private final PieceRepository pieceRepository;

    @Autowired
    public PieceService(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    // Méthode pour enregistrer une nouvelle pièce dans la base de données
    public Piece savePiece(Piece piece) {
        return pieceRepository.save(piece);
    }

    // Méthode pour récupérer une pièce par son ID
    public Piece getPieceById(Long id) {
        return pieceRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer toutes les pièces de la base de données
    public List<Piece> getAllPieces() {
        return pieceRepository.findAll();
    }

    // Méthode pour supprimer une pièce par son ID
    public void deletePieceById(Long id) {
        pieceRepository.deleteById(id);
    }
}
