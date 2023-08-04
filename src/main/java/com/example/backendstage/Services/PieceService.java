package com.example.backendstage.Services;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Dossier;
import com.example.backendstage.Models.Piece;
import com.example.backendstage.Repositories.PieceRepository;
import com.example.backendstage.Requests.DossierRequest;
import com.example.backendstage.Requests.PieceRequest;
import com.example.backendstage.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PieceService {
    private final PieceRepository pieceRepository;

    @Autowired
    public PieceService(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    // Méthode pour enregistrer une nouvelle pièce dans la base de données
    public Piece savePiece(PieceRequest pieceRequest) {
        Piece piece= new Piece();

            piece.setNom_piece(pieceRequest.getNom_piece());


            pieceRepository.save(piece);


        return piece;
    }
    public List<Long> getAllPieceIds() {
        List<Piece> pieces = pieceRepository.findAll();
        return pieces.stream()
                .map(Piece::getId)
                .collect(Collectors.toList());
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
