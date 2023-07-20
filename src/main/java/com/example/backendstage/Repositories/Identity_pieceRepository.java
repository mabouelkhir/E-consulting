package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Identity_piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Identity_pieceRepository extends JpaRepository<Identity_piece,Long> {
}
