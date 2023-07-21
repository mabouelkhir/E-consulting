package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Piece;
import com.example.backendstage.Services.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Piece")

public class PieceController {
    private final PieceService pieceService;
@Autowired
    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }


    //pour enregistrer une nouvelle piece
    @PostMapping("/Save")
    public Piece savePiece(@RequestBody Piece piece) {
        return pieceService.savePiece(piece);
    }

    //pour récupérer tous les pieces
    @GetMapping("/Piece")
    public List<Piece> getAllPieces() {
        List<Piece> pieces = pieceService.getAllPieces();
        return pieces;
    }

    //pour supprimer une piece par son ID
    @DeleteMapping("/{id}")
    public void  deletePieceById(@PathVariable Long id) {
        pieceService.deletePieceById(id);
    }
}
