package org.example.soutnance.repository;


import org.example.soutnance.domain.Recoltes;
import org.example.soutnance.domain.enums.Saison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecolteRepository extends JpaRepository<Recoltes,Long> {
    boolean existsByChamp_IdAndSaison(Long id, Saison saison);
}
