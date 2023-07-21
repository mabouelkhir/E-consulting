package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Piece;
import com.example.backendstage.Models.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PieceRepository extends JpaRepository<Piece,Long> {
}
