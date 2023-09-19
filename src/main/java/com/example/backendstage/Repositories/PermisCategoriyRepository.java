package com.example.backendstage.Repositories;

import com.example.backendstage.Models.Permis;
import com.example.backendstage.Models.PermisCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisCategoriyRepository extends JpaRepository<PermisCategorie,Long> {

    List<PermisCategorie> getAllByPermis(Permis permis);
}
