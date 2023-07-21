package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Identity_piece;
import com.example.backendstage.Services.Identity_pieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/id_piece")
public class Identity_pieceController {

    private final Identity_pieceService identity_pieceService;
    @Autowired
    public Identity_pieceController(Identity_pieceService identity_pieceService) {
        this.identity_pieceService = identity_pieceService;
    }


    //pour enregistrer un nouvel identity
    @PostMapping("/Save")
    public Identity_piece saveIdentity_piece(@RequestBody Identity_piece identity_piece) {
        return identity_pieceService.saveIdentityPiece(identity_piece);
    }

    //pour récupérer tous les identiy
    @GetMapping("/Identity_piece")
    public List<Identity_piece> getAllIdentity_pieces() {
        List<Identity_piece> identity_pieces = identity_pieceService.getAllIdentityPieces();
        return identity_pieces;
    }

    //pour supprimer un identity par son ID
    @DeleteMapping("/{id}")
    public void  deleteIdentity_pieceById(@PathVariable Long id) {
        identity_pieceService.deleteIdentityPieceById(id);
    }

}
